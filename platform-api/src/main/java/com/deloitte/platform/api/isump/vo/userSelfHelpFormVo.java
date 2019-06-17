package com.deloitte.platform.api.isump.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhangdi
 * @Date 11/05/2019
 */
@ApiModel("外部人员自助")
@Data
public class userSelfHelpFormVo extends BaseForm {

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


    @ApiModelProperty(value = "学科")
    private String subject;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthDate;

    @ApiModelProperty(value = "职称")
    private String positionTitle;

    @ApiModelProperty(value = "最高学历")
    private String education;

    @ApiModelProperty(value = "从事专业")
    private String major;

    @ApiModelProperty(value = "固定电话")
    private String tel;

    @ApiModelProperty(value = "传真")
    private String fax;

    @ApiModelProperty(value = "证件类型")
    private String idCardType;

    @Size(min = 6, max = 256 , message = "证件号码位数为6~256位")
    @ApiModelProperty(value = "证件号码")
    private String idCard;

    @ApiModelProperty(value = "学位授予国别")
    private String educationCountry;

    @ApiModelProperty(value = "授予年份")
    private String educationYear;

    @ApiModelProperty(value = "所在单位")
    private String dept;

    @ApiModelProperty(value = "每年工作时间")
    private Integer workPerYear;

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

    @ApiModelProperty(value = "国别及地区")
    private String country;

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

    @ApiModelProperty(value = "财务用户信息")
    private FsscUserVo fsscUser;

}
