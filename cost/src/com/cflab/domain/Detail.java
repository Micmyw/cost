package com.cflab.domain;

public class Detail {
    private int detailId;
    private int expenseId;
    private int costId;
    private String detailMoney;
    private String detailDesc;

    //获取费用名称
    private String costName;

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getCostId() {
        return costId;
    }

    public void setCostId(int costId) {
        this.costId = costId;
    }

    public String getDetailMoney() {
        return detailMoney;
    }

    public void setDetailMoney(String detailMoney) {
        this.detailMoney = detailMoney;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }
}
