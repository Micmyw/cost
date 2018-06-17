<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.GregorianCalendar" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/3
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>show info</title>
</head>
<body>
    <h2>Http实例信息show 11</h2>
    <table width="100%" border="1" align="center" cellpadding="0">
        <tr bgcolor="#add8e6">
            <th>Hearder  Name</th>
            <th>Hearder  values</th>
        </tr>
        <%
            /**
             *  读取头http部信息，enumeration是一个枚举类
             */
            Enumeration headerName=request.getHeaderNames();
            //判断是否还有元素
            while (headerName.hasMoreElements()){
                    //获取每一个参数名字
                    String paraname= (String) headerName.nextElement();
                    out.print("<tr><td>"+paraname+"</td>\n");
                    //返回指定request信息头的值
                    String paraValues=request.getHeader(paraname);
                    out.print("<td>"+paraValues+"</td><tr>\n");
            }
        %>
    </table>
    <h3>自动刷新</h3>
    <h4>刷新+2</h4>
    <%
        response.setIntHeader("Refresh",5);
        Calendar calendar=new GregorianCalendar();
        String am_pm;
        int hour=calendar.get(Calendar.HOUR);
        int minute=calendar.get(Calendar.MINUTE);
        int second=calendar.get(Calendar.SECOND);
        if(calendar.get(Calendar.AM_PM)==0){
            am_pm="AM";
        }else
            am_pm= "PM";
        String CT=hour+":"+minute+":"+second+"   "+am_pm;
        out.print("当前时间："+CT+"\n");
    %>
</body>
</html>
