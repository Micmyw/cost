package com.cflab.domain;

import java.util.Date;

public class SalaryRecord {
    private  int salaryId;
    private int  userId;
    private Date salaryMonth;
    private Date salaryDate;
    private float salaryBasic;
    private float salaryComm;

    private String userName;

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SalaryRecord{" +
                "salaryId=" + salaryId +
                ", userId=" + userId +
                ", salaryMonth=" + salaryMonth +
                ", salaryDate=" + salaryDate +
                ", salaryBasic=" + salaryBasic +
                ", salaryComm=" + salaryComm +
                '}';
    }

    public Date getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(Date salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
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
