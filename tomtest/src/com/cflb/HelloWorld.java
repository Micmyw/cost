package com.cflb;

import javax.naming.Name;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
    private String message;

    public void init() throws ServletException {
        //执行初始化
        message = "HelloWorld";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name = new String(req.getParameter("name").getBytes("ISO8859-1"), "UTF-8");
        //设置相应内容类型
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();
        String docType = "<!DOCTYPE html> \n";
        String title = "使用 GET 方法读取表单数据";
        out.println(docType +
                "<html>\n"+
                "<head><title>"+title +"</title></head>\n"+
                "<body bgcolor=\"#f0f0f0\">\n"+
                "<h1 align=\"center\">"+title +"</h1>\n"+
                "<ul>\n"+
                "  <li><b>站点名</b>："
                + name +"\n"+
                "  <li><b>网址</b>："
                +req.getParameter("url")+"\n"+
                "</ul>\n"+
                "</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
