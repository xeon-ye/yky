package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckAchUser新增修改form对象
 * @Modified :
 */
@ApiModel("批量修改表单")
@Data
public class CheckAchEvaluateUserModifyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "评估状态")
    private String evaluateStatus;

    @ApiModelProperty(value = "评价人员id")
    private List<String> list;

}
