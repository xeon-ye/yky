package com.deloitte.services.fssc.business.general.biz;

import com.deloitte.platform.api.fssc.general.vo.GeGeneralExpenseForm;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpense;

public interface GeGeneralExpenseBiz {

    GeGeneralExpense saveOrUpdateGeExpense(GeGeneralExpenseForm geGeneralExpenseForm);



}
