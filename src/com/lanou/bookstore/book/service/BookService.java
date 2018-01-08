package com.lanou.bookstore.book.service;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.domain.BookBean;

import java.util.List;

/**
 * Created by dllo on 17/12/23.
 */
public class BookService {
    private BookDao bookDao =new BookDao();

    public List<BookBean> findAllBook(){
        return bookDao.findAllBook();
    }

    public List<BookBean> findSomeBooks(String cid){  //参数BookBean连接数据库 bean填入方法
        return bookDao.findSomeBooks(cid);               //承上启下
    }

    //找到一本书
    public BookBean findOneBook(String bid){
        return bookDao.FindBookBid(bid);
    }

}
