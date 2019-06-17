package com.deloitte.platform.api.fssc.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

@Data
public class AddContentCommitmentOrgUnitForm implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "contentCommitmentIds")
    @NotBlank(message = "支出大类ID不能为空!")
    private String contentCommitmentIds;

    @ApiModelProperty(value = "formList")
    private List<BaseContentCommitmentUnitForm> formList;

}
