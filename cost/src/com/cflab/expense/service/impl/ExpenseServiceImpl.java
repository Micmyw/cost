package com.cflab.expense.service.impl;

import com.cflab.domain.AuditRecord;
import com.cflab.domain.Detail;
import com.cflab.domain.Expense;
import com.cflab.expense.dao.IExpenseDao;
import com.cflab.expense.dao.Impl.ExpenseDaoImpl;
import com.cflab.expense.service.IExpenseService;

import java.util.ArrayList;
import java.util.List;

public class ExpenseServiceImpl implements IExpenseService{
    IExpenseDao expenseDao = new ExpenseDaoImpl();
    @Override
    public boolean expenseAdd(Expense expense) {
        int rows = expenseDao.expenseAdd(expense);
        if (rows>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean expenseDelete(Expense expense) {
        return false;
    }

    @Override
    public boolean expenseUpdate(Expense expense) {
        int rows = expenseDao.expenseUpdate(expense);
        if (rows>0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Expense> expenseQuery(Expense expense) {
        //设置审核状态
        List<Expense> expenseList = expenseDao.expenseQuery(expense);
        List<Expense> newList = new ArrayList<>();
//        System.out.println(newList);
        for (Expense expense1 : expenseList) {
            if (expense1.getExpenseState().equals("0")) {
                expense1.setExpenseStateStr("<input type=button class='btn btn-danger' value='未提交'>");
            }else if (expense1.getExpenseState().equals("1")) {
                expense1.setExpenseStateStr("<input type=button class='btn btn-success' value='待审核'>");
            }else if (expense1.getExpenseState().equals("2")) {
                expense1.setExpenseStateStr("<input type=button class='btn btn-success' value='经理审核通过'>");
            }else if (expense1.getExpenseState().equals("3")) {
                expense1.setExpenseStateStr("<input type=button class='btn btn-success' value='财务审核通过'>");
            }else if (expense1.getExpenseState().equals("-1")) {
                expense1.setExpenseStateStr("<input type=button class='btn btn-danger' value='未通过经理审核'>");
            } else {
                expense1.setExpenseStateStr("<input type=button class='btn btn-danger' value='未通过财务审核'>");
            }
            newList.add(expense1);
        }
        return newList;
    }

    @Override
    public List<Detail> expenseDetailQuery(int expenseId) {
        return expenseDao.expenseDetailQuery(expenseId);
    }

    @Override
    /**
     * 添加审核记录
     */
    public boolean auditExpense(AuditRecord auditRecord) {
        int rows = expenseDao.auditExpense(auditRecord);
        if (rows>0) {
            return true;
        }
        return false;
    }

    @Override
    /**
     * 根据报销单编号查询多条审核记录
     */
    public List<AuditRecord> queryAuditExpense(int expenseId) {
        //设置审核状态
        List<AuditRecord> auditRecordList = expenseDao.queryAuditExpense(expenseId);
        List<AuditRecord> newList = new ArrayList<>();
//        System.out.println(newList);
        for (AuditRecord auditRecordList1 : auditRecordList) {
            if (auditRecordList1.getAuditState().equals("0")) {
                auditRecordList1.setAuditStateStr("<input type=button class='btn btn-danger' value='未提交'>");
            }else if (auditRecordList1.getAuditState().equals("1")) {
                auditRecordList1.setAuditStateStr("<input type=button class='btn btn-success' value='待审核'>");
            }else if (auditRecordList1.getAuditState().equals("2")) {
                auditRecordList1.setAuditStateStr("<input type=button class='btn btn-success' value='经理审核通过'>");
            }else if (auditRecordList1.getAuditState().equals("3")) {
                auditRecordList1.setAuditStateStr("<input type=button class='btn btn-success' value='财务审核通过'>");
            }else if (auditRecordList1.getAuditState().equals("-1")) {
                auditRecordList1.setAuditStateStr("<input type=button class='btn btn-danger' value='未通过经理审核'>");
            } else {
                auditRecordList1.setAuditStateStr("<input type=button class='btn btn-danger' value='未通过财务审核'>");
            }
            newList.add(auditRecordList1);
        }
        return newList ;
    }
}
