package com.cflab.expense.web;

import com.cflab.domain.Cost;
import com.cflab.domain.Expense;
import com.cflab.expense.service.IExpenseService;
import com.cflab.expense.service.impl.ExpenseServiceImpl;
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
@WebServlet("/expense/expenseAdd")
/**
 * 报销单状态：0 原始状态   1 待经理审核  2经理审核通过
 *             3 财务审核通过  -1 经理审核未通过  -2 财务审核未通过
 */
public class ExpenseAddServlet extends HttpServlet {
    ICostService costService = new CostServiceImpl();
    IExpenseService expenseService = new ExpenseServiceImpl();
    @Override
    /**
     * 跳转报销页面
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.直接查询所有费用对象
//      也可以通过传入cost对象查询
        List<Cost> costList = costService.queryCost(new Cost());
//        2.在弹框里回显可报销信息
        req.setAttribute("costList", costList);
        req.getRequestDispatcher("/view/expense/expenses/expense_add.jsp").forward(req,resp );
    }

    @Override
    /**
     * 提交保存报销明细页面
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.接受参数
        Expense expense = RequestBeanUtils.requestToBean(req,Expense.class );
        System.out.println(expense);
//        2.调用逻辑层
        boolean flag = expenseService.expenseAdd(expense);
        if (flag) {
            req.setAttribute("tip", "提交成功");
        }else {
            req.setAttribute("tip", "提交失败");
        }
//        3.刷新回显跳转界面
        req.getRequestDispatcher("/view/expense/expenses/expense_add.jsp").forward(req, resp);

    }
}
