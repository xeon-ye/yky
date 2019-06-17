package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : Teacher新增修改form对象
 * @Modified :
 */
@ApiModel("修改Teacher表单")
@Data
public class TeacherForm  {
    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "申请任教学科")
    private String applyTeachSubject;

    @ApiModelProperty(value = "是否参加能力测试（1是，2否）")
    private String isAbilityTest;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "身份证号码")
    private String idCode;

}
