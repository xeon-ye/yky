package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccAnnualCheck查询from对象
 * @Modified :
 */
@ApiModel("GccAnnualCheck查询表单")
@Data
public class GccAnnualCheckQueryForm extends BaseQueryForm<GccAnnualCheckQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "序号")
    private Long orderNumber;

    @ApiModelProperty(value = "上报年份")
    private String reportingYear;

    @ApiModelProperty(value = "单位")
    private String unitId;

    @ApiModelProperty(value = "人才项目")
    private String personnelProject;

    @ApiModelProperty(value = "上报内容")
    private String reportingContent;

    @ApiModelProperty(value = "上报人")
    private String reportingUser;

    @ApiModelProperty(value = "上报日期")
    private LocalDateTime reportingTime;

    @ApiModelProperty(value = "附件")
    private String fileId;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
