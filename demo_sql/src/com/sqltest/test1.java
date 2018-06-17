package com.sqltest;

import com.utils.DButils;

import java.sql.*;

public class test1 {
    public static void main(String [] args) throws SQLException, ClassNotFoundException {
        //Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DButils.getConnection();
        PreparedStatement pre=null;
        ResultSet res = null;
        try {
            //获取数据库连接
           // con=DButils.getConnection();
            //获取实例
            String sql = "select * from dept";
            pre=con.prepareStatement(sql);
            //执行sql返回结果集
            res = pre.executeQuery();
            while (res.next()) {
                System.out.println("部门号"+res.getInt("DEPTNO")+"部门名称"+res.getString("DNAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            DButils.close(res, pre, con);
        }

    }
}
