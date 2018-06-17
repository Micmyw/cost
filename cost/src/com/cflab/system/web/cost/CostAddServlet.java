package com.cflab.system.web.cost;

import com.cflab.domain.Cost;
import com.cflab.system.service.ICostService;
import com.cflab.system.service.impl.CostServiceImpl;
import com.my.web.servlet.RequestBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/system/costAdd")
public class CostAddServlet extends HttpServlet {
    ICostService costService = new CostServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.接受表单参数
        Cost cost = RequestBeanUtils.requestToBean(req,Cost.class );
//        2.调用service，业务逻辑层；
        boolean flag = costService.addCost(cost);
//        3.跳转页面返回参数
        if (flag) {
            req.setAttribute("tip","添加成功" );
        }else {
            req.setAttribute("tip", "添加失败");
        }
        req.getRequestDispatcher("/view/system/cost/cost_add.jsp").forward(req,resp );

    }
}
