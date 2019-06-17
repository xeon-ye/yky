package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   TeacherApplyInfo查询from对象
 * @Modified :
 */
@ApiModel("TeacherApplyInfo查询表单")
@Data
public class TeacherApplyInfoQueryForm extends BaseQueryForm<TeacherApplyInfoQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "教师资格申请信息表ID（关联hr_teacher_ability_apply表）")
    private Long teacherAbilityApplyId;

    @ApiModelProperty(value = "证书名称")
    private String certName;

    @ApiModelProperty(value = "证书编号")
    private String certCode;

    @ApiModelProperty(value = "生效日期")
    private LocalDate effectiveDate;

    @ApiModelProperty(value = "有效期（月）")
    private String validTerm;

    @ApiModelProperty(value = "证书状态（1有效，2无效）")
    private Integer certStatus;

    @ApiModelProperty(value = "颁发机构")
    private String awardCertOrg;

    @ApiModelProperty(value = "教师资格附件ID  （关联附件表）")
    private String attachmentTeacherId;

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

    @ApiModelProperty(value = "状态（1保存未提交证书/未上传，2已提交，3审核通过，4审核未通过）  维护字典表")
    private Integer status;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;
}
