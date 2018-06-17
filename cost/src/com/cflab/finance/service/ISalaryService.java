package com.cflab.finance.service;

import com.cflab.domain.SalaryChart;
import com.cflab.domain.SalaryRecord;

import java.util.List;

public interface ISalaryService {
    boolean addSalary(SalaryRecord salary);
    List<SalaryRecord> querySalary(SalaryRecord salary);
    List<SalaryChart> querySalaryChart();
}
