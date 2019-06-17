package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :   CheckAchUserContent查询from对象
 * @Modified :
 */
@ApiModel("CheckAchUserContent查询表单")
@Data
public class CheckAchUserContentQueryForm extends BaseQueryForm<CheckAchUserContentQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "个人业绩考核id")
    private String checkAchUserId;

    @ApiModelProperty(value = "考核指标")
    private String quotaContent;

    @ApiModelProperty(value = "指标描述")
    private String quotaDescribe;

    @ApiModelProperty(value = "评价标准")
    private String evaluateStandard;

    @ApiModelProperty(value = "业绩完成情况")
    private String finishStatus;

    @ApiModelProperty(value = "权重")
    private String evaluateOpinion;

    @ApiModelProperty(value = "完成日期")
    private String finishDate;

    @ApiModelProperty(value = "工作标准")
    private String workStandard;

    @ApiModelProperty(value = "自评分")
    private String selfRating;

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
