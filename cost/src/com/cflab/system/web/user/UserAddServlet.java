package com.cflab.system.web.user;

import com.cflab.domain.User;
import com.cflab.system.service.IUserService;
import com.cflab.system.service.impl.UserServiceImpl;
import com.my.web.servlet.RequestBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/system/useradd")
public class UserAddServlet extends HttpServlet {
    /**
     * 实现接口添加用户方法
     */
    IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //1.接受参数，接受添加的用户信息,依赖jar包commy-web.jar中Servlet类
        //RequestBeanUtils 注意：from表单的name属性值必须与实体属性保持一致
        User user= RequestBeanUtils.requestToBean(req, User.class);
       // System.out.println(user);

        //2.业务逻辑层
        boolean flag = userService.addUser(user);

        //3.返回页面并返回参数
        if (flag) {
            req.setAttribute("tip", "添加用户成功");
        }else {
            req.setAttribute("tip", "添加用户失败");
        }
        req.getRequestDispatcher("/view/system/user/userinfo_add.jsp").forward(req, resp);


  }
}
