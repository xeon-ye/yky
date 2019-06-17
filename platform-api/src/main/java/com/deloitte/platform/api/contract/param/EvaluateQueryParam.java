package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : hemingzheng
 * @Date : Create in 2019-04-25
 * @Description :  Evaluate查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String contractId;
    private String contractName;
    private String signStrategyImplementation;
    private String signBiddingRiskanalysis;
    private String signQuotationsLessons;
    private String signLessonsCareful;
    private String signCoordinationEvaluation;
    private String performIsNormal;
    private String performSpecialCase;
    private String performProsCons;
    private String performCoordinatProblem;
    private String performOther;
    private String termsProsCons;
    private String termsSpecialpromResult;
    private String termsExpressionPromotion;
    private String termsOther;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String spareField1;
    private String spareField2;
    private String spareField3;
    private LocalDateTime spareField4;
    private Long spareField5;

}
