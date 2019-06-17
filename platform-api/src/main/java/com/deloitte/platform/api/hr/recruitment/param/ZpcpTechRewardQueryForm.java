package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :   ZpcpTechReward查询from对象
 * @Modified :
 */
@ApiModel("ZpcpTechReward查询表单")
@Data
public class ZpcpTechRewardQueryForm extends BaseQueryForm<ZpcpTechRewardQueryParam>  {


    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "selectType")
    private String selectType;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

   /* @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "获奖项目名称")
    private String rewardProjectName;

    @ApiModelProperty(value = "奖励名称")
    private String rewardName;

    @ApiModelProperty(value = "等级")
    private String grade;

    @ApiModelProperty(value = "排名")
    private String ranking;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;



    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "证书编号")
    private String certificateNo;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;*/
}
