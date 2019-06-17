package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description : CheckAchUserContent新增修改form对象
 * @Modified :
 */
@ApiModel("考核内容审批form")
@Data
public class CheckAchUserContentAppealForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "通知标题")
    private String notifyName;

    @ApiModelProperty(value = "通知时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "考核关系id")
    private String checkRelationId;

    @ApiModelProperty(value = "考核工作id")
    private String checkWorkId;

    @ApiModelProperty(value = "考核期间id")
    private String checkTimeId;

    @ApiModelProperty(value = "考核组织id")
    private String checkOrgId;

    @ApiModelProperty(value = "业绩考核模板id")
    private String checkAchTempateId;

    @ApiModelProperty(value = "评估状态")
    private String evaluateStatus;

    @ApiModelProperty(value = "被评价人id")
    private String userId;

    @ApiModelProperty(value = "教职工姓名")
    private String name;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "部门编号")
    private String depCode;

    @ApiModelProperty(value = "岗位编号")
    private String positionCode;

    @ApiModelProperty(value = "开始日期")
    private LocalDate startTime;

    @ApiModelProperty(value = "截至日期")
    private LocalDate endTime;

}
