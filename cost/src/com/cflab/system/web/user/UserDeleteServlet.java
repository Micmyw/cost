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

@WebServlet("/system/userDelete")
public class UserDeleteServlet extends HttpServlet {
    IUserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req,resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.接受参数,接受需要删除的多个用户的编号
        User user = RequestBeanUtils.requestToBean(req,User.class );
//        2.调用service
        boolean flag = userService.deleteUser(user);
//        3.刷新页面，返回参数
        if (flag) {
            req.setAttribute("tip","删除成功" );
        }else {
            req.setAttribute("tip","删除失败" );
        }
        /**
         * 返回查询的页面(即进行刷新操作)
         * 必须通queryServlet进行页面刷新跳转
         * 不能直接跳转到list界面
         * 否则显示为空
         */
        req.getRequestDispatcher("/system/userQuery").forward(req,resp );
    }
}
