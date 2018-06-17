<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/4
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<html>
<head>
    <title>getCookie</title>
</head>
<body>
<%
    Cookie cookie=null;
    Cookie[] cookies=null;
    /**
     * getCookies()方法返回的是一个Cookie数组
     */
    cookies=request.getCookies();
    if (cookies!=null) {
        out.print("<h2>查找Cookie名和值</h2>");
        for (int i = 0; i < cookies.length; i++) {
            cookie=cookies[i];
            out.print("参数名："+cookie.getName());
            out.print("<br/>");
            out.print("参数值:"+cookie.getValue());
            out.print("<br/>");
            out.print("--------------------------");
            out.print("<br/>");

        }
    }else{
        out.print("未发现Cookie值");
    }

%>
</body>
</html>
