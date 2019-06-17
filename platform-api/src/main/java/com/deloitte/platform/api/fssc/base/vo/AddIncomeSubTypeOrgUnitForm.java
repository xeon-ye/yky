package com.deloitte.platform.api.fssc.base.vo;

import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class AddIncomeSubTypeOrgUnitForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "incomeMainTypeId",notes = "多个id,以逗号分割")
    @NotBlank(message = "收入小类ID不能为空!")
    private String incomeSubTypeIds;

    @ApiModelProperty(value = "formList")
    private List<BaseIncomeSubTypeOrgUnitForm> formList;

}
