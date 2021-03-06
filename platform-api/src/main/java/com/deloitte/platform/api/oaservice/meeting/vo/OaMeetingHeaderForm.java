package com.deloitte.platform.api.oaservice.meeting.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description : OaMeetingHeader新增修改form对象
 * @Modified :
 */
@ApiModel("新增OaMeetingHeader表单")
@Data
public class OaMeetingHeaderForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "会议编号")
    private String meetingNo;

    @ApiModelProperty(value = "会议主题")
    private String meetingTitle;

    @ApiModelProperty(value = "会议内容")
    private String meetingContent;

    @ApiModelProperty(value = "紧急程度")
    private String emergency;

    @ApiModelProperty(value = "申请人姓名")
    private String createByName;

    @ApiModelProperty(value = "申请人部门ID")
    private String deptId;

    @ApiModelProperty(value = "申请人部门名称")
    private String deptName;

    @ApiModelProperty(value = "联系人")
    private String contactUserId;

    @ApiModelProperty(value = "联系人姓名")
    private String contactUserName;

    @ApiModelProperty(value = "联系人联系电话")
    private String contactTelphone;

    @ApiModelProperty(value = "联系人联系EMAIL")
    private String contactEmail;

    @ApiModelProperty(value = "会议开始日期")
    private String meetingStartDate;

    @ApiModelProperty(value = "会议结束日期")
    private String meetingEndDate;

    @ApiModelProperty(value = "会议开始时间")
    private String startTime;

    @ApiModelProperty(value = "会议结束时间")
    private String endTime;

    @ApiModelProperty(value = "会议类型")
    private String meetingType;

    @ApiModelProperty(value = "外部参会人员")
    private String outMembers;

    @ApiModelProperty(value = "是否需要备案")
    private String isBack;

    @ApiModelProperty(value = "是否发送参会通知")
    private String isNotice;

    @ApiModelProperty(value = "会议资源")
    private String meetingResouce;

    @ApiModelProperty(value = "备注")
    private String remarks;

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
