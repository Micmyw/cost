<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/3
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%
    /**
     * 解决中文乱码问题；
     * 当表单使用get提交时，使用下面方法
     *String str= URLEncoder.encode(request.getParameter("name"),"UTF-8");
     *
     * 当使用使用post方法提交时使用"ISO-8859-1"解决中文乱码问题
     */
    String  str=new String((request.getParameter("name").getBytes("ISO-8859-1")),"UTF-8");
    //设置name和url  cookie
    Cookie name=new Cookie("name",str);
    Cookie url=new Cookie("url",request.getParameter("url"));
    //设置coolie过期时间
    name.setMaxAge(3600*24);
    url.setMaxAge(3600*24);
    //在响应头部添加cookie
    response.addCookie(name);
    response.addCookie(url);
%>
<html>
<head>
    <title>getTest</title>
</head>
<body>
    <h2>使用get或post方法提交数据</h2>
    <ul>
        <li><p><b>站点名：
            <!--获取表单参数的值，传入进去的是表单属性对应name属性的值-->
            <%=request.getParameter("name")%>
        </b></p></li>
        <li><p><b>网址：
            <%=request.getParameter("url")%>
        </b></p></li>
    </ul>
</body>
</html>
