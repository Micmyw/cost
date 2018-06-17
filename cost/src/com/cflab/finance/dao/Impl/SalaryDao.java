package com.cflab.finance.dao.Impl;

import com.cflab.domain.SalaryChart;
import com.cflab.domain.SalaryRecord;
import com.cflab.finance.dao.ISalaryDao;
import com.cflab.utils.C3p0Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDao implements ISalaryDao {
    @Override
    public int addSalary(SalaryRecord salary) {
//        1.sql,salaryDate发放时间直接是当前时间
        String sql = "insert into t_salary_record(userId,salaryMonth,salaryDate,salaryBasic,salaryComm) " +
                " values(?,?,now(),?,?)";
//        2.执行
        try {
            return C3p0Util.update(sql,salary.getUserId(),salary.getSalaryMonth()
                    ,salary.getSalaryBasic(),salary.getSalaryComm());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<SalaryRecord> querySalary(SalaryRecord salary) {
//        1.sql
        StringBuffer sb =  new StringBuffer("select tsr.*,tu.userName from t_salary_record tsr,t_user tu " +
                " where tu.userId=tsr.userId");

        List<Object> paramas = new ArrayList<>();
        if (salary.getUserId()!=0) {
            sb.append(" and tsr.userId=?");
            paramas.add(salary.getUserId());
        }
        if (salary.getUserName()!=null && !salary.getUserName().equals("")) {
            sb.append(" and tu.userName like ?");
            paramas.add("%"+salary.getUserName()+"%");
        }
        if (salary.getSalaryMonth()!=null) {
            sb.append(" and tsr.salaryMonth=?");
            paramas.add(salary.getSalaryMonth());
        }
        try {
            return C3p0Util.queryList(sb.toString(),SalaryRecord.class ,paramas.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    /**
     * 查询每个月份的总的薪资和总的提成,
     * 根据发放月份分组，
     * 并且两个SUM必须给一个别名
     * 不然映射不了c3po里面
     */
    public List<SalaryChart> querySalaryChart() {
//        1.SQL
        String sql = "select salaryMonth,SUM(salaryBasic) as salaryBasicTotal,SUM(salaryComm)" +
                " as salaryCommTotal from t_salary_record group by salaryMonth";
        try {
            return C3p0Util.queryList(sql, SalaryChart.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
