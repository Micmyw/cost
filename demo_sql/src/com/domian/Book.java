package com.domian;
//该层存放实体类的包
/**
 * 书籍管理的实体类
 * 书籍属性（书籍名称，书籍作者等）
 * 书籍的行为（添加书籍add,修改书籍update，删除书籍delete，查询书籍select）
 * 只包含属性，贫血模型：分离DB（DAO层），专门用于数据库操作
 * 如多有方法，充血模型，不包含get，set方法
 */
public class Book {

    /**
     *    书籍名称
     */
    private  int bookId;
    /**
     * 书籍名称
     */
    private  String bookName;
    /**
     *  书籍作者
     */
    private  String writer;
    /**
     * 书籍价格
     */
    private  Double price;


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", writer='" + writer + '\'' +
                ", price=" + price +
                '}';
    }
}
