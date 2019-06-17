package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.api.hr.common.util.DateFarmatUtil;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : TeacherApplyInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增TeacherApplyInfo表单")
@Data
public class TeacherApplyInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "教师资格申请信息表ID")
    private Long teacherAbilityApplyId;

    @ApiModelProperty(value = "证书名称")
    private String certName;

    @ApiModelProperty(value = "证书编号")
    private String certCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "生效日期（格式:2019-03-26）")
    private LocalDate effectiveDate;

    @ApiModelProperty(value = "有效期（月）")
    private String validTerm;

    @ApiModelProperty(value = "证书状态（1有效，2无效）")
    private Integer certStatus;

    @ApiModelProperty(value = "颁发机构")
    private String awardCertOrg;

    @ApiModelProperty(value = "教师资格附件Url ")
    private String attachmentTeacherUrl;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态（1保存，2提交）")
    private Integer status;

}
