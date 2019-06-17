package com.deloitte.services.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 合同评价信息表
 * </p>
 *
 * @author hemingzheng
 * @since 2019-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_EVALUATE")
@ApiModel(value="Evaluate对象", description="合同评价信息表")
public class Evaluate extends Model<Evaluate> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "合同ID")
    @TableField("CONTRACT_ID")
    private String contractId;

    @ApiModelProperty(value = "合同名称")
    @TableField("CONTRACT_NAME")
    private String contractName;

    @ApiModelProperty(value = "合同战略和策略实现情况")
    @TableField("SIGN_STRATEGY_IMPLEMENTATION")
    private String signStrategyImplementation;

    @ApiModelProperty(value = "招标文件与合同风险分析的准确程度")
    @TableField("SIGN_BIDDING_RISKANALYSIS")
    private String signBiddingRiskanalysis;

    @ApiModelProperty(value = "环境调查，实施方案，预算以及报价方面的问题及经验教训")
    @TableField("SIGN_QUOTATIONS_LESSONS")
    private String signQuotationsLessons;

    @ApiModelProperty(value = "合同谈判的问题及经验教训，以后签订同类合同的注意点")
    @TableField("SIGN_LESSONS_CAREFUL")
    private String signLessonsCareful;

    @ApiModelProperty(value = "相关合同之间的协调问题评价")
    @TableField("SIGN_COORDINATION_EVALUATION")
    private String signCoordinationEvaluation;

    @ApiModelProperty(value = "合同履约是否正常")
    @TableField("PERFORM_IS_NORMAL")
    private String performIsNormal;

    @ApiModelProperty(value = "合同履约中出现哪些特殊情况及处理方式")
    @TableField("PERFORM_SPECIAL_CASE")
    private String performSpecialCase;

    @ApiModelProperty(value = "合同风险控制的利弊得失")
    @TableField("PERFORM_PROS_CONS")
    private String performProsCons;

    @ApiModelProperty(value = "相关合同在执行中协调的问题")
    @TableField("PERFORM_COORDINAT_PROBLEM")
    private String performCoordinatProblem;

    @ApiModelProperty(value = "履行情况评价其他")
    @TableField("PERFORM_OTHER")
    private String performOther;

    @ApiModelProperty(value = "条款的表达和执行利弊得失")
    @TableField("TERMS_PROS_CONS")
    private String termsProsCons;

    @ApiModelProperty(value = "签订和执行过程中所遇到的特殊问题的分析结果")
    @TableField("TERMS_SPECIALPROM_RESULT")
    private String termsSpecialpromResult;

    @ApiModelProperty(value = "合同条款表达提升点")
    @TableField("TERMS_EXPRESSION_PROMOTION")
    private String termsExpressionPromotion;

    @ApiModelProperty(value = "合同条款分析其他")
    @TableField("TERMS_OTHER")
    private String termsOther;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableField("SPARE_FIELD_1")
    private String spareField1;

    @TableField("SPARE_FIELD_2")
    private String spareField2;

    @TableField("SPARE_FIELD_3")
    private String spareField3;

    @TableField("SPARE_FIELD_4")
    private LocalDateTime spareField4;

    @TableField("SPARE_FIELD_5")
    private Long spareField5;


    public static final String ID = "ID";

    public static final String CONTRACT_ID = "CONTRACT_ID";

    public static final String CONTRACT_NAME = "CONTRACT_NAME";

    public static final String SIGN_STRATEGY_IMPLEMENTATION = "SIGN_STRATEGY_IMPLEMENTATION";

    public static final String SIGN_BIDDING_RISKANALYSIS = "SIGN_BIDDING_RISKANALYSIS";

    public static final String SIGN_QUOTATIONS_LESSONS = "SIGN_QUOTATIONS_LESSONS";

    public static final String SIGN_LESSONS_CAREFUL = "SIGN_LESSONS_CAREFUL";

    public static final String SIGN_COORDINATION_EVALUATION = "SIGN_COORDINATION_EVALUATION";

    public static final String PERFORM_IS_NORMAL = "PERFORM_IS_NORMAL";

    public static final String PERFORM_SPECIAL_CASE = "PERFORM_SPECIAL_CASE";

    public static final String PERFORM_PROS_CONS = "PERFORM_PROS_CONS";

    public static final String PERFORM_COORDINAT_PROBLEM = "PERFORM_COORDINAT_PROBLEM";

    public static final String PERFORM_OTHER = "PERFORM_OTHER";

    public static final String TERMS_PROS_CONS = "TERMS_PROS_CONS";

    public static final String TERMS_SPECIALPROM_RESULT = "TERMS_SPECIALPROM_RESULT";

    public static final String TERMS_EXPRESSION_PROMOTION = "TERMS_EXPRESSION_PROMOTION";

    public static final String TERMS_OTHER = "TERMS_OTHER";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String SPARE_FIELD_1 = "SPARE_FIELD_1";

    public static final String SPARE_FIELD_2 = "SPARE_FIELD_2";

    public static final String SPARE_FIELD_3 = "SPARE_FIELD_3";

    public static final String SPARE_FIELD_4 = "SPARE_FIELD_4";

    public static final String SPARE_FIELD_5 = "SPARE_FIELD_5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
