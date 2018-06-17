package com.cflab.finance.service.impl;

import com.cflab.domain.SalaryChart;
import com.cflab.domain.SalaryRecord;
import com.cflab.finance.dao.ISalaryDao;
import com.cflab.finance.dao.Impl.SalaryDao;
import com.cflab.finance.service.ISalaryService;

import java.util.List;

public class SalaryService implements ISalaryService {
    ISalaryDao salaryDao = new SalaryDao();
    @Override
    public boolean addSalary(SalaryRecord salary) {
        int rows = salaryDao.addSalary(salary);
        if (rows>0) {
            return true;
        }
        return false;
    }

    @Override
    public List<SalaryRecord> querySalary(SalaryRecord salary) {
        List<SalaryRecord> salaryRecordList = salaryDao.querySalary(salary);
        return salaryRecordList;
    }

    @Override
    public List<SalaryChart> querySalaryChart() {
        return salaryDao.querySalaryChart();
    }
}
