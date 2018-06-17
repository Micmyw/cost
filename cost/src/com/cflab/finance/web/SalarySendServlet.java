package com.cflab.finance.web;

import com.cflab.domain.SalaryRecord;
import com.cflab.domain.User;
import com.cflab.finance.service.ISalaryService;
import com.cflab.finance.service.impl.SalaryService;
import com.cflab.system.service.IUserService;
import com.cflab.system.service.impl.UserServiceImpl;
import com.cflab.utils.MyDateconvertUtil;
import com.my.web.servlet.RequestBeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/finance/salarySend")
public class SalarySendServlet extends HttpServlet {
    IUserService userService = new UserServiceImpl();
    ISalaryService salaryService = new SalaryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //跳转薪资发放页面

        //回显公司所有员工,没有条件就会查询所有的用户
        User user = new User();
        List<User> userList = userService.queryUser(user);
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("/view/finance/cost/salarypayment_add.jsp").forward(req,resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受薪资记录
        ConvertUtils.register(new MyDateconvertUtil("yyyy-MM"), Date.class);

        SalaryRecord salary = RequestBeanUtils.requestToSimpleBean(req,SalaryRecord.class );


        //2.调用逻辑层
        boolean flag = salaryService.addSalary(salary);
        if (flag) {
            req.setAttribute("tip","薪资发放成功" );
        }else {
            req.setAttribute("tip","薪资发放失败" );
        }
        List<User> userList = userService.queryUser(new User());
        req.setAttribute("userList",userList );
        req.getRequestDispatcher("/view/finance/cost/salarypayment_add.jsp").forward(req,resp );

    }
}
