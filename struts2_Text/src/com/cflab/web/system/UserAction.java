package com.cflab.web.system;

import com.cflab.domain.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
    /**
     *定义私有成员变量
     * 设置set方法
     */
    private String name;
    private String password;
    /**
     *  接受user
     * 非模型驱动
     */
    private User user;



    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() throws Exception {
        System.out.println("userAction");
        return SUCCESS;
    }

    /**
     * 接受从地址栏输入的值（模型驱动方式可以返回User对象）
     * 模型驱动方式
     * HelloWorld_add_hello？name=myw&password=123
     * 不用模型驱动方式
     *HelloWorld_add_hello？user.name=myw&user.password=123
     * 并且不用实例化user
     * @return
     */
    public String addUser(){
        System.out.println("add");
        System.out.println("userName=" + name + "userPs=" + password + "User=" + user);
        return SUCCESS;
    }
    public String updateUser(){
        System.out.println("update");
        return SUCCESS;
    }
    public String deleteUser(){
        System.out.println("delete");
        return SUCCESS;
    }

}
