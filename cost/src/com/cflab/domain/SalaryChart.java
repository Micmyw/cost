package com.cflab.domain;

import java.util.Date;

/**
 * 薪资报表
 */
public class SalaryChart {
    private Date salaryMonth;
    private float salaryBasic;
    private float salaryComm;

    private float salaryBasicTotal;
    private float salaryCommTotal;

    public float getSalaryBasicTotal() {
        return salaryBasicTotal;
    }

    public void setSalaryBasicTotal(float salaryBasicTotal) {
        this.salaryBasicTotal = salaryBasicTotal;
    }

    public float getSalaryCommTotal() {
        return salaryCommTotal;
    }

    public void setSalaryCommTotal(float salaryCommTotal) {
        this.salaryCommTotal = salaryCommTotal;
    }

    public Date getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(Date salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public float getSalaryBasic() {
        return salaryBasic;
    }

    public void setSalaryBasic(float salaryBasic) {
        this.salaryBasic = salaryBasic;
    }

    public float getSalaryComm() {
        return salaryComm;
    }

    public void setSalaryComm(float salaryComm) {
        this.salaryComm = salaryComm;
    }
}
