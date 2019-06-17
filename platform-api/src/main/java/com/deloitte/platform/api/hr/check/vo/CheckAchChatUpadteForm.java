package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckAchChat新增修改form对象
 * @Modified :
 */
@ApiModel("业绩沟通填写或者表单")
@Data
public class CheckAchChatUpadteForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业绩沟通id集合")
    private List<String> chatIdList;

    @ApiModelProperty(value = "沟通状态")
    private String chatStatus;

    @ApiModelProperty(value = "申诉结果")
    private String appeaResult;


}
