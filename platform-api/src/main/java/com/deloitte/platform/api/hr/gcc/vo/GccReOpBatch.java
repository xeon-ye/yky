package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-13
 * @Description : GccReviewOption新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccReviewOption表单")
@Data
public class GccReOpBatch extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "批量对象")
    private List<GccReviewOptionBatchForm > list;

    @ApiModelProperty(value = "评审平台编号")
    private String platformId;


}
