<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/4
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传实例</title>
</head>
<body>
    <h1>文件上传实例</h1>
    <!--上传格式，提交地址，编码格式-->
    <form method="post" action="" enctype="multipart/form-data">
        选择一个文件：
        <input type="file" name="uploadFile"/>
        <br/>
        <br/>
        <input type="submit" value="上传">
    </form>
</body>
</html>
