package com.service;

import com.dao.BookDAO;
import com.domian.Book;

import java.util.List;

/**
 * 书籍的业务逻辑层
 */
public class BookService {
    //数据访问对象
    BookDAO dao = new BookDAO();
    /**
     * 添加
     * @param book 书籍信息
     * @return  true 添加成功 false 添加失败
     */
    public boolean add(Book book){
        if (book!=null) {
            int i = dao.add(book);
            if (i>0) {
                return true;
            }
        }

        return  false;
    }

    /**
     * 修改
     * @param book 书籍信息
     * @return true 修改成功 false 修改失败
     */
    public boolean update(Book book){
        if (book!=null) {
            int i = dao.update(book);
            if (i>0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除（通过bookId来删除书籍）
     * @param book 书籍信息
     * @return true 删除成功 false 删除失败
     */
    public boolean delete(Book book){
        if(book!=null && book.getBookId()!=0){
            int i = dao.delete(book.getBookId());
            if (i>0) {
                return true;
            }
        }
        return false;
    }

    /**
     *根据编号来查询书籍信息
     * @param book
     * @return 返回书籍信息
     */
    public Book getBook(Book book){
        if (book!=null && book.getBookId()!=0) {
            return dao.getBook(book.getBookId());
        }
        return null;
    }

    /**
     * 查询所有书籍信息
     * @return 返回所有书籍信息
     */
    public List<Book> list(){
       return dao.list();
    }
}
