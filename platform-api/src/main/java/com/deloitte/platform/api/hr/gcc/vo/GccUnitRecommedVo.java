package com.deloitte.platform.api.hr.gcc.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : jiangString
 * @Date : Create in 2019-04-01
 * @Description : GccUnitRecommed返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccUnitRecommedVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "单位推荐意见")
    private String unitRecommendOpinion;

    @ApiModelProperty(value = "意见日期")
    private LocalDateTime opinionTime;

    @ApiModelProperty(value = "意见签章")
    private String opinionSignature;

    @ApiModelProperty(value = "经费匹配承诺比例")
    private Double promiseFundsRatio;

    @ApiModelProperty(value = "承诺日期")
    private LocalDateTime promiseTime;

    @ApiModelProperty(value = "承诺签章")
    private String promiseSignature;

    @ApiModelProperty(value = "投票同意票数")
    private String agreeVotes;

    @ApiModelProperty(value = "投票不同意票数")
    private String disagreeVotes;

    @ApiModelProperty(value = "投票弃权票数")
    private String waiverVotes;

    @ApiModelProperty(value = "投票日期")
    private LocalDateTime voteTime;

    @ApiModelProperty(value = "投票签章")
    private String voteSignature;

    @ApiModelProperty(value = "院校评审意见")
    private String academyReviewOpinion;

    @ApiModelProperty(value = "院校评审日期")
    private LocalDateTime academyReviewTime;

    @ApiModelProperty(value = "院校评审签章")
    private String academyReviewSignature;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "专家组人数")
    private String expertNum;
}
