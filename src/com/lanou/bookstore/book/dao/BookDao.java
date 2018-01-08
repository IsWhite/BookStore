package com.lanou.bookstore.book.dao;

import com.lanou.bookstore.book.domain.BookBean;
import com.lanou.bookstore.user.domain.UserBean;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BookDao {
    private GxQueryRunner runner = new GxQueryRunner();
    private int flag;


    //全部分类(全部查找)

    public List<BookBean> findAllBook() {
        List<BookBean> bookBeans = new ArrayList<>();//创建数组用来存储
        String sql = "select * from book";
        try {
            bookBeans = runner.query(sql, new BeanListHandler<BookBean>(BookBean.class));//查出来以集合的形式返回

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return bookBeans;

    }

    //分类查询
    public List<BookBean> findSomeBooks(String cid) { //只需要用cid作为参数,而不需要太大的对象(BookBean bean)作为参数

        List<BookBean> bookClasses =new ArrayList<>();

        String sql = "select * from book where cid = ?"; //查询所有cid
        try {
          bookClasses=runner.query(sql, new BeanListHandler<BookBean>(BookBean.class),cid);//携带cid


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookClasses;
    }

    //找到点击的书书籍的方法(FindBookBid)
    public BookBean FindBookBid(String bid){ //数据类型:BookBean类中有书所有信息
        String sql ="select * from book where bid = ?";

        BookBean bookBeenBids=new BookBean();
        try {
            bookBeenBids=runner.query(sql,new BeanHandler<BookBean>(BookBean.class),bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookBeenBids;
    }


}







