package com.cflab.expense.web;

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

@WebServlet("/expense/expenseQuery")
public class ExpenseQueryServlet extends HttpServlet {
    IExpenseService expenseService = new ExpenseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //有时间查询条件，注册时间转换器
        ConvertUtils.register(new MyDateconvertUtil("yyyy-MM-dd"), Date.class);
        Expense expense = RequestBeanUtils.requestToSimpleBean(req,Expense.class );
        /**
         * 限制查询报销信息,设置状态
         */
        expense.setExpenseStates(new Integer[]{1,2,3,-1,-2});
        //直接调用service
        List<Expense> expenseList = expenseService.expenseQuery(expense);

        req.setAttribute("expenseList", expenseList);
        req.setAttribute("expense",expense );
        req.getRequestDispatcher("/view/expense/expenses/expense_list.jsp").forward(req,resp );
    }
}
