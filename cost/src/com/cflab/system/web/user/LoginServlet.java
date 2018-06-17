package com.cflab.system.web.user;

import com.cflab.domain.Menu;
import com.cflab.domain.SalaryChart;
import com.cflab.domain.User;
import com.cflab.finance.service.ISalaryService;
import com.cflab.finance.service.impl.SalaryService;
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
import java.util.List;

@WebServlet("/system/doLogin")
public class LoginServlet extends HttpServlet {
    IUserService userService = new UserServiceImpl();
    ISalaryService salaryService = new SalaryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受用户登录信息
        //省略req.getParameter()获取表单数据方法
        User user = RequestBeanUtils.requestToBean(req, User.class);
        /**
         * 该步骤是为了在首页中强行刷新页面
         * 不会调转到登录页面
         * 强行刷新首页面，在重新登录时不会携带账号和密码
         * 通过session来给loginUser赋值
         *
         * 若是第一次登陆或是重启服务器，
         * 会销毁以前的session
         * 下面赋值无效
         */
        HttpSession session = req.getSession();
        //用未销毁session给loginUser赋值
        User loginUser = (User) session.getAttribute("userInfo");
        if (user.getUserAccount()!=null && !user.getUserAccount().equals("")&&
                user.getUserPwd()!=null && !user.getUserPwd().equals("")) {
            //2.调用service
             loginUser = userService.doLogin(user);
        }
        //3.返回
        if (loginUser==null) {
             req.setAttribute("tip", "用户名或密码错误");
            /**
             * 返回错误信息并跳转登录页面
             */
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        }else {

            /**
             *  1.存取登录信息
             */
            session.setAttribute("userInfo", loginUser);

            /**
             * 2.根据用户查询用户的所有权限（功能表单）
             */
            List<Menu> menuList= userService.queryUserMenus(loginUser.getRoleId());
            session.setAttribute("menuList", menuList);

            /**
             *  3.查询薪资报表信息
             */
            List<SalaryChart> salaryChartList = salaryService.querySalaryChart();
            session.setAttribute("salaryChartList", salaryChartList);

            //跳转首页
            req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
        }

    }
}
