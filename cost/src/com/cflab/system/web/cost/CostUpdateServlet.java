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

@WebServlet("/system/costUpdate")
public class CostUpdateServlet extends HttpServlet {
    ICostService costService = new CostServiceImpl();
    @Override
    /**
     * 先进行get类型的页面跳转，
     * 再通过post提交方式进行页面修改
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.接受传入文本框的value值参数
        Cost cost = RequestBeanUtils.requestToBean(req, Cost.class);
//        2.调用逻辑层回显要修改的用户信息；
        List<Cost> costList = costService.queryCost(cost);
        cost = costList.get(0);
        req.setAttribute("cost", cost);//通过EL表达式回显参数
//        3.跳转页面
        req.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(req,resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.接受传入文本框的value值参数
        Cost cost = RequestBeanUtils.requestToBean(req, Cost.class);
//        2.调用逻辑层
        boolean flag = costService.updateCost(cost);
        if (flag) {
            req.setAttribute("tip","信息修改成功" );
        }else {
            req.setAttribute("tip","信息修改失败" );
        }
//        3.页面跳转
        req.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(req, resp);
    }
}
