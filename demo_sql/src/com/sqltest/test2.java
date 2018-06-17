package com.sqltest;

import com.domian.Book;
import com.service.BookService;

import java.util.List;

public class test2 {
    public static void main(String [] args){
        BookService bs = new BookService();
//        List<Book> list = bs.list();
//         for(Book book : list) {
//            System.out.println(book.getBookId()+" "+book.getBookName()+" "+book.getWriter()+" "+book.getPrice());
//        }
        Book book= new Book();
        book.setBookId(1001);
        System.out.println(bs.getBook(book));
//        System.out.println(book.getBookId()+" "+book.getBookName()+" "+book.getWriter()+" "+book.getPrice());
    }
}
