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
@ApiModel("新增CheckAchChat表单")
@Data
public class CheckAchChatNotifyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核结果id集合")
    private List<String> checkResultIdlist;

    @ApiModelProperty(value = "绩效沟通")
    private CheckAchChatForm checkAchChatForm;

}
