package com.deloitte.platform.api.srpmp.project.apply.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-04
 * @Description : SrpmsProjectApplyInnPre新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectApplyInnPre表单")
@Data
public class SrpmsProjectApplyInnPreForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "预期成果类型CODE")
    private String achievementType;

    @ApiModelProperty(value = "拟申请岗位数")
    private Long applyPostNumber;

    @ApiModelProperty(value = "拟申请经费数")
    private Double applyFunds;

    @ApiModelProperty(value = "项目摘要")
    private String projectAbstract;

    @ApiModelProperty(value = "立项必要性")
    private String approvalNecessay;

    @ApiModelProperty(value = "项目成果的呈现形式及描述")
    private String achievementForm;

    @ApiModelProperty(value = "项目成果的预期经济、社会效益")
    private String achievementBenefit;

    @ApiModelProperty(value = "主要研究内容")
    private String researchContentMain;

    @ApiModelProperty(value = "拟采取的研究方法、技术路线及其可行性分析")
    private String researchContentMethod;

    @ApiModelProperty(value = "项目总体目标、考核指标及测评方式/方法")
    private String projectTarget;

    @ApiModelProperty(value = "基础条件和优势")
    private String superiorityDeptBasic;

    @ApiModelProperty(value = "${field.comment}")
    private Double fundSourceAmount;

    @ApiModelProperty(value = "${field.comment}")
    private Double fundSourceProject;

    @ApiModelProperty(value = "${field.comment}")
    private Double fundSourceSelf;

    @ApiModelProperty(value = "${field.comment}")
    private Double fundSourceOther;

}
