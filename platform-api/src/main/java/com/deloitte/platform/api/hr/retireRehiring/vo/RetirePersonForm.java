package com.deloitte.platform.api.hr.retireRehiring.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description : RetirePerson新增修改form对象
 * @Modified :
 */
@ApiModel("新增RetirePerson表单")
@Data
public class RetirePersonForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "申请表Id")
    private Long appId;

    @ApiModelProperty(value = "员工编码")
    private String empCode;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "通知记录表Id")
    private Long recordId;

}
