package com.cflab.finance.web;

import com.cflab.domain.Expense;
import com.cflab.expense.service.IExpenseService;
import com.cflab.expense.service.impl.ExpenseServiceImpl;
import com.cflab.utils.MyDateconvertUtil;
import com.my.web.servlet.RequestBeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/finance/financeAuditQuery")
public class FinanceAuditQueryServlet extends HttpServlet {
    IExpenseService expenseService = new ExpenseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ConvertUtils.register(new MyDateconvertUtil("yyyy-MM"), Date.class);
        Expense expense = RequestBeanUtils.requestToSimpleBean(req,Expense.class );
        /**
         *   设置只显示经理审核过的报销单,
         *   在查询时sql语句会加上状态等于2，或3，或-2的条件（sql添加了将其转换成字符串的功能）
         */
        expense.setExpenseStates(new Integer[]{2,3,-2});
//        2.调用service
        List<Expense> expenseList = expenseService.expenseQuery(expense);
//        3.回显数据
        req.setAttribute("expense",expense );
        req.setAttribute("expenseList",expenseList );

        req.getRequestDispatcher("/view/finance/audit/financeaudit_list.jsp").forward(req,resp );
    }
}
