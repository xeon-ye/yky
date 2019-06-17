package com.deloitte.platform.api.fssc.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * 新增单据类型-人员类型 Form
 *
 * @author jawjiang
 */
@Data
public class AddDocumentTypePersonTypeForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "documentTypeIds", notes = "多个id,以逗号分割")
    @NotBlank(message = "单据类型ID不能为空!")
    private String documentTypeIds;

    @ApiModelProperty(value = "formList")
    private List<BasePersonIncomeTypeForm> formList;
}
