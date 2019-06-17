package com.deloitte.platform.api.oaservice.notice.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.api.oaservice.noticeper.vo.OaNoticePermissionVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description : OaCalender返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OaNoticeOtherVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键")
    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "应用类型code")
    private String appTypeCode;

    @ApiModelProperty(value = "应用类型名称")
    private String appTypeName;

    @ApiModelProperty(value = "申请部门code")
    private String applyOrgCode;

    @ApiModelProperty(value = "申请部门名称")
    private String applyOrgName;

    @ApiModelProperty(value = "申请人员id")
    private String applyUserId;

    @ApiModelProperty(value = "申请人员名称")
    private String applyUserName;

    @ApiModelProperty(value = "申请日期，默认当前日期")
    private LocalDateTime applyDatetime;

    @ApiModelProperty(value = "紧急程度，默认0，一般")
    private String urgentLevel;

    @ApiModelProperty(value = "是否删除，默认0否，1，是，0，否")
    private Integer delFlag;

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

    @ApiModelProperty(value = "附件列表")
    private List<OaAttachmentVo> attachments;

    @ApiModelProperty(value = "流水号")
    private String orderNum;

    @ApiModelProperty(value = "申请单位code")
    private String applyDeptCode;

    @ApiModelProperty(value = "点击量")
    private Long noticeHit;

    @ApiModelProperty(value = "部门权限列表")
    private List<OaNoticePermissionVo> permissionDepts;

    @ApiModelProperty(value = "单位权限: inter 内部单位, outer 外部单位")
    private String deptPer;

}
