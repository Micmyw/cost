package com.dao;

import com.domian.Book;
import com.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库访问层（只做与数据库相关操作）
 */
public class BookDAO {
    /**
     * 添加书籍信息
     * @param book 书籍信息
     * @return  返回书籍影响的行数
     */
    public int  add(Book book){
        Connection con = null;
        PreparedStatement pre = null;
        try {
            //获取连接
            con= DButils.getConnection();
            //获取数据库对象
            String sql = "insert into t_book(book_id,book_name,book_witer,price) values(?,?,?,?)";
            //预准备状态来获取sql值
            pre = con.prepareStatement(sql);
            pre.setInt(1, book.getBookId());
            pre.setString(2, book.getBookName());
            pre.setString(3, book.getWriter());
            pre.setDouble(4, book.getPrice());

            //执行SQL
            return pre.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 修改书籍
     * @param book 书籍信息
     * @return  返回书籍影响的行数
     * @return
     */
    public int update(Book book){
        Connection con = null;
        PreparedStatement pre = null;
        try {
            //获取连接
            con= DButils.getConnection();
            //获取数据库对象
            String sql = "update t_book  set book_name=?,book_witer=?,price=? where book_id=?";
            //预准备状态来获取sql值
            pre = con.prepareStatement(sql);
            pre.setString(1, book.getBookName());
            pre.setString(2, book.getWriter());
            pre.setDouble(3, book.getPrice());
            pre.setInt(4, book.getBookId());

            //执行SQL
            return pre.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除书籍
     *  @param bookId 书籍信息
     * @return  返回书籍影响的行数
     * @return
     */
    public int delete(Integer bookId){
        Connection con = null;
        PreparedStatement pre = null;
        try {
            //获取连接
            con= DButils.getConnection();
            //获取数据库对象
            String sql = "delete from t_book  where book_id=?";
            //预准备状态来获取sql值
            pre = con.prepareStatement(sql);
            pre.setInt(1, bookId);

            //执行SQL
            return pre.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据编号查询书籍
     * @param bookId 书籍编号
     * @return
     */
    public Book getBook(int bookId){
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            //获取连接
            con= DButils.getConnection();
            //获取数据库对象
            String sql = "SELECT * from T_BOOK where BOOK_ID =?";
            pre = con.prepareStatement(sql);
            pre.setInt(1, bookId);
            //执行SQL，返回结果集
            res = pre.executeQuery();
            if (res.next()) {
                Book book = new Book();
                book.setBookId(res.getInt("book_id"));
                book.setBookName(res.getString("book_name"));
                book.setWriter(res.getString("book_writer"));
                book.setPrice(res.getDouble("price"));
                return book;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DButils.close(res, pre, con);
        }
        return null;
    }

    /**
     * 查询所有书籍信息
     * @return 返回所有书籍信息
     */
    public List<Book> list(){
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            //获取连接
            con= DButils.getConnection();
            //获取数据库对象
            String sql = "seclet * from t_book ";
            pre = con.prepareStatement(sql);

            List<Book> list = new ArrayList<Book>();

            //执行SQL，返回结果集
            res = pre.executeQuery();
            while (res.next()) {
                Book book = new Book();
                book.setBookId(res.getInt("book_id"));
                book.setBookName(res.getString("book_name"));
                book.setWriter(res.getString("book_writer"));
                book.setPrice(res.getDouble("price"));
                list.add(book);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DButils.close(res, pre, con);
        }
        return null;
    }
}
