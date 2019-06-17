package com.deloitte.services.notice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.deloitte.services.attachment.entity.OaAttachment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 校历
 * </p>
 *
 * @author jianghaixun
 * @since 2019-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_MEETING_RECORD")
@ApiModel(value="OaMeetingRecord对象", description="校历")
public class OaMeetingRecord extends Model<OaMeetingRecord> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会议纪要ID，主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "会议纪要标题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty(value = "申请部门id")
    @TableField("APPLY_DEPT_ID")
    private String applyDeptId;

    @ApiModelProperty(value = "申请部门名称")
    @TableField("APPLY_DEPT_NAME")
    private String applyDeptName;

    @ApiModelProperty(value = "申请人员id")
    @TableField("APPLY_USER_ID")
    private String applyUserId;

    @ApiModelProperty(value = "申请人员名称")
    @TableField("APPLY_USER_NAME")
    private String applyUserName;

    @ApiModelProperty(value = "申请日期，默认当前日期")
    @TableField("APPLY_DATETIME")
    private LocalDateTime applyDatetime;

    @ApiModelProperty(value = "是否删除，默认0否，1，是，0，否")
    @TableField("DEL_FLAG")
    private Integer delFlag;

    @ApiModelProperty(value = "是否需要业务负责人审批，默认0否，1，是，0，否")
    @TableField("ISNEED_BUSSINESS")
    private Integer isneedBussiness;

    @ApiModelProperty(value = "业务负责人id")
    @TableField("BUSSINESS_ID")
    private String bussinessId;

    @ApiModelProperty(value = "业务负责人姓名")
    @TableField("BUSSINESS_NAME")
    private String bussinessName;

    @ApiModelProperty(value = "紧急程度，默认0，一般")
    @TableField("URGENT_LEVEL")
    private String urgentLevel;

    @ApiModelProperty(value = "附件列表")
    @TableField(exist = false)
    private List<OaAttachment> attachments;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_DATETIME")
    private LocalDateTime updateDatetime;

    @ApiModelProperty(value = "更新人姓名")
    @TableField("UPDATE_USER_NAME")
    private String updateUserName;

    @ApiModelProperty(value = "更新人id")
    @TableField("UPDATE_USER_ID")
    private String updateUserId;

    @ApiModelProperty(value = "审批状态，默认0，0，审核中，1，审批完成")
    private String approvalStatus;

    @ApiModelProperty(value = "流水号")
    @TableField("ORDER_NUM")
    private String orderNum;

    public static final String ID = "ID";

    public static final String TITLE = "TITLE";

    public static final String APPLY_DEPT_ID = "APPLY_DEPT_ID";

    public static final String APPLY_DEPT_NAME = "APPLY_DEPT_NAME";

    public static final String APPLY_USER_ID = "APPLY_USER_ID";

    public static final String APPLY_USER_NAME = "APPLY_USER_NAME";

    public static final String APPLY_DATETIME = "APPLY_DATETIME";

    public static final String DEL_FLAG = "DEL_FLAG";

    public static final String ISNEED_BUSSINESS = "ISNEED_BUSSINESS";

    public static final String BUSSINESS_ID = "BUSSINESS_ID";

    public static final String BUSSINESS_NAME = "BUSSINESS_NAME";

    public static final String URGENT_LEVEL = "URGENT_LEVEL";

    public static final String APPROVAL_STATUS = "APPROVAL_STATUS";

    public static final String UPDATE_DATETIME = "UPDATE_TIME";

    public static final String UPDATE_USER_NAME = "UPDATE_USER_NAME";

    public static final String UPDATE_USER_ID = "UPDATE_USER_ID";

    public static final String ORDER_NUM = "ORDER_NUM";
    
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
