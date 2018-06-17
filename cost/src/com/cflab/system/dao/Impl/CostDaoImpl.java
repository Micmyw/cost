package com.cflab.system.dao.Impl;

import com.cflab.domain.Cost;
import com.cflab.system.dao.ICostDao;
import com.cflab.utils.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/system/costDelete")
public class CostDaoImpl implements ICostDao {
    @Override
    public int addCost(Cost cost) {
//        1.sql语句
        String sql = "insert into t_cost(costId,costName,costDesc,costMark) values(?,?,?,?)";
//        2.执行SQL
        try {
            int rows = C3p0Util.update(sql,cost.getCostId(),cost.getCostName(),cost.getCostDesc(),"0");
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteCost(Cost cost) {
//        1.sql
        String sql = "update t_cost set costMark=1 where costId=?";
//        2.执行SQL(多条数据操作，要使用事务)
        Connection conn = null;
        try {
            conn = C3p0Util.getConn();//获取连接
            conn.setAutoCommit(false);//关闭事务自动提交
            QueryRunner qr = new QueryRunner();//获取事务对象
            Integer[] ids = cost.getIds();//获取多个input复选框的id数组
            int rows = 0;
            for (int i = 0; i < ids.length; i++) {
                 rows += qr.update(conn,sql,ids[i] );
            }
            //提交事务
            conn.commit();
            //还原自动提交
            conn.setAutoCommit(true);
           return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                //错误就进行事务回滚
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }


        return 0;
    }

    @Override
    public int updateCost(Cost cost) {
//        1.sql
        String sql = "update t_cost set costName=?,costDesc=? where costId=?";
//        2.在执行sql
        try {
            return C3p0Util.update(sql,cost.getCostName(),cost.getCostDesc(),cost.getCostId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Cost> queryCost(Cost cost) {
//        1.sql
        StringBuffer sb = new StringBuffer("select * from t_cost where costMark=0");
        List<Object> params = new ArrayList<Object>();
//        2.语句拼接,查询不定选项
        if (cost.getCostId()!=0) {
            sb.append(" and costId=?");
            params.add(cost.getCostId());
        }
        if (cost.getCostName()!=null && !cost.getCostName().equals("")) {
            sb.append(" and costName like ?");
            params.add("%"+cost.getCostName()+"%");
        }
//        3.执行SQL
        try {
            return C3p0Util.queryList(sb.toString(),Cost.class ,params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
