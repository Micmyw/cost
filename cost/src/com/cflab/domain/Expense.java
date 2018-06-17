package com.cflab.domain;

import java.util.Arrays;
import java.util.Date;

public class Expense {
    //报销单属性
    private int expenseId;
    private int userId;
    private String expenseName;
    private float expenseTotal;
    private Date expenseDate;
    private String expenseDesc;
    private String expenseState;

    //多个报销单明细信息,下面显示框信息（报销明细表）
    private Integer [] costIds;
    private float [] detailMoneys;
    private String [] detailDescs;

    //经理审批查询报销单信息
    private String userName;
    private Date startDate;
    private Date endDate;

    //多个状态数组(审核页面显示多种不同状态的审核记录)
    private Integer[] expenseStates;

    //报销状态设置
    private String expenseStateStr;

    public String getExpenseStateStr() {
        return expenseStateStr;
    }

    public void setExpenseStateStr(String expenseStateStr) {
        this.expenseStateStr = expenseStateStr;
    }

    //我的报销单提交操作
    private String operate;

    public String getOperate() {
        if (expenseState.equals("0")||expenseState.equals("-1")||expenseState.equals("-2")) {
            operate ="<a href = 'expense/expenseUpdate?expenseId="+this.getExpenseId()+"'>修改</a>";
        }else {
            operate ="<a href = 'expense/expenseDetailQuery?expenseId="+this.getExpenseId()+"'>查看详情1</a>";
            System.out.println(expenseState);
        }
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }


    public Integer[] getExpenseStates() {
        return expenseStates;
    }
    public void setExpenseStates(Integer[] expenseStates) {
        this.expenseStates = expenseStates;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public float getExpenseTotal() {
        return expenseTotal;
    }

    public void setExpenseTotal(float expenseTotal) {
        this.expenseTotal = expenseTotal;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getExpenseDesc() {
        return expenseDesc;
    }

    public void setExpenseDesc(String expenseDesc) {
        this.expenseDesc = expenseDesc;
    }

    public String getExpenseState() {
        return expenseState;
    }

    public void setExpenseState(String expenseState) {
        this.expenseState = expenseState;
    }

    public Integer[] getCostIds() {
        return costIds;
    }

    public void setCostIds(Integer[] costIds) {
        this.costIds = costIds;
    }

    public float[] getDetailMoneys() {
        return detailMoneys;
    }

    public void setDetailMoneys(float[] detailMoneys) {
        this.detailMoneys = detailMoneys;
    }

    public String[] getDetailDescs() {
        return detailDescs;
    }

    public void setDetailDescs(String[] detailDescs) {
        this.detailDescs = detailDescs;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", userId=" + userId +
                ", expenseName='" + expenseName + '\'' +
                ", expenseTotal=" + expenseTotal +
                ", expenseDate=" + expenseDate +
                ", expenseDesc='" + expenseDesc + '\'' +
                ", expenseState='" + expenseState + '\'' +
                ", costIds=" + Arrays.toString(costIds) +
                ", detailMoneys=" + Arrays.toString(detailMoneys) +
                ", detailDescs=" + Arrays.toString(detailDescs) +
                ", userName='" + userName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }


}
