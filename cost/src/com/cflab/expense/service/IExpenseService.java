package com.cflab.expense.service;

import com.cflab.domain.AuditRecord;
import com.cflab.domain.Detail;
import com.cflab.domain.Expense;

import java.util.List;

public interface IExpenseService {
    boolean expenseAdd(Expense expense);
    boolean expenseDelete(Expense expense);
    boolean expenseUpdate(Expense expense);
    List<Expense> expenseQuery(Expense expense);
    List<Detail> expenseDetailQuery(int expenseId);
    boolean auditExpense (AuditRecord auditRecord);
    List<AuditRecord> queryAuditExpense(int expenseId);
}
