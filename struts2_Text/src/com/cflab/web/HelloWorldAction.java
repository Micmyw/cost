package com.cflab.web;

import com.cflab.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class HelloWorldAction extends ActionSupport implements ModelDriven<User>{
    /**
     *定义私有成员变量
     * 设置set方法
     */
    private String name;
    private String password;
/**
 *  接受user
 *  模型驱动，实现model接口，在重写的方法里返回user
 *  并实例化user
 */
    private User user = new User();



    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() throws Exception {
        System.out.println("hello struts2");
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
    public String add(){
        System.out.println("add");
        System.out.println("userName=" + name + "userPs=" + password + "User=" + user);
        return SUCCESS;
    }
    public String update(){
        System.out.println("update");
        return SUCCESS;
    }
    public String delete(){
        System.out.println("delete");
        return SUCCESS;
    }

    @Override
    public User getModel() {
        return user;
    }
}
