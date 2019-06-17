package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : Teacher返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "教师资格申请信息表ID")
    private String teacherAbilityApplyId;

    @ApiModelProperty(value = "教师编号")
    private String teacherCode;

    @ApiModelProperty(value = "教师姓名")
    private String name;

    @ApiModelProperty(value = "所属单位")
    private String attachUnit;

    @ApiModelProperty(value = "所在部门")
    private String attachUnitDepartment;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "毕业学校")
    private String graduationSchool;

    @ApiModelProperty(value = "最高学历")
    private String maxEducation;

    @ApiModelProperty(value = "最高学位")
    private String maxDegree;

    @ApiModelProperty(value = "现从事职业")
    private String currentProfession;

    @ApiModelProperty(value = "身份证号码")
    private String idCode;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "专业技术职务")
    private String workPosition;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "申请任教学科")
    private String applyTeachSubject;

    @ApiModelProperty(value = "是否参加能力测试（1是，2否）")
    private String isAbilityTest;

    @ApiModelProperty(value = "状态（1所院审核通过，2院校审核中，3院校审核通过，4院校审核不通过，5教委审核中，6教委审核通过，7教委审核不通过）  维护字典表")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

}
