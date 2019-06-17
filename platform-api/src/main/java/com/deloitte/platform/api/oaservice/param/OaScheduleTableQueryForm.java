package com.deloitte.platform.api.oaservice.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-03-26
 * @Description :   OaScheduleTable查询from对象
 * @Modified :
 */
@ApiModel("OaScheduleTable查询表单")
@Data
public class OaScheduleTableQueryForm extends BaseQueryForm<OaScheduleTableQueryParam>  {


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "行号")
    private int rowNum;

    @ApiModelProperty(value = "业务单据主键")
    private String businessId;

    @ApiModelProperty(value = "日程类型")
    private String workType;

    @ApiModelProperty(value = "日程简述")
    private String workName;

    @ApiModelProperty(value = "日程内容")
    private String workDesc;

    @ApiModelProperty(value = "日程状态")
    private String workStatus;

    @ApiModelProperty(value = "是否显示")
    private String showFlag;

    @ApiModelProperty(value = "日程对象")
    private String userId;

    @ApiModelProperty(value = "日程对象名称")
    private String userName;

    @ApiModelProperty(value = "日程对象所属部门ID")
    private String deptId;

    @ApiModelProperty(value = "日程对象所属部门名称")
    private String deptName;

    @ApiModelProperty(value = "日程开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "日程结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "日程创建人")
    private String createBy;

    @ApiModelProperty(value = "日程创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "日程最后更新人")
    private String updateBy;

    @ApiModelProperty(value = "日程最后更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "个人日程类型")
    private String personType;

    @ApiModelProperty(value = "提醒方式")
    private String noticeType;

    @ApiModelProperty(value = "备用字段")
    private String ext1;

    @ApiModelProperty(value = "备用字段")
    private String ext2;

    @ApiModelProperty(value = "备用字段")
    private String ext3;

    @ApiModelProperty(value = "备用字段")
    private String ext4;

    @ApiModelProperty(value = "备用字段")
    private String ext5;
}
