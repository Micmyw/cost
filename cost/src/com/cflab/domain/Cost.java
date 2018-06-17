package com.cflab.domain;

public class Cost {
    private int costId;
    private String costName;
    private String costDesc;
    private String costMark;
    //定义并返回复选框
    private String ck;
    //复选框个数对象集合，用于进行多条数据操作
    private Integer[] ids;

    public String getCk() {
        String ck;
        ck = "<input type='checkbox' value="+this.getCostId()+" name=ids>";
        return ck;
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public int getCostId() {
        return costId;
    }

    public void setCostId(int costId) {
        this.costId = costId;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public String getCostDesc() {
        return costDesc;
    }

    public void setCostDesc(String costDesc) {
        this.costDesc = costDesc;
    }

    public String getCostMark() {
        return costMark;
    }

    public void setCostMark(String costMark) {
        this.costMark = costMark;
    }
}
