package com.deloitte.platform.api.oaservice.notice.vo;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
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
 * @Description : OaMeetingRecord返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OaMeetingRecordVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会议纪要ID，主键")
    private String id;

    @ApiModelProperty(value = "会议纪要标题")
    private String title;

    @ApiModelProperty(value = "申请部门id")
    private String applyDeptId;

    @ApiModelProperty(value = "申请部门名称")
    private String applyDeptName;

    @ApiModelProperty(value = "申请人员id")
    private String applyUserId;

    @ApiModelProperty(value = "申请人员名称")
    private String applyUserName;

    @ApiModelProperty(value = "申请日期，默认当前日期")
    private LocalDateTime applyDatetime;

    @ApiModelProperty(value = "是否删除，默认0否，1，是，0，否")
    private Integer delFlag;

    @ApiModelProperty(value = "是否需要业务负责人审批，默认0否，1，是，0，否")
    private Integer isneedBussiness;

    @ApiModelProperty(value = "业务负责人id")
    private String bussinessId;

    @ApiModelProperty(value = "业务负责人姓名")
    private String bussinessName;

    @ApiModelProperty(value = "紧急程度，默认0，一般")
    private String urgentLevel;

    @ApiModelProperty(value = "审批状态，默认0，0，审判中，1，审批完成")
    private String approvalStatus;

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

}
