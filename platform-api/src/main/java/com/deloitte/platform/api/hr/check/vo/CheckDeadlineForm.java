package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckDeadline新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckDeadline表单")
@Data
public class CheckDeadlineForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核工作id")
    private Long checkWorkId;

    @ApiModelProperty(value = "考核期间id")
    private Long checkTimeId;

    @ApiModelProperty(value = "提醒业务类型")
    private String warnType;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期 (格式：yyyy-MM-dd)")
    private LocalDate startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期 (格式：yyyy-MM-dd)")
    private LocalDate endTime;

    @ApiModelProperty(value = "是否启用")
    private String isEnabled;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
