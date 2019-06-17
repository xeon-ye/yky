package com.deloitte.platform.api.hr.gcc.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccStudyExperience查询from对象
 * @Modified :
 */
@ApiModel("GccStudyExperience查询表单")
@Data
public class GccStudyExperienceQueryForm extends BaseQueryForm<GccStudyExperienceQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "编号")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "院校（系）名称")
    private String institutionName;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "学位")
    private String degree;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "申报id")
    private Long applicationId;
}
