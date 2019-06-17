package com.deloitte.platform.api.hr.retireRehiring.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description : RetireRehiringPerson新增修改form对象
 * @Modified :
 */
@ApiModel("新增RetireRehiringPerson表单")
@Data
public class RehiringPersonForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "返聘部门")
    private Long rehiringDept;

    @ApiModelProperty(value = "返聘岗位")
    private Long rehiringPost;

    @ApiModelProperty(value = "返聘开始时间")
    private LocalDate startTime;

    @ApiModelProperty(value = "返聘结束时间")
    private LocalDate endTime;

    @ApiModelProperty(value = "通知记录表Id")
    private Long recordId;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

}
