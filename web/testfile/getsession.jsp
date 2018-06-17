<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/4
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //获取session创建的时间
    Date createTime = new Date(session.getCreationTime());
    //获取最后访问页面的时间
    Date lastAccessTime = new Date(session.getLastAccessedTime());

    Integer vistCount = new Integer(0);
    String vistKey = new String("vistCount");
    String useIDKey = new String("useID");
    String useID = new String("第一个用户");

    //检查网页是否有新的访问用户
    if (session.isNew()) {
        //使用指定名称和值产生一个对象并绑定到session中
        session.setAttribute(vistKey,vistCount);
        session.setAttribute(useIDKey,useID);
    }else{
        //获得session指定名称的对象,访问次数
        vistCount= (Integer) session.getAttribute(vistKey);
        vistCount+=1;
        //通过对象名称得到绑定在session上的对象
        useID= (String) session.getAttribute(useIDKey);
        //再次访问的时候重新设置访问次数vistCount
        session.setAttribute(vistKey,vistCount);
    }
%>
<html>
<head>
    <title>Session跟踪</title>
</head>
<body>
    <h1>Session跟踪</h1>
    <table border="1" align="center" width="100%">
        <tr bgcolor="#6495ed">
            <th>session信息</th>
            <th>session值</th>
        </tr>
        <tr bgcolor="#f0f8ff">
            <td>id</td>
            <td><%out.print(session.getId());%></td>
        </tr>
        <tr bgcolor="#7fffd4">
            <td>创建时间</td>
            <td><%out.print(createTime);%></td>
        </tr>
        <tr bgcolor="#dc143c">
            <td>最后访问时间</td>
            <td><%out.print(lastAccessTime);%></td>
        </tr>
        <tr bgcolor="#8a2be2">
            <td>用户id</td>
            <td><%out.print(useID);%></td>
        </tr>
        <tr>
            <td>访问次数</td>
            <td><%out.print(vistCount);%></td>
        </tr>
    </table>
</body>
</html>
