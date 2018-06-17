package com.cflab.expense.web;

import com.cflab.domain.AuditRecord;
import com.cflab.domain.Detail;
import com.cflab.domain.Expense;
import com.cflab.expense.service.IExpenseService;
import com.cflab.expense.service.impl.ExpenseServiceImpl;
import com.my.web.servlet.RequestBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/expense/expenseDetailQuery")
public class ExpenseDetailQueryServlet extends HttpServlet {
    IExpenseService expenseService = new ExpenseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.跳转审核页面，根据报销单信息查询报销明细列表
        Expense expense = RequestBeanUtils.requestToSimpleBean(req ,Expense.class );
        //2.①.根据报销单id查询报销单信息
        expense = expenseService.expenseQuery(expense).get(0);

        //②.根据报销单编号查询报销明细（多条明细）
        List<Detail> detailList = expenseService.expenseDetailQuery(expense.getExpenseId());

        //③根据编号查询审核记录（多哦条记录）
        List<AuditRecord> auditRecordList = expenseService.queryAuditExpense(expense.getExpenseId());
        //3.回显数据
        req.setAttribute("expense", expense);//回显报销单信息
        req.setAttribute("detailList", detailList);
        req.setAttribute("auditRecordList", auditRecordList);
        //4.跳转页面

        req.getRequestDispatcher("/view/expense/expenses/expense_show.jsp").forward(req,resp );
    }
}
