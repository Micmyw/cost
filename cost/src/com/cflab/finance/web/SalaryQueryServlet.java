package com.cflab.finance.web;

import com.cflab.domain.SalaryRecord;
import com.cflab.finance.service.ISalaryService;
import com.cflab.finance.service.impl.SalaryService;
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
@WebServlet("/finance/salaryQuery")
public class SalaryQueryServlet extends HttpServlet{
    ISalaryService salaryService = new SalaryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConvertUtils.register(new MyDateconvertUtil("yyyy-MM"), Date.class);

        SalaryRecord salary = RequestBeanUtils.requestToSimpleBean(req,SalaryRecord.class );

        List<SalaryRecord> salaryRecordList = salaryService.querySalary(salary);

        req.setAttribute("salary", salary);
        req.setAttribute("salaryRecordList", salaryRecordList);

        req.getRequestDispatcher("/view/finance/cost/salarypayment_list.jsp").forward(req,resp );
    }
}
