<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/25
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--不适用模型驱动表单布局--%>
    <form action="http://localhost:8080/struts2_Text/system/HelloWorld_add_hello">
        <input name="user.name">
        <input name="user.password">
    </form>
    <%--使用模型驱动--%>
    <form action="http://localhost:8080/struts2_Text/system/HelloWorld_add_hello">
        <input name="name">
        <input name="password">
    </form>
</body>
</html>
