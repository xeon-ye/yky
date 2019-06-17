package com.deloitte.platform.api.oaservice.notice.vo;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.api.oaservice.noticeper.vo.OaNoticePermissionVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description : OaNotice返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OaNoticeVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "通知公告id，主键")
    private String id;

    @ApiModelProperty(value = "通知公告标题")
    private String title;

    @ApiModelProperty(value = "紧急程度")
    private String urgentLevel;

    @ApiModelProperty(value = "通知公告分类Code，取自字典")
    private String noticeTypeCode;

    @ApiModelProperty(value = "通知公告分类，取自字典，例：内部公告、院校公告")
    private String noticeTypeName;

    @ApiModelProperty(value = "申请部门Code")
    private String applyOrgCode;

    @ApiModelProperty(value = "申请部门")
    private String applyOrgName;

    @ApiModelProperty(value = "申请人ID")
    private String applyUserId;

    @ApiModelProperty(value = "申请人")
    private String applyUserName;

    @ApiModelProperty(value = "申请时间")
    private LocalDateTime applyDatetime;

    @ApiModelProperty(value = "公告类别Code，取自字典")
    private String noticeSortCode;

    @ApiModelProperty(value = "公告类别，取自字典，例：行政通知、人事任免、会议通知、规章制度通知、其他通知公告")
    private String noticeSortName;

    @ApiModelProperty(value = "点击量")
    private Long noticeHit;

    @ApiModelProperty(value = "公告正文")
    private String noticeContent;

    @ApiModelProperty(value = "是否删除，默认0， 0，否，1，是")
    private Integer delFlag;

    @ApiModelProperty(value = "附件列表")
    private List<OaAttachmentVo> attachments;

    @ApiModelProperty(value = "审批状态，默认0，0，审核中，1，审批完成")
    private String approvalStatus;

    @ApiModelProperty(value = "是否需要业务负责人审批，默认0否，1，是，0，否")
    private Integer isneedBussiness;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDatetime;

    @ApiModelProperty(value = "更新人姓名")
    private String updateUserName;

    @ApiModelProperty(value = "更新人id")
    private String updateUserId;

    @ApiModelProperty(value = "流水号")
    private String orderNum;

    @ApiModelProperty(value = "申请单位code")
    private String applyDeptCode;

    @ApiModelProperty(value = "部门权限列表")
    private List<OaNoticePermissionVo> permissionDepts;

    @ApiModelProperty(value = "单位权限: inter 内部单位, outer 外部单位")
    private String deptPer;

}
