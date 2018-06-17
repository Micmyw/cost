package com.cflab.finance.web;

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

@WebServlet("/finance/financeAudit")
public class FinanceAuditServlet extends HttpServlet{
    IExpenseService expenseService = new ExpenseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       跳转财务审核页面
//        1.接受报销单编号
        Expense expense = RequestBeanUtils.requestToBean(req,Expense.class );

//        2.service
//        根据编号查询报销单信息,有且只有一个，也可不用查直接传入
        expense = expenseService.expenseQuery(expense).get(0);
//        根据编号查询报销明细
        List<Detail> detailList = expenseService.expenseDetailQuery(expense.getExpenseId());
//        根据编号查询审核记录
        List<AuditRecord> auditRecordList = expenseService.queryAuditExpense(expense.getExpenseId());

//        3.回显数据
        req.setAttribute("expense",expense );
        req.setAttribute("detailList", detailList);
        req.setAttribute("auditRecordList", auditRecordList);

//        4.跳转审核页面
        req.getRequestDispatcher("/view/finance/audit/financeaudit_audit.jsp").forward(req,resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //保存审核
        //接受审核记录对象
        AuditRecord record = RequestBeanUtils.requestToBean(req,AuditRecord.class );

        boolean flag = expenseService.auditExpense(record);
        if (flag) {
            req.setAttribute("tip","审核成功" );
        }else {
            req.setAttribute("tip","审核失败" );
        }
        //回显报销单信息
        Expense expense = new Expense();
        expense.setExpenseId(record.getExpenseId());//或得并设置该编号Id
        expense = expenseService.expenseQuery(expense).get(0);
        //        根据编号查询报销明细
        List<Detail> detailList = expenseService.expenseDetailQuery(expense.getExpenseId());
//        根据编号查询审核记录
        List<AuditRecord> auditRecordList = expenseService.queryAuditExpense(expense.getExpenseId());

//        3.回显数据
        req.setAttribute("expense",expense );
        req.setAttribute("detailList", detailList);
        req.setAttribute("auditRecordList", auditRecordList);


        //跳转保存页面
        req.getRequestDispatcher("/view/finance/audit/financeaudit_audit.jsp").forward(req,resp );
    }
}
