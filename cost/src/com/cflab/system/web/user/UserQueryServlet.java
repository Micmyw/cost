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
import java.util.List;

@WebServlet("/system/userQuery")
public class UserQueryServlet extends HttpServlet {
    IUserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受参数,获取页面表单参数值
        User user = RequestBeanUtils.requestToBean(req, User.class);
        //2.调用service
        List<User> userList =  userService.queryUser(user);
        //3.通过界面器返回参数到界面
        req.setAttribute("userList", userList);
        req.setAttribute("user", user);
        //4.跳转页面

        req.getRequestDispatcher("/view/system/user/userinfo_list.jsp").forward(req, resp);

    }
}
