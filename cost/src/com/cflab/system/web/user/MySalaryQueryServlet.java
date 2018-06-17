package com.cflab.system.web.user;

import com.cflab.domain.SalaryRecord;
import com.cflab.domain.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/system/mySalaryQuery")
public class MySalaryQueryServlet extends HttpServlet {
    ISalaryService salaryService = new SalaryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.接受参数
        ConvertUtils.register(new MyDateconvertUtil("yyyy-MM"), Date.class);
        SalaryRecord salary = RequestBeanUtils.requestToSimpleBean(req,SalaryRecord.class );

        //获取userId根据userId查询记录
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");
        salary.setUserId(user.getUserId());
        //2.调用service
        List<SalaryRecord> salaryRecordList = salaryService.querySalary(salary);

        //3.回显查询条件和工资记录
        req.setAttribute("salary",salary );
        req.setAttribute("salaryRecordList",salaryRecordList );

        //4.跳转页面
        req.getRequestDispatcher("/view/system/user/salarypayment_mylist.jsp").forward(req,resp );
    }
}
