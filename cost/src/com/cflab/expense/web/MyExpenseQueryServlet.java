package com.cflab.expense.web;

import com.cflab.domain.Expense;
import com.cflab.domain.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/expense/MyExpenseQuery")
public class MyExpenseQueryServlet extends HttpServlet {
    IExpenseService expenseService = new ExpenseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受参数
        ConvertUtils.register(new MyDateconvertUtil("yyyy-MM"), Date.class);
        Expense expense = RequestBeanUtils.requestToSimpleBean(req, Expense.class);

        /**
         *  查询条件没有用户名，直接根据userId来查询我的报销单
         *  通过session获取信息
                */
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        /**
         * 设置当前登录用户ID只能查看本人报销信息
         */
        expense.setUserId(user.getUserId());

        //设置本人可查询的报销单状态
        expense.setExpenseStates(new Integer[]{0,1,2,3,-1,-2});
        //2.调用service
        List<Expense> expenseList = expenseService.expenseQuery(expense);

        //3.回显值
        req.setAttribute("expense",expense );
        req.setAttribute("expenseList",expenseList );

        req.getRequestDispatcher("/view/expense/expenses/expense_mylist.jsp").forward(req,resp );
    }
}
