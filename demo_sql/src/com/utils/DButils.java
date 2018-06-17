package com.utils;



import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * 工具包类，存放工程项目的工具类
 */
public class DButils {
    //数据库驱动
    public static final String driverClass ;
    //URL
    public static final String url ;
    //账号
    public static final String user ;
    //密码
    public static final String password ;
    /**
     * 静态代码块,初始化执行，
     * 通过读取properties属性文件
     * 来获取加载资源
     *
     * 方便后期维护修改，体现了封装性
     */

    static {
        Properties pro = null;

        try {
            //读取属性文件，使用java中的properties对象
            InputStream in = new FileInputStream(new File("demo_sql\\src\\jdbc.properties"));
            pro = new Properties();
            //加载资源文件
            pro.load(in);
        }catch (Exception e){
            e.printStackTrace();
        }
        driverClass = pro.getProperty("driverClass");
        url =  pro.getProperty("url");
        user = pro.getProperty("user");
        password = pro.getProperty("password");
    }

    /**
     * 获取数据库连接对象方法
     * @return
     */
    public static Connection getConnection(){
        try {
            //加载驱动
            System.out.println(driverClass);
            System.out.println(url);
            System.out.println(user);
            System.out.println(password);
            Class.forName(driverClass);
            Connection con = DriverManager.getConnection(url, user , password);
            System.out.println(con);
            return con;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭资源
     * @param res sql返回的结果集
     * @param pre 预准备状态
     * @param con sql连接
     */
    public static  void close(ResultSet res, PreparedStatement pre,Connection con){

        if (res!=null){
            try{
                res.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (pre!=null){
            try{
                pre.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (con!=null){
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
