package com.cflab.domain;

import java.util.Date;

public class AuditRecord {
    private int auditId;
    private int userId;
    private int expenseId;
    private String auditSugg;
    private String auditState;
    private Date auditDate;

    private String auditStateStr;

    public String getAuditStateStr() {
        return auditStateStr;
    }

    public void setAuditStateStr(String auditStatestr) {
        this.auditStateStr = auditStatestr;
    }

    //审核记录需要回显审核人名
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getAuditSugg() {
        return auditSugg;
    }

    public void setAuditSugg(String auditSugg) {
        this.auditSugg = auditSugg;
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }
}
