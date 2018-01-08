package com.lanou.bookstore.user.web;

import com.lanou.bookstore.BeanUtil;
import com.lanou.bookstore.user.domain.*; //手动导包
import com.lanou.bookstore.user.service.*;
import com.lanou.commons.CommonUtils;
import com.lanou.jdbc.GxQueryRunner;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 数据从前端到数据库
 */
@WebServlet(name = "UserServlet", urlPatterns = "/user") // "/user"接收
public class UserServlet extends HttpServlet {
    private UserService service = new UserService();
    private int flag;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf8");
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8"); //更改编码格式
        String method = request.getParameter("method");
        switch (method) {
            case "login":
                userLogin(request, response);
                break;
            case "regist":
                userRegist(request, response);
        }
    }

    //用户注册
    private void userRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserBean bean = new UserBean(); //new实体类
        BeanUtil.requestToBean(request, bean);
        boolean flag = service.userRegist(bean);//存入数据库
        if (flag) {
            request.setAttribute("msg", "注册成功");
        } else {
            request.setAttribute("msg", "注册失败");
        }
        request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
    }

    //用户登录
    private void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String userName = request.getParameter("username");//前端获取信息
        UserBean bean = service.userLogin(userName);//填入方法
        String password = request.getParameter("password");
        Cookie cookie1 = new Cookie("username", userName);//信息以键值对的形式
        Cookie cookie2 = new Cookie("password", password);
        response.addCookie(cookie1);//存进Cookie
        response.addCookie(cookie2);




        if (!userName.isEmpty()&&password!=null&&bean!=null) {//userName.isEmpty()没有填用户 :提示不存在
                                                                //bean!=null填写的用户在数据库没找到:提示不存在
            if (userName.equals(bean.getUsername())&&
                    password.equals(bean.getPassword())) { //已经获取到了password就直接调用.
                                                        // 错误写法request.getParameter("username").password.equals(bean.getPassword())
                HttpSession session = request.getSession();
                session.setAttribute("username", userName);//记录
                //session.getAttribute("username");这步没有用
                request.setAttribute("msg","登录成功");//在请求转发前设置信息
                request.getRequestDispatcher("/jsps/main.jsp").forward(request, response);

                String uid = bean.getUid();
                session.setAttribute("uid",uid);//set一个uid传到carvsert,用来设置uid信息

            } else {
                request.setAttribute("msg", "密码错误");
                request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
            }
        }else {
            request.setAttribute("msg","用户不存在");
            request.getRequestDispatcher("/jsps/user/login.jsp").forward(request,response);
        }

    }
}
