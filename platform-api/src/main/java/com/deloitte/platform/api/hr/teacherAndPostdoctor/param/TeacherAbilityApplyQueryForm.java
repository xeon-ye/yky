package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   TeacherAbilityApply查询from对象
 * @Modified :
 */
@ApiModel("TeacherAbilityApply查询表单")
@Data
public class TeacherAbilityApplyQueryForm extends BaseQueryForm<TeacherAbilityApplyQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "教师信息表ID（关联hr_teacher表）")
    private Long teacherId;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "申请任教学科")
    private String applyTeachSubject;

    @ApiModelProperty(value = "是否参加能力测试（1是，2否）")
    private String isAbilityTest;

    @ApiModelProperty(value = "状态（1所院审核通过，2院校审核中，3院校审核通过，4院校审核不通过，5教委审核中，6教委审核通过，7教委审核不通过）  维护字典表")
    private Integer status;

    @ApiModelProperty(value = "是否上传证书（1是，2否）")
    private Integer isUploadCert;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "最后更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;
}
