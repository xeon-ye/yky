package com.deloitte.services.notice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.sql.Clob;
import java.io.Serializable;
import java.util.List;

import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.noticeper.entity.OaNoticePermission;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通知公告表
 * </p>
 *
 * @author fuqiao
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_NOTICE")
@ApiModel(value="OaNotice对象", description="通知公告表")
public class OaNotice extends Model<OaNotice> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "通知公告id，主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "通知公告标题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty(value = "紧急程度")
    @TableField("URGENT_LEVEL")
    private String urgentLevel;

    @ApiModelProperty(value = "通知公告分类Code，取自字典")
    @TableField("NOTICE_TYPE_CODE")
    private String noticeTypeCode;

    @ApiModelProperty(value = "通知公告分类，取自字典，例：内部公告、院校公告")
    @TableField("NOTICE_TYPE_NAME")
    private String noticeTypeName;

    @ApiModelProperty(value = "申请部门Code")
    @TableField("APPLY_ORG_CODE")
    private String applyOrgCode;

    @ApiModelProperty(value = "申请部门")
    @TableField("APPLY_ORG_NAME")
    private String applyOrgName;

    @ApiModelProperty(value = "申请人ID")
    @TableField("APPLY_USER_ID")
    private String applyUserId;

    @ApiModelProperty(value = "申请人")
    @TableField("APPLY_USER_NAME")
    private String applyUserName;

    @ApiModelProperty(value = "申请时间")
    @TableField("APPLY_DATETIME")
    private LocalDateTime applyDatetime;

    @ApiModelProperty(value = "公告类别Code，取自字典")
    @TableField("NOTICE_SORT_CODE")
    private String noticeSortCode;

    @ApiModelProperty(value = "公告类别，取自字典，例：行政通知、人事任免、会议通知、规章制度通知、其他通知公告")
    @TableField("NOTICE_SORT_NAME")
    private String noticeSortName;

    @ApiModelProperty(value = "点击量")
    @TableField("NOTICE_HIT")
    private Long noticeHit;

    @ApiModelProperty(value = "公告正文")
    @TableField("NOTICE_CONTENT")
    private String noticeContent;

    @ApiModelProperty(value = "是否删除，默认0， 0，否，1，是")
    @TableField("DEL_FLAG")
    private Integer delFlag;

    @ApiModelProperty(value = "附件列表")
    @TableField(exist = false)
    private List<OaAttachment> attachments;

    @ApiModelProperty(value = "审批状态，默认0，0，审核中，1，审批完成")
    @TableField("APPROVAL_STATUS")
    private String approvalStatus;

    @ApiModelProperty(value = "是否需要业务负责人审批，默认0否，1，是，0，否")
    @TableField("ISNEED_BUSSINESS")
    private Integer isneedBussiness;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_DATETIME")
    private LocalDateTime updateDatetime;

    @ApiModelProperty(value = "更新人姓名")
    @TableField("UPDATE_USER_NAME")
    private String updateUserName;

    @ApiModelProperty(value = "更新人id")
    @TableField("UPDATE_USER_ID")
    private String updateUserId;

    @ApiModelProperty(value = "流水号")
    @TableField("ORDER_NUM")
    private String orderNum;

    @ApiModelProperty(value = "申请单位code")
    @TableField("APPLY_DEPT_CODE")
    private String applyDeptCode;

    @ApiModelProperty(value = "部门权限列表")
    @TableField(exist = false)
    private List<OaNoticePermission> permissionDepts;

    @ApiModelProperty(value = "单位权限: inter 内部单位, outer 外部单位")
    @TableField("DEPT_PER")
    private String deptPer;

    public static final String ID = "ID";

    public static final String TITLE = "TITLE";

    public static final String URGENT_LEVEL = "URGENT_LEVEL";

    public static final String NOTICE_TYPE_CODE = "NOTICE_TYPE_CODE";

    public static final String NOTICE_TYPE_NAME = "NOTICE_TYPE_NAME";

    public static final String APPLY_ORG_CODE = "APPLY_ORG_CODE";

    public static final String APPLY_ORG_NAME = "APPLY_ORG_NAME";

    public static final String APPLY_USER_ID = "APPLY_USER_ID";

    public static final String APPLY_USER_NAME = "APPLY_USER_NAME";

    public static final String APPLY_DATETIME = "APPLY_DATETIME";

    public static final String NOTICE_SORT_CODE = "NOTICE_SORT_CODE";

    public static final String NOTICE_SORT_NAME = "NOTICE_SORT_NAME";

    public static final String NOTICE_HIT = "NOTICE_HIT";

    public static final String NOTICE_CONTENT = "NOTICE_CONTENT";

    public static final String DEL_FLAG = "DEL_FLAG";

    public static final String APPROVAL_STATUS = "APPROVAL_STATUS";

    public static final String ISNEED_BUSSINESS = "ISNEED_BUSSINESS";

    public static final String UPDATE_DATETIME = "UPDATE_TIME";

    public static final String UPDATE_USER_NAME = "UPDATE_USER_NAME";

    public static final String UPDATE_USER_ID = "UPDATE_USER_ID";

    public static final String ORDER_NUM = "ORDER_NUM";

    public static final String APPLY_DEPT_CODE = "APPLY_DEPT_CODE";

    public static final String DEPT_PER = "DEPT_PER";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
