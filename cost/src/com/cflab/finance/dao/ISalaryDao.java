package com.cflab.finance.dao;

import com.cflab.domain.SalaryChart;
import com.cflab.domain.SalaryRecord;

import java.util.List;

public interface ISalaryDao {
    int addSalary(SalaryRecord salary);
    List<SalaryRecord> querySalary(SalaryRecord salary);
    List<SalaryChart> querySalaryChart();
}
