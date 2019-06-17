package com.deloitte.services.meeting.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fuqiao
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_MEETING_HEADER")
@ApiModel(value="OaMeetingHeader对象", description="")
public class OaMeetingHeader extends Model<OaMeetingHeader> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @TableField("MEETING_NO")
    private String meetingNo;

    @ApiModelProperty(value = "会议主题")
    @TableField("MEETING_TITLE")
    private String meetingTitle;

    @ApiModelProperty(value = "会议内容")
    @TableField("MEETING_CONTENT")
    private String meetingContent;

    @ApiModelProperty(value = "紧急程度")
    @TableField("EMERGENCY")
    private String emergency;

    @ApiModelProperty(value = "申请人姓名")
    @TableField("CREATE_BY_NAME")
    private String createByName;

    @ApiModelProperty(value = "申请人部门ID")
    @TableField("DEPT_ID")
    private String deptId;

    @ApiModelProperty(value = "申请人部门名称")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "联系人")
    @TableField("CONTACT_USER_ID")
    private String contactUserId;

    @ApiModelProperty(value = "联系人姓名")
    @TableField("CONTACT_USER_NAME")
    private String contactUserName;

    @ApiModelProperty(value = "联系人联系电话")
    @TableField("CONTACT_TELPHONE")
    private String contactTelphone;

    @ApiModelProperty(value = "联系人联系EMAIL")
    @TableField("CONTACT_EMAIL")
    private String contactEmail;

    @ApiModelProperty(value = "会议开始日期")
    @TableField("MEETING_START_DATE")
    private String meetingStartDate;

    @ApiModelProperty(value = "会议结束日期")
    @TableField("MEETING_END_DATE")
    private String meetingEndDate;

    @ApiModelProperty(value = "会议开始时间")
    @TableField("START_TIME")
    private String startTime;

    @ApiModelProperty(value = "会议结束时间")
    @TableField("END_TIME")
    private String endTime;

    @ApiModelProperty(value = "会议类型")
    @TableField("MEETING_TYPE")
    private String meetingType;

    @ApiModelProperty(value = "外部参会人员")
    @TableField("OUT_MEMBERS")
    private String outMembers;

    @ApiModelProperty(value = "是否需要备案")
    @TableField("IS_BACK")
    private String isBack;

    @ApiModelProperty(value = "是否发送参会通知")
    @TableField("IS_NOTICE")
    private String isNotice;

    @ApiModelProperty(value = "会议资源")
    @TableField("MEETING_RESOUCE")
    private String meetingResouce;

    @ApiModelProperty(value = "备注")
    @TableField("REMARKS")
    private String remarks;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT5")
    private String ext5;


    public static final String ID = "ID";

    public static final String MEETING_NO = "MEETING_NO";

    public static final String MEETING_TITLE = "MEETING_TITLE";

    public static final String MEETING_CONTENT = "MEETING_CONTENT";

    public static final String EMERGENCY = "EMERGENCY";

    public static final String CREATE_BY_NAME = "CREATE_BY_NAME";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String CONTACT_USER_ID = "CONTACT_USER_ID";

    public static final String CONTACT_USER_NAME = "CONTACT_USER_NAME";

    public static final String CONTACT_TELPHONE = "CONTACT_TELPHONE";

    public static final String CONTACT_EMAIL = "CONTACT_EMAIL";

    public static final String MEETING_START_DATE = "MEETING_START_DATE";

    public static final String MEETING_END_DATE = "MEETING_END_DATE";

    public static final String START_TIME = "START_TIME";

    public static final String END_TIME = "END_TIME";

    public static final String MEETING_TYPE = "MEETING_TYPE";

    public static final String OUT_MEMBERS = "OUT_MEMBERS";

    public static final String IS_BACK = "IS_BACK";

    public static final String IS_NOTICE = "IS_NOTICE";

    public static final String MEETING_RESOUCE = "MEETING_RESOUCE";

    public static final String REMARKS = "REMARKS";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
