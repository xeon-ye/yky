package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description : CheckAchUserContent新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckAchUserContent表单")
@Data
public class CheckAchUserContentForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "个人业绩考核id")
    private Long checkAchUserId;

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

}
