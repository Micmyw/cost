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
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/system/updateMyUsers")
public class updateMyUserServlet extends HttpServlet {
    IUserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.接受参数
        User user = RequestBeanUtils.requestToBean(req, User.class);
//        2.调用逻辑层
        boolean flag = userService.updateUser(user);
//        3.如果修改成功，保存后可以回显修改后所有值包括从session中获取的不能修改的值(账号等)
        if (flag) {
            req.setAttribute("tip","用户信息修改成功" );
            HttpSession session = req.getSession();
//            更新session值
            session.setAttribute("userInfo", user);
        }else {
            req.setAttribute("tip","用户信息修改失败" );
        }
        req.getRequestDispatcher("/view/system/user/userinfo_show.jsp").forward(req,resp );

    }
}
