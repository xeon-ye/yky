package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : User新增修改form对象
 * @Modified :
 */
@ApiModel("新增User表单")
@Data
public class UserForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "姓名不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "员工号")
    private String empNo;

    @NotEmpty
    @Pattern(regexp = "^1[2-9]\\d{9}$" ,message = "请输入正确的手机号码")
    @ApiModelProperty(value = "电话号码")
    private String phone;

    @NotEmpty
    @Pattern(regexp = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$" ,message = "请输入正确的电子邮箱")
    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "头衔，职称")
    private String honor;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    private String reserve;

    @ApiModelProperty(value = "备用字段2")
    private String version;

    @ApiModelProperty(value = "擅长领域")
    private String expertise;

    @NotEmpty(message = "学科不能为空")
    @ApiModelProperty(value = "学科")
    private String subject;
    @NotEmpty(message = "性别不能为空")
    @ApiModelProperty(value = "性别")
    private String gender;

    @NotNull(message = "生日不能为空")
    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthDate;

    @NotEmpty(message = "职称不能为空")
    @ApiModelProperty(value = "职称")
    private String positionTitle;

    @NotEmpty(message = "最高学历不能为空")
    @ApiModelProperty(value = "最高学历")
    private String education;

    @ApiModelProperty(value = "从事专业")
    private String major;

    @NotEmpty(message = "固定电话不能为空")
    @ApiModelProperty(value = "固定电话")
    private String tel;

    @ApiModelProperty(value = "传真")
    private String fax;

    @NotEmpty(message = "证件类型不能为空")
    @ApiModelProperty(value = "证件类型")
    private String idCardType;

    @NotEmpty(message = "证件号码不能为空")
    @Size(min = 6, max = 256 , message = "证件号码位数为6~256位")
    @ApiModelProperty(value = "证件号码")
    private String idCard;

    @NotEmpty(message = "学位授予国别不能为空")
    @ApiModelProperty(value = "学位授予国别")
    private String educationCountry;

    @NotEmpty
    @ApiModelProperty(value = "授予年份")
    private String educationYear;

    @NotEmpty
    @ApiModelProperty(value = "所在单位")
    private String dept;

    @ApiModelProperty(value = "每年工作时间")
    private Integer workPerYear;

    @NotEmpty
    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    @ApiModelProperty(value = "依托基地名称")
    private String liveBaseName;

    @ApiModelProperty(value = "入选人才计划")
    private String talentPlan;

    @ApiModelProperty(value = "源人员ID")
    private String sourcePersonId;

    @ApiModelProperty(value = "学位")
    private String degree;

    @ApiModelProperty(value = "所在科室")
    private String officeName;

    @ApiModelProperty(value = "承担单位")
    private String projectCommitmentUnit;

    @NotEmpty
    @ApiModelProperty(value = "国别及地区")
    private String country;

    @NotEmpty
    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "院系")
    private String faculty;

    @ApiModelProperty(value = "出生地")
    private String birthPlace;

    @ApiModelProperty(value = "政治面貌")
    private String ploSta;

    @ApiModelProperty(value = "管理岗位等级")
    private String managePositionRank;

    @ApiModelProperty(value = "默认副账号ID")
    private Long deputyAccountId;

    @ApiModelProperty(value = "研究成果")
    private String researchResults;

    @ApiModelProperty(value = "流程下一个审批页面路径")
    private String objectUrl;

    @ApiModelProperty(value = "职位级别")
    private String positionLevel;

}
