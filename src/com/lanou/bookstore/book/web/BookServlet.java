package com.lanou.bookstore.book.web;

import com.lanou.bookstore.book.domain.BookBean;
import com.lanou.bookstore.book.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/** 右键MySQL选择 OPen New Console可以验证sql查询语句
 *
 * 流程 :       Left点击->servlet接收->list显示
 *                          ^
 *                          |
 * 数据库->dao->service->servlet
 */
@WebServlet(name = "BookServlet", urlPatterns = "/books")//接收
public class BookServlet extends HttpServlet {
    private BookService service = new BookService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf8");

        String method = request.getParameter("method");
        switch (method) {
            case "findAllBook"://所有书籍  " "引号里面的要写进left.jsp value值中
                findAllBook(request, response);
                break;
            case "findBookSE":  //JavaEE分类
                findSomeBooks(request,response);
                break;
            case "findBookJE": //JavaJE分类
                findSomeBooks(request,response);
                break;
            case "findBookJS": //JavaScript分类
                findSomeBooks(request,response);
                break;
            case "findOne": //点书进入图书信息(car)
                findOneBook(request,response);
        }
    }
    //跳转到描述界面
    private void findOneBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        BookBean book2 = service.findOneBook(bid);
        request.setAttribute("book1",book2);//在book/desc.jsp中,用key调用
        request.getRequestDispatcher("/jsps/book/desc.jsp").forward(request,response);
    }


    //查出所有书籍

    private void findAllBook(HttpServletRequest request, HttpServletResponse response) {
        List<BookBean> beanLists = service.findAllBook();//数据库
        request.setAttribute("allbook", beanLists); //"allbook"发送到前端的key值,EL表达式可以用allbook这个key值调用数据
                                                    //"allbook"key值必须与/jsps/book.jsp的items="${allbook}名相同

        try {
            request.getRequestDispatcher("/jsps/book/list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //分类查询
    private void findSomeBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");//从前端获取cid信息

        List<BookBean> someBooks=service.findSomeBooks(cid);//实体类->Dao->service->servlet存到集合中

        request.setAttribute("allbook",someBooks); //"bookClass"为要显示的value;request.setAttribute存到服务器里

        request.getRequestDispatcher("/jsps/book/list.jsp").forward(request,response);
    }


}

    
 
