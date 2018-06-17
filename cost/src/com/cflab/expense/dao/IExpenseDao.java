package com.cflab.expense.dao;

import com.cflab.domain.AuditRecord;
import com.cflab.domain.Detail;
import com.cflab.domain.Expense;

import java.util.List;

public interface IExpenseDao {
    int expenseAdd(Expense expense);
    int expenseDelete(Expense expense);
    int expenseUpdate(Expense expense);
    List<Expense> expenseQuery(Expense expense);
    List<Detail> expenseDetailQuery(int expenseId);
    int auditExpense (AuditRecord auditRecord);
    List<AuditRecord> queryAuditExpense(int expenseId);

}
