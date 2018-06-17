package com.cflab.system.dao.Impl;

import com.cflab.domain.Menu;
import com.cflab.domain.User;
import com.cflab.system.dao.IUserDao;
import com.cflab.utils.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public int addUser(User user) {
        //执行添加用户的SQL语句
        //1.sql语句
        String sql = "insert into t_user( roleId,userName,userSex,userAge,userPhone,userAccount,userPwd,userBasic,userMark)"
                +"values(?,?,?,?,?,?,?,?,?)";

        //2.执行SQL
        try {
            //返回受影响的行数
            int  rows = C3p0Util.update(sql,user.getRoleId(),user.getUserName(),user.getUserSex(),user.getUserAge(),user.getUserPhone()
                    ,user.getUserAccount(),user.getUserPwd(),user.getUserBasic(),"0");
            // System.out.println("成功");
            return rows;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int checkAccount(String userAccount) {
        //sql语句
        String sql = "select count(userAccount) from t_user where userAccount = ?";
        //执行SQL
        try {
//            System.out.println(userAccount);
//            System.out.println(C3p0Util.queryNumber(sql, long.class, userAccount).intValue());
            return C3p0Util.queryNumber(sql, long.class,userAccount).intValue();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    /**
     * 登录同时获取用户信息
     */
    public User doLogin(User user) {
        //1.sql,同时将用户角色名字得到
        String sql = "select tu.*,tr.roleName as userRole from t_user tu,t_role tr where tr.roleId=tu.roleId and userAccount=? and userPwd=? and userMark=0";

        //执行SQL
        try {
            return C3p0Util.queryOne(sql, User.class,user.getUserAccount(),user.getUserPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Menu> queryUserMenus(int roleId) {
        //1.sql
        String sql = "select tm.* from t_menu tm , t_role_menu trm where tm.menuId=trm.menuId AND  trm.roleId=?;";

        //执行SQL
        try {
            return C3p0Util.queryList(sql, Menu.class, roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> queryUser(User user) {
        //1.sql
        StringBuffer sb = new StringBuffer("select tu.*,tr.roleName as userRole from t_user tu " +
                "inner join t_role tr on tu.roleId=tr.roleId where userMark=0");

        //2.通过拼接字符串来查询不定项条件
        List<Object> params = new ArrayList();

        if (user.getUserId()!=0) {
            sb.append(" and tu.userId=?");
            params.add(user.getUserId());
        }
        if (user.getUserName()!=null && ! user.getUserName().equals("")) {
            sb.append(" and tu.userName like ?");
            params.add("%"+user.getUserName()+"%");
        }

        //3.执行SQL
        try {
            //将sb转换成string类型，params不定数list集合，转换成数组类型
            return C3p0Util.queryList(sb.toString(), User.class, params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return null;
    }

    @Override
    public int updateUser(User user) {
//        1.SQL语句
        String sql = "update t_user set userName=?,userSex=?,userAge=?,RoleId=?,userPhone=?,userPwd=?," +
                "userBasic=? where userId=?";
//        2.执行SQL
        try {
           return C3p0Util.update(sql,user.getUserName(),user.getUserSex(),user.getUserAge(),user.getRoleId()
            ,user.getUserPhone(),user.getUserPwd(),user.getUserBasic(),user.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteUser(User user) {
//        1.SQL语句
        String sql = "update t_user set userMark=1 where userId=?";

//        2.设计多条数据操作时,yao要用事物
        Connection conn = null;
        try {
            conn = C3p0Util.getConn();//获取连接

            conn.setAutoCommit(false);//关闭事务自动提交
            QueryRunner qr = new QueryRunner();//获取事务操作对象

            //循环执行修改，由name属性获得value属性值
            Integer[] userIds = user.getUserIds();//用户多个ID的集合，返回多个name属性=userIds的input表单对象个数，
            int rows = 0;
            for (int i = 0; i < userIds.length; i++) {
                rows+=qr.update(conn,sql ,userIds[i] );
                //System.out.println(userIds[i]);
            }
            //提交事务
            conn.commit();
            conn.setAutoCommit(true);
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();//异常则事务回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return 0;
    }
}

