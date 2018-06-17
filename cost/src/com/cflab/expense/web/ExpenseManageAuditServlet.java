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

@WebServlet("/expense/expenseManageAudit")
public class ExpenseManageAuditServlet extends HttpServlet{
    IExpenseService expenseService = new ExpenseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        req.getRequestDispatcher("/view/expense/audit/expense_audit.jsp").forward(req,resp );

    }

    @Override
    /**
     * 要回显三张表记录（报销单，报销明细表，审核记录）
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.接受审核记录
        AuditRecord auditRecord = RequestBeanUtils.requestToBean(req, AuditRecord.class);
//        2.调用service
        boolean flag = expenseService.auditExpense(auditRecord);
        if (flag) {
            req.setAttribute("tip","审核成功" );
        }else {
            req.setAttribute("tip","审核失败" );
        }
//        3.回显信息
        /**①回显报销单信息
         * 通过审核记录来获取报销单编号
         * 然后通过报销单编号来查询该条信息
         */
        Expense expense = new Expense();
        expense.setExpenseId(auditRecord.getExpenseId());//设置编号值
        expense = expenseService.expenseQuery(expense).get(0);//回显的报销单信息
        req.setAttribute("expense", expense);
        /**
         * ②回显报销单明细
         * 通过报销单编号查询并显示报销单明细信息
         */
        List<Detail> detailList = expenseService.expenseDetailQuery(auditRecord.getExpenseId());
        req.setAttribute("detailList", detailList);
        /**
         * ③回显审核记录
         */
        List<AuditRecord> auditRecordList = expenseService.queryAuditExpense(expense.getExpenseId());
        req.setAttribute("auditRecordList", auditRecordList);



//        4.返回界面
        req.getRequestDispatcher("/view/expense/audit/expense_audit.jsp").forward(req,resp );



    }
}
