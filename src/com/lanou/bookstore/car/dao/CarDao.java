package com.lanou.bookstore.car.dao;

import com.lanou.bookstore.book.domain.BookBean;
import com.lanou.bookstore.car.domain.CarBean;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * update工具
 * query 工具
 */
public class CarDao {
    private GxQueryRunner runner = new GxQueryRunner();
    private int flag;

    //添加到购物车
    public boolean buyBook(CarBean carBean) { //update工具类返回的就是布尔
        String sql = "insert into cart values (?,?,?,?,?,?,?)";
        try {
            flag = runner.update(sql,
                    carBean.getCarid(),
                    carBean.getImg(),
                    carBean.getBookname(),
                    carBean.getAnthor(),
                    carBean.getPrice(),
                    carBean.getCount(),
                    carBean.getUid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (flag>0){
            return true;
        }else {
            return false;
        }
    }


    //**********************************
    //uid-->人(car)
    public List<CarBean> LookByUid(String uid) {
        String sql = "select * from cart where uid = ? "; //没想到:要填一个uid的查询条件
        List<CarBean> carBeanLists = new ArrayList<>();
        try {
            carBeanLists = runner.query(sql, new BeanListHandler<CarBean>(CarBean.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carBeanLists;
    }

    //******************
    //查找购物车列表
    public  List<CarBean> LookCarList(){
        String sql ="select * from cart";
        List<CarBean> carLists=new ArrayList<>();
        try {
            carLists =runner.query(sql,new BeanListHandler<CarBean>(CarBean.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carLists;
    }
    //删除购物车里的书(通过carid) (添加和删除返回值都是boolean)
    public boolean DelBook(String carid) {
        String sql = "delete from cart where carid= ?";
        try {
            flag = runner.update(sql,carid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (flag>0){
            return true;
        }else {
            return false;
        }
    }
//******************************
    //清空购物车(byUid)
//    public boolean DelAllBooks(String img){
//        String sql ="delete from cart where  = ?";
//
//        try {
//            flag=runner.update(sql,new BeanListHandler<CarBean>(CarBean.class),img);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
