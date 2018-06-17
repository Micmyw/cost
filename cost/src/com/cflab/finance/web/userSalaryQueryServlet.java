package com.cflab.finance.web;

import com.cflab.domain.User;
import com.cflab.system.service.IUserService;
import com.cflab.system.service.impl.UserServiceImpl;
import com.my.web.servlet.RequestBeanUtils;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/finance/queryUserSalary")
public class userSalaryQueryServlet  extends HttpServlet{
    IUserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受参数
        User user = RequestBeanUtils.requestToBean(req,User.class );

        //2.根据编号差底薪
        user = userService.queryUser(user).get(0);
        float basicSalary = user.getUserBasic();
        System.out.println(basicSalary);

        //返回
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("basicSalary",basicSalary);
        PrintWriter out = resp.getWriter();
        out.print(jsonObject);
        out.flush();
        out.close();


    }
}
