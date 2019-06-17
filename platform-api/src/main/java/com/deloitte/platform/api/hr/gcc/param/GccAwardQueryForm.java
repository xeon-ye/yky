package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccAward查询from对象
 * @Modified :
 */
@ApiModel("GccAward查询表单")
@Data
public class GccAwardQueryForm extends BaseQueryForm<GccAwardQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "人员编号")
    private Long userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "获奖项目名称")
    private String awardProjectName;

    @ApiModelProperty(value = "奖励名称")
    private String awardName;

    @ApiModelProperty(value = "排名")
    private String ranking;

    @ApiModelProperty(value = "证书编号")
    private String certificateNumber;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "申报id")
    private Long applicationId;
}
