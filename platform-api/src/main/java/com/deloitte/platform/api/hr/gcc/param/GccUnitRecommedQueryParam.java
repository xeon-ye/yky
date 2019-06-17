package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccUnitRecommed查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccUnitRecommedQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String unitRecommendOpinion;
    private LocalDateTime opinionTime;
    private String opinionSignature;
    private Double promiseFundsRatio;
    private LocalDateTime promiseTime;
    private String promiseSignature;
    private Long agreeVotes;
    private Long disagreeVotes;
    private Long waiverVotes;
    private LocalDateTime voteTime;
    private String voteSignature;
    private String academyReviewOpinion;
    private LocalDateTime academyReviewTime;
    private String academyReviewSignature;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
