<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/3
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! int day=1;%>
<%! int fontsize;%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <h1>hello</h1>
    <%
      out.println("Your IP address is  " + request.getRemoteAddr());
    %>
    <h2>今天日期：<%=(new java.util.Date()).toLocaleString()%></h2>
    <%if(day==0||day==7){%>
    <p>今天周末</p>
    <%}else{%>
    <p>今天不是周末</p>
    <%}%>
  <h1>已更改好了+6</h1>
  <%for(fontsize=0;fontsize<=3;fontsize++){%>
     <font size="<%=fontsize%>" color="#6495ed">
       不同字体大小
     </font><br>
  <%}%>
  <h2>调用bean</h2>
  <jsp:forward page="date.jsp"></jsp:forward>
  </body>
</html>
