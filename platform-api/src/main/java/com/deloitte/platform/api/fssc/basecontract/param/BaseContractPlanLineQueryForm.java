package com.deloitte.platform.api.fssc.basecontract.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description :   BaseContractPlanLine查询from对象
 * @Modified :
 */
@ApiModel("BaseContractPlanLine查询表单")
@Data
public class BaseContractPlanLineQueryForm extends BaseQueryForm<BaseContractPlanLineQueryParam> {

        private Long contractId;

        private String contractType;

        private String contractNo;

        private String contractName;
        private String sideSubjectCode;
        private String sideSubjectName;
        private BigDecimal hasVerAmount;
        private BigDecimal actualPlayAmount;
        private BigDecimal receipPlayAmount;
        private BigDecimal paidAmount;
}
