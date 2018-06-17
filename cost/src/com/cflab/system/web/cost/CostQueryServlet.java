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
import java.util.List;

@WebServlet("/system/costQuery")
public class CostQueryServlet extends HttpServlet {
    ICostService costService = new CostServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.接受参数，获取cost相应值
        Cost cost = RequestBeanUtils.requestToBean(req,Cost.class );
//        2.调用业务逻辑层
        List<Cost> costList = costService.queryCost(cost);
//        3.返回参数，显示数据，
        req.setAttribute("costList", costList);
       req.setAttribute("cost",cost );
//        4.跳转页面
        req.getRequestDispatcher("/view/system/cost/cost_list.jsp").forward(req,resp );
    }
}
