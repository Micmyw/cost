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

@WebServlet("/system/userUpdate")
public class UserUpdateServlet extends HttpServlet {
    IUserService userService = new UserServiceImpl();
    @Override
    /**
     * doGet()方法主要是回显要修改的用户信息
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受参数,根据用户编号查询信息，并回显到修改页面
        User user = RequestBeanUtils.requestToBean(req, User.class);
        //调用service
        List<User> userList = userService.queryUser(user);
        user = userList.get(0);
        //通过EL表达式回显参数
        req.setAttribute("user", user);
        //跳转页面
        req.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(req,resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受参数
        User user = RequestBeanUtils.requestToBean(req,User.class );
        //调用业务逻辑层Service
        boolean flag = userService.updateUser(user);
        System.out.println(flag);
        if (flag) {
            req.setAttribute("tip","用户修改成功" );
        }else {
            req.setAttribute("tip","用户修改失败" );
        }
        //跳转页面并回显信息
        req.setAttribute("user", user);
        req.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(req,resp );
    }
}
