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
 * @Description : CheckTime新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckTime表单")
@Data
public class CheckTimeForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核年度")
    private String checkYear;

    @ApiModelProperty(value = "考核期间名称")
    private String checkTimeName;

    @ApiModelProperty(value = "考核期间类型")
    private String checkTimeType;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期 (格式：yyyy-MM-dd)")
    private LocalDate startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期 (格式：yyyy-MM-dd)")
    private LocalDate endTime;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
