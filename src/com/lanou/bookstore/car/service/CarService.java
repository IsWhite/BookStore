package com.lanou.bookstore.car.service;

import com.lanou.bookstore.book.domain.BookBean;
import com.lanou.bookstore.car.dao.CarDao;
import com.lanou.bookstore.car.domain.CarBean;
import com.lanou.bookstore.user.domain.UserBean;
import com.lanou.commons.CommonUtils;

import java.util.List;

/**
 * Created by dllo on 17/12/25.
 */
public class CarService {
    private CarDao carDao =new CarDao();

    //添加商品
    public boolean Buybook(CarBean bean){
        bean.setCarid(CommonUtils.uuid()); //设置一个cid(随机)
        return carDao.buyBook(bean);
    }


    //通过uid查找
    public List<CarBean> LookByUid(String uid){
        return carDao.LookByUid(uid);
    }

    //查找cart表
    public  List<CarBean> LookCarList(){
        return carDao.LookCarList();
    }

    //删除商品
    public boolean DelBook(String carid){
        return carDao.DelBook(carid);
    }
}
