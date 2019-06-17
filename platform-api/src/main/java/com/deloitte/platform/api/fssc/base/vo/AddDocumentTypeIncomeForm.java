package com.deloitte.platform.api.fssc.base.vo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增单据类型-收入大类 Form
 *
 * @author jawjiang
 */
@Data
public class AddDocumentTypeIncomeForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "documentTypeIds", notes = "多个id,以逗号分割")
    @NotBlank(message = "单据类型ID不能为空!")
    private String documentTypeIds;

    @ApiModelProperty(value = "formList")
    private List<BaseDocumentTypeIncomeForm> formList;
}
