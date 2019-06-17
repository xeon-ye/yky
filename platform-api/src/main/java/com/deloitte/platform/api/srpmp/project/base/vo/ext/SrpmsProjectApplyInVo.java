package com.deloitte.platform.api.srpmp.project.base.vo.ext;

import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-11
 * @Description : 申请书项目信息数据集合
 * @Modified :
 */
@ApiModel("申请书项目信息数据集合")
@Data
public class SrpmsProjectApplyInVo extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目执行期限开始")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "项目负责人ID")
    private Long leadPersonId;

    @ApiModelProperty(value = "共同首席专家ID")
    private Long bothTopExpertPersonId;

    @ApiModelProperty(value = "负责人信息JSON")
    private String leadPerson;

    @ApiModelProperty(value = "共同负责人信息JSON")
    private String bothTopExpertPerson;

    @ApiModelProperty(value = "承担单位ID")
    private Double leadDeptId;

    @ApiModelProperty(value = "承担单位信息JSON")
    private String leadDept;

    @ApiModelProperty(value = "项目角色CODE")
    private String projectRole;

    @ApiModelProperty(value = "学科分类CODE")
    private String subjectCategory;
}
