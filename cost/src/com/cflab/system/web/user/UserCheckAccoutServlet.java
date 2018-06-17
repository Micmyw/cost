package com.cflab.system.web.user;


import com.cflab.system.service.IUserService;
import com.cflab.system.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 */
@WebServlet("/system/checkUser")
public class UserCheckAccoutServlet extends HttpServlet {
    IUserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受参数，获取Ajax通过jq得到的账号值
        String userAccount = req.getParameter("userAccount");
        //2.调用Service
        boolean flag = userService.checkAccount(userAccount);
        System.out.println(flag);
        //返回结果
        JSONObject jsonObject = new JSONObject();
        if (flag) {
            //将该两个键值对存入json对象中。
            jsonObject.put("state", 0);
            jsonObject.put("tip", "该用户已经存在");
        }else {
            jsonObject.put("state", 1);
            jsonObject.put("tip", "该用户账号可以使用");
        }
        PrintWriter out = resp.getWriter();
        out.print(jsonObject);
        out.close();
    }
}
