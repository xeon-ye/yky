package com.deloitte.platform.api.hr.technology.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-15
 * @Description : RecruitTechnology返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitTechnologyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "参加工作时间")
    private LocalDateTime joinworkDay;

    @ApiModelProperty(value = "工作部门")
    private String dep;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "学位")
    private String degree;

    @ApiModelProperty(value = "毕业时间")
    private LocalDateTime graduate;

    @ApiModelProperty(value = "毕业专业")
    private String graduationMajor;

    @ApiModelProperty(value = "毕业学校")
    private String graduationShool;

    @ApiModelProperty(value = "现任专业职务")
    private String incumbentMajor;

    @ApiModelProperty(value = "现任专业职务开始时间")
    private LocalDateTime incumbentStartdate;

    @ApiModelProperty(value = "现任专业职务结束时间")
    private LocalDateTime incumbentEnddate;

    @ApiModelProperty(value = "现具备未聘任的专业技术资格名称")
    private String technicalNot;

    @ApiModelProperty(value = "获取时间")
    private LocalDateTime getDate;

    @ApiModelProperty(value = "审批单位")
    private String approvalCompany;

    @ApiModelProperty(value = "外语语种")
    private String languageType;

    @ApiModelProperty(value = "外语级别")
    private String languageLevel;

    @ApiModelProperty(value = "成绩")
    private String score;

    @ApiModelProperty(value = "外语取得时间")
    private LocalDateTime getLanguagedate;

    @ApiModelProperty(value = "审批单位")
    private String approvalCompanyLang;

    @ApiModelProperty(value = "申请专业技术职务")
    private String applyJob;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentVo> attachments;

    private String applyState;

    @ApiModelProperty(value = "标记是否毕业生")
    private String flag;

}
