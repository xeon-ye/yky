package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : hemingzheng
 * @Date : Create in 2019-04-25
 * @Description : Evaluate新增修改form对象
 * @Modified :
 */
@ApiModel("新增Evaluate表单")
@Data
public class EvaluateForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "合同ID")
    private String contractId;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "合同战略和策略实现情况")
    private String signStrategyImplementation;

    @ApiModelProperty(value = "招标文件与合同风险分析的准确程度")
    private String signBiddingRiskanalysis;

    @ApiModelProperty(value = "环境调查，实施方案，预算以及报价方面的问题及经验教训")
    private String signQuotationsLessons;

    @ApiModelProperty(value = "合同谈判的问题及经验教训，以后签订同类合同的注意点")
    private String signLessonsCareful;

    @ApiModelProperty(value = "相关合同之间的协调问题评价")
    private String signCoordinationEvaluation;

    @ApiModelProperty(value = "合同履约是否正常")
    private String performIsNormal;

    @ApiModelProperty(value = "合同履约中出现哪些特殊情况及处理方式")
    private String performSpecialCase;

    @ApiModelProperty(value = "合同风险控制的利弊得失")
    private String performProsCons;

    @ApiModelProperty(value = "相关合同在执行中协调的问题")
    private String performCoordinatProblem;

    @ApiModelProperty(value = "履行情况评价其他")
    private String performOther;

    @ApiModelProperty(value = "条款的表达和执行利弊得失")
    private String termsProsCons;

    @ApiModelProperty(value = "签订和执行过程中所遇到的特殊问题的分析结果")
    private String termsSpecialpromResult;

    @ApiModelProperty(value = "合同条款表达提升点")
    private String termsExpressionPromotion;

    @ApiModelProperty(value = "合同条款分析其他")
    private String termsOther;

    @ApiModelProperty(value = "${field.comment}")
    private String spareField1;

    @ApiModelProperty(value = "${field.comment}")
    private String spareField2;

    @ApiModelProperty(value = "${field.comment}")
    private String spareField3;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "${field.comment}")
    private Long spareField5;

}
