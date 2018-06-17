package com.cflab.expense.dao.Impl;

import com.cflab.domain.AuditRecord;
import com.cflab.domain.Detail;
import com.cflab.domain.Expense;
import com.cflab.expense.dao.IExpenseDao;
import com.cflab.utils.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpenseDaoImpl implements IExpenseDao {

    @Override
    public int expenseAdd(Expense expense) {
        //多条数据操作，采用事务
        Connection conn = null;
        try {
            conn = C3p0Util.getConn();
            QueryRunner qr = new QueryRunner();
            conn.setAutoCommit(false);

            /**一个页面要同时操作两张表
             *
             * 添加报销单信息,（expense）
             * now()是MySQL获取系统时间函数
             *
             * 数据从前端页面获取
             */
            String sql = "insert into t_expense(userId,expenseName,expenseTotal,expenseDate,expenseDesc,expenseState)" +
                    " values(?,?,?,now(),?,?) ";
//            返回修改的行数
           int rows = qr.update(conn, sql,expense.getUserId(),expense.getExpenseName(),
                   expense.getExpenseTotal(),expense.getExpenseDesc(),expense.getExpenseState());

            /**
             *
             * 循环添加多条报销单描述信息（expense_detail），
             * 该表要获取expense表的Id值
             *
             * 由于表单中无法获取，需要根据上表来查询
             */
            String detailSql = "insert into t_expense_detail(expenseId,costId,detailDesc,detailMoney)" +
                    " values(?,?,?,?)";
            //expenseId需要从t_expense中查询出来；
            String queryExpenseIdSql = "select last_insert_id()";

            int expenseId = qr.query(conn,queryExpenseIdSql, new ScalarHandler<BigInteger>()).intValue();
            Integer [] costIds = expense.getCostIds();
            String [] detailDescs = expense.getDetailDescs();
            float [] detailMoneys = expense.getDetailMoneys();

            for (int i = 0; i < costIds.length; i++) {
                //参数存放于表单对应的name属性名
                rows += qr.update(conn,detailSql ,expenseId,costIds[i],detailDescs[i],detailMoneys[i]);
            }

            conn.commit();//事务提交
            conn.setAutoCommit(true);//将事务设置原来的自动提交
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();//异常就进行事务回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int expenseDelete(Expense expense) {
        return 0;
    }

    @Override
    public int expenseUpdate(Expense expense) {
        //开启事务（多条语句）

        Connection conn = null;
        try {
            conn =  C3p0Util.getConn();
            conn.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();
            //修改报销信息
            /**
             * 进入修改界面，
             * 通过语句已经从前端将expenseState更改为“1”
             * 待审核状态
             */
            String sql = "update t_expense set expenseName=?,expenseTotal=?" +
                    ",expenseDesc=?,expenseState=?,expenseDate=now() where expenseId=?";
            int rows = qr.update(conn,sql ,expense.getExpenseName(),expense.getExpenseTotal(),
                    expense.getExpenseDesc(),expense.getExpenseState(),expense.getExpenseId());
            //更新报销明细（添加，修改，删除）
            /**
             * 该情况：①显删除数据库里该信息的相关内容,包括状态
             *         ②在重新更新添加进数据库
             */
            //①删除报销单相关报销明细内容
            String deleteSql = "delete  from t_expense_detail where expenseId=?";
            rows += qr.update(conn,deleteSql ,expense.getExpenseId());

            //②循环添加明细
            Integer [] costIds = expense.getCostIds();
            float [] detailMoneys = expense.getDetailMoneys();
            String [] detailDescs = expense.getDetailDescs();
            String addSql = "insert into t_expense_detail(costId,expenseId,detailDesc,detailMoney) " +
                    " values(?,?,?,?)";
            for (int i = 0; i < costIds.length; i++) {
               rows = qr.update(conn,addSql ,costIds[i],expense.getExpenseId(),detailDescs[i],detailMoneys[i] );
            }

            conn.commit();
            conn.setAutoCommit(true);
            return rows;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }


        return 0;
    }

    @Override
    /**
     * 查询报销单信息
     */
    public List<Expense> expenseQuery(Expense expense) {
//        1.sql
        String sql = "select te.* ,tu.userName  from t_expense te,t_user tu  where " +
                " tu.userId=te.userId";
//        2.多个条件查询判断条件
        List<Object> params = new ArrayList<Object>();
        if (expense.getExpenseId()!=0 && !expense.equals("")) {
            sql += " and te.expenseId=?";
            params.add(expense.getExpenseId());
        }
        if (expense.getUserId() != 0) {
            sql += " and te.userId = ?";
            params.add(expense.getUserId());
        }
        if (expense.getUserName()!=null && !expense.getUserName().equals("")) {
            sql += " and tu.userName like ?";
            params.add("%"+expense.getUserName()+"%");
        }
        if (expense.getExpenseState()!=null && !expense.getExpenseState().equals("")) {
            sql += " and te.expenseState=?";
            params.add(expense.getExpenseState());
        }
        if (expense.getExpenseStates()!=null&& expense.getExpenseStates().length>0) {
            //已知条件，不用加参数
            sql += " and te.expenseState in (";//添加引号将数值转换成字符串
            String states = Arrays.toString(expense.getExpenseStates());//[1,2,3,-1,-2]
            String state = states.substring(1,states.length()-1 );//截取大括号
            sql += state;
            sql += ")";
        }
        if (expense.getStartDate()!=null && !expense.getStartDate().equals("")) {
            sql += " and te.expenseDate >=?";
            params.add(expense.getStartDate());
        }
        if (expense.getEndDate()!=null && !expense.getEndDate().equals("")) {
            sql += " and te.expenseDate <=?";
            params.add(expense.getEndDate());
        }
        if (expense.getExpenseName()!=null && !expense.getExpenseName().equals("")) {
            sql += " and te.expenseName like ?";
            params.add("%"+expense.getExpenseName()+"%");
        }
//        3.执行sql
        try {
            return C3p0Util.queryList(sql, Expense.class,params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    /**
     * 通过编号查询报销明细列表
     */
    public List<Detail> expenseDetailQuery(int expenseId) {
//        1.sql
        String sql = "select * from t_expense_detail ted,t_cost tc where tc.costId=ted.costID" +
                " and expenseId=?";
//        2.执行SQL
        try {
           return   C3p0Util.queryList(sql,Detail.class ,expenseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    /**
     * 添加审核记录信息（t_audit_record）
     */
    public int auditExpense(AuditRecord auditRecord) {
        Connection conn = null;
        try {//操作多条数据
            conn =  C3p0Util.getConn();
            conn.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();
//        1.根据编号修改报销单状态
            String updateSql = "update t_expense set expenseState=? where expenseId=?";
            qr.update(conn, updateSql, auditRecord.getAuditState(),auditRecord.getExpenseId());
//        2.添加一条审核数据
            String addSql = "insert into t_audit_record(expenseId,userId,auditSugg,auditState,auditDate)" +
                    " values(?,?,?,?,now())";
            qr.update(conn,addSql ,auditRecord.getExpenseId(),auditRecord.getUserId(),auditRecord.getAuditSugg()
            ,auditRecord.getAuditState());
            conn.commit();
            conn.setAutoCommit(true);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return 0;
    }

    @Override
    /**
     * 根据报销单编号查询审核记录
     */
    public List<AuditRecord> queryAuditExpense(int expenseId) {
//        1.sql
        String sql = "select tar.*,tu.userName from t_audit_record tar ,t_user tu where " +
                " tu.userId=tar.userId and tar.expenseId=?";
//        2.执行SQL
        try {
           return C3p0Util.queryList(sql,AuditRecord.class ,expenseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
