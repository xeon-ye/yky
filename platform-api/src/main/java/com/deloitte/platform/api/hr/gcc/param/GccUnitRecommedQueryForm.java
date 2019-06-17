package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccUnitRecommed查询from对象
 * @Modified :
 */
@ApiModel("GccUnitRecommed查询表单")
@Data
public class GccUnitRecommedQueryForm extends BaseQueryForm<GccUnitRecommedQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;


    @ApiModelProperty(value = "用户id")
    private Long userId;

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
    private Long agreeVotes;

    @ApiModelProperty(value = "投票不同意票数")
    private Long disagreeVotes;

    @ApiModelProperty(value = "投票弃权票数")
    private Long waiverVotes;

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
}
