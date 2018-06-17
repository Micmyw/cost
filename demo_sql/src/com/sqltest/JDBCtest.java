package com.sqltest;

import java.sql.*;

public class JDBCtest {

    public static void test1( String deptno){

        try {
            //1.加载驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //2.获取连接
            String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
            String user="scott";//账号
            String password="123456"; //密码
            Connection conn = DriverManager.getConnection(url,user,password); // 连接

            //3.获取执行sql对象
            String sql="select * from dept where deptno = '"+deptno+"'";
            Statement sta=conn.createStatement();//表示数据库的状态


            //4.执行语句
            ResultSet res = sta.executeQuery(sql);

            /**
             *在第三步通过连接（conn）获得tatement接口对象时
             *
             * 一般采用PreparedStatement预编译的Statement接口
             *
             * 优点与不同：
             *        ①相比Statement可以防止sql注入问题
             *        sql语句条件不用 '"+deptno+"'连接，直接用“？”代替
             *        ②预编译是先传入sql语句，在获取对象时就传入
             *        而Statement是在执行语句这一步传入sql
             *
             * //1.加载驱动
              Class.forName("oracle.jdbc.driver.OracleDriver");

             //2.获取连接
             String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
             String user="scott";//账号
             String password="123456"; //密码
             Connection conn = DriverManager.getConnection(url,user,password); // 连接

             //3.获取执行sql对象
             String sql="select * from dept where deptno = ?";
             PreparedStatement sta=conn.prepareStatement(sql);
             sta.setString(1，"deptno");//设置条件


             //4.执行语句
             *ResultSet res = sta.executeQuery();
             */

            while (res.next()) {
                System.out.println("部门号"+res.getInt("DEPTNO")+"部门名称"+res.getString("DNAME"));
            }
            res.close();
            sta.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        test1("10");
    }

}
