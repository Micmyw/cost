package com.cflab.expense.web;

import com.cflab.domain.AuditRecord;
import com.cflab.domain.Cost;
import com.cflab.domain.Detail;
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

@WebServlet("/expense/expenseUpdate")
public class ExpenseUpdateServlet extends HttpServlet{
    IExpenseService expenseService = new ExpenseServiceImpl();
    ICostService costService = new CostServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.根据报销单编号查询信息
        Expense expense = RequestBeanUtils.requestToBean(req,Expense.class );

        //2.调用service
        /**
         * 根据报销单编号查询报销单信息
         * 查询报销明细
         * 查询报销审核记录
         * 查询弹出框的允许报销的报销费用
         */
        expense = expenseService.expenseQuery(expense).get(0);//根据编号查询报销单
        //根据编号查报销明细
        List<Detail> detailList = expenseService.expenseDetailQuery(expense.getExpenseId());
        //根据编号查询审核记录
        List<AuditRecord> auditRecordList = expenseService.queryAuditExpense(expense.getExpenseId());
        //通过明细表里的costId查询可报销费用明细
        List<Cost> costList = costService.queryCost(new Cost());

//        3.回显数据
        req.setAttribute("expense",expense );
        req.setAttribute("detailList", detailList);
        req.setAttribute("auditRecordList", auditRecordList);
        req.setAttribute("costList", costList);

        req.getRequestDispatcher("/view/expense/expenses/expense_update.jsp").forward(req,resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受报销单信息和明细信息
        Expense expense = RequestBeanUtils.requestToBean(req, Expense.class);

//        2.service
        boolean flag = expenseService.expenseUpdate(expense);

//        3.回显参数
        if (flag) {
            req.setAttribute("tip","修改成功" );
        }else {
            req.setAttribute("tip","修改失败" );
        }
        //报销单信息，可查询出来，也可直接传入
        expenseService.expenseQuery(expense).get(0);
        //报销单明细
        List<Detail> detailList = expenseService.expenseDetailQuery(expense.getExpenseId());
        //审核记录
        List<AuditRecord> auditRecordList = expenseService.queryAuditExpense(expense.getExpenseId());
        //费用信息
        List<Cost> costList = costService.queryCost(new Cost());
//        3.回显数据
        req.setAttribute("expense",expense );
        req.setAttribute("detailList", detailList);
        req.setAttribute("auditRecordList", auditRecordList);
        req.setAttribute("costList", costList);

//        4.跳转页面
        req.getRequestDispatcher("/view/expense/expenses/expense_update.jsp").forward(req,resp );
    }
}
