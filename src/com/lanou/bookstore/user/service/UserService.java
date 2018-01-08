package com.lanou.bookstore.user.service;

import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.domain.UserBean;
import com.lanou.commons.CommonUtils;

import java.util.List;

/**
 * Created by dllo on 17/12/22.
 */
public class UserService {
    private UserDao userDao=new UserDao();
    public boolean userRegist(UserBean bean){//复写
        bean.setUid(CommonUtils.uuid()); //随机的uid添加
        return userDao.userRegist(bean);
    }

    public UserBean userLogin(String name){
       return userDao.userLogin(name);
    }


}
