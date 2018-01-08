package com.lanou.bookstore.car.servlet;

import com.lanou.bookstore.BeanUtil;
import com.lanou.bookstore.book.domain.BookBean;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.car.domain.CarBean;
import com.lanou.bookstore.car.service.CarService;
import com.lanou.bookstore.user.domain.UserBean;
import com.lanou.bookstore.user.service.UserService;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 右键表格Modify Table更改表格属性
 */
@WebServlet(name = "CarServlet", urlPatterns = "/car")
public class CarServlet extends HttpServlet {
    private CarService service = new CarService();
    private BookService bookService = new BookService(); //用来调用book方法

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");
        String method = request.getParameter("method");
        switch (method) {
            case "addbuybook": //添加要买的书
                addBuyBooks(request, response);
                break;
            case "buyCar": //购物车
                buyCars(request, response);
                break;
            case "empty"://清空购物车
                emptyCar(request, response);
                break;
            case "delCarBook"://删除购物车里的书
                delCarBooks(request, response);
                break;

        }
    }

    //清空购物车
    private void emptyCar(HttpServletRequest request, HttpServletResponse response) {


    }


    //添加图书
    private void addBuyBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //bid->书
        String newBid = request.getParameter("bid");
        BookBean bookBean = bookService.findOneBook(newBid); //书不是new出来的,是从数据库得到的;(错误写法:new出来一个对象)

        //uid->人
                                                             // String userUid=request.getParameter("uid"); 错误写法:不能直接从前端获取uid
        String uid = (String) request.getSession().getAttribute("uid"); //session中得到uid,没想到的是:要经过getSession()
        if (uid == null) {                                  //得到uid后判断,作用域只在这层
            request.setAttribute("msg", "请您先登录");
            request.getRequestDispatcher("/jsps/msg2.jsp").forward(request,response);
        } else if (uid != null) {                           //如果不符合第一条件,判断另个条件
            //新建个车
            List<CarBean> carBeanList = new ArrayList<>();  //有好多本书,所以用数组
            CarBean carbean = new CarBean();

            //书的信息转到车里
            carbean.setImg(bookBean.getImage());
            carbean.setBookname(bookBean.getBname());
            carbean.setAnthor(bookBean.getAuthor());
            carbean.setPrice(bookBean.getPrice());
            carbean.setUid(uid);  //uid确认用户的最后一步,没想到的是:sql查询条件
            carbean.setCount((request.getParameter("count")));


            boolean flag = service.Buybook(carbean);//添加到数据库

            carBeanList.add(carbean);//添加到集合
            carBeanList = service.LookByUid(uid);//重新调用方法显示,刷新

            if (flag) {
                request.setAttribute("msg", "添加到购物车");
                request.setAttribute("Newbooks", carBeanList);
                request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request, response);

            } else {
                request.setAttribute("msg", "失败");
                request.getRequestDispatcher("/jsps/book/list.jsp").forward(request, response);
            }
        }
    }


    //点击购物车看所有要买的书
    private void buyCars(HttpServletRequest request, HttpServletResponse response) {
        try {
            String uid= (String) request.getSession().getAttribute("uid");
            if (uid==null){
                request.getRequestDispatcher("/jsps/msg2.jsp").forward(request,response);
            }else {
                List<CarBean> LookNewBooks = service.LookByUid(uid); //数据库获得
                request.setAttribute("Newbooks", LookNewBooks); //设置到前端,存到request中
                request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request, response);

            }

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除列表里的书(通过carid)(carid->删除 ,session->uid->设置->显示)
    private void delCarBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carid = request.getParameter("carid"); //前端
        boolean flag = service.DelBook(carid); //数据库
        //uid查找和显示


        if (flag) {
            request.setAttribute("msg", "删除成功");
          List<CarBean> LookNewBooks = service.LookCarList(); //删除数据后再重新获取
            request.setAttribute("Newbooks", LookNewBooks);//并set

        } else {
            request.setAttribute("msg", "删除失败");
        }
        request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request, response);
    }


}
