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

@WebServlet("/expense/expenseManagerQuery")
public class ExpenseManagerQueryServlet extends HttpServlet{
    IExpenseService expenseService = new ExpenseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         *  1.接受参数,查询报销单列表,
         *  ①starDate和endDate是string类型时：直接传入参数，并将list.sp中fmt转换去掉（简单方法）
         *  ②starDate和endDate是date类型时：转换时间回显的格式，与jsp设置的一致（复杂）
         *  Expense expense = RequestBeanUtils.requestToBean(req,Expense.class,"yyyy-MM-dd" );
         *  但时间为空时会报错，需要自己定义时间转换器
          */
        //注册一个自己的时间转换器
        ConvertUtils.register(new MyDateconvertUtil("yyyy-MM-dd"), Date.class);
        Expense expense = RequestBeanUtils.requestToSimpleBean(req,Expense.class );
        /**
         * 将状态设置为“1”，此时经理审批列表只会显示状态为“1”的expense
         */
        expense.setExpenseStates(new Integer[]{1,-1,2});
//        expense.setExpenseState("1");
//        2.调用业务逻辑层
        List<Expense> expenseList = expenseService.expenseQuery(expense);
//        System.out.println(expenseList);
//        3.回显页面
        req.setAttribute("expense",expense );//回显查询条件
        req.setAttribute("expenseList", expenseList);//回显列表
//        4.跳转页面
        req.getRequestDispatcher("/view/expense/expenses/expense_managerlist.jsp").forward(req,resp );

    }
}
