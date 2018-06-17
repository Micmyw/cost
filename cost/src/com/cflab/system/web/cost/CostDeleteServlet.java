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

@WebServlet("/system/costDeletes")
public class CostDeleteServlet extends HttpServlet {
    ICostService costService = new CostServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.接受参数，根据Id查询数据
        Cost cost = RequestBeanUtils.requestToBean(req,Cost.class );
//        2.调用逻辑层
        boolean flag = costService.deleteCost(cost);
//        3.设置参数
        if (flag) {
            req.setAttribute("tip","删除成功" );
        }else {
            req.setAttribute("tip","删除失败" );
        }
//        4.跳转到queryServlet操作，然后刷新显示页面
        req.getRequestDispatcher("/system/costQuery").forward(req,resp );
    }
}
