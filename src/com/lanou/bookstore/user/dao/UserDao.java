package com.lanou.bookstore.user.dao;

import com.lanou.bookstore.user.domain.UserBean;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/12/22.
 */
public class UserDao {

    private GxQueryRunner runner = new GxQueryRunner();
    private int flge;


    //用户注册
    public boolean userRegist(UserBean bean) {
        String sql = "insert into USER values(?,?,?,?,?)";
        try {
            //从前端获取信息存到数据库
            flge = runner.update(sql,
                    bean.getUid(),
                    bean.getUsername(),
                    bean.getPassword(),
                    bean.getEmail(),
                    bean.getCode()
            );


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (flge > 0) {
                return true;
            } else {
                return false;
            }
        }
    }



    //登录
    public UserBean userLogin(String name) { //用name去找到对象,所以用UserBean
                                            //错误写法List<UserBean> userLogin(UserBean bean)

       UserBean userBean1 = new UserBean();//错误写法是用了数组

       String  sql = "select *from USER where username= ? ";  //从数据库列表里查到很多username
                //USER 大写,表名就是大写

        try {
          userBean1= runner.query(sql, new BeanHandler<UserBean>(UserBean.class),name); //携带name信息
                        //错误写法 runner.query(sql, new BeanListHandler<UserBean>(UserBean.class))
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userBean1; //把得到返回给对象
    }


}
