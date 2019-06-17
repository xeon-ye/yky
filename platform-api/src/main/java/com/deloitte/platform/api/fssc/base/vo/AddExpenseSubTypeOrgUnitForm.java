package com.deloitte.platform.api.fssc.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

@Data
public class AddExpenseSubTypeOrgUnitForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "expenseMainTypeIds",notes = "多个id,以逗号分割")
    @NotBlank(message = "支出小类ID不能为空!")
    private String expenseSubTypeIds;

    @ApiModelProperty(value = "formList")
    private List<BaseExpenseSubTypeUnitForm> formList;

}
