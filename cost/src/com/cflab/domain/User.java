package com.cflab.domain;

public class User {
    /**
     *
     */
    private int userId;
    private int roleId;
    private String userName;
    private String userSex;
    private int userAge;
    private String userPhone;
    private String userAccount;
    private String userPwd;
    private float userBasic;
    private String userMark;
    //另加的用户角色名称，与前端匹配
    private String userRole;
    //定义并返回复选框
    private String ck;
    private Integer userIds[];
    //复选框个数对象集合，用于进行多条数据操作

    public String getCk() {
        //将列表设置为复选框，并获得相应列的ID值
        return ck="<input type='checkbox'value='"+this.getUserId()+ "' name='userIds'>";
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public Integer[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Integer[] userIds) {
        this.userIds = userIds;
    }

    public float getUserBasic() {
        return userBasic;
    }

    public void setUserBasic(float userBasic) {
        this.userBasic = userBasic;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserMark() {
        return userMark;
    }

    public void setUserMark(String userMark) {
        this.userMark = userMark;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userAge=" + userAge +
                ", userPhone='" + userPhone + '\'' +
                ", userAcount='" + userAccount + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userBasic=" + userBasic +
                ", userMark='" + userMark + '\'' +
                '}';

    }
}
