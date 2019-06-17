package com.deloitte.platform.api.hr.retireRehiring.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description : RetireApply返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetireApplyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "备注/退休原由")
    private String remark;

    @ApiModelProperty(value = "0保存,1提交")
    private String status;

    @ApiModelProperty(value = "审批转态(保留字段)")
    private String approval;

    @ApiModelProperty(value = "1.人力资源处,2.机关职工-处级,副高级女干部,3.所院高级干部")
    private String retireType;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long createBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private Double updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "经办人员工编号")
    private String managerCode;

    @ApiModelProperty(value = "附件对象数组")
    private List<HrAttachmentVo> attachmentVo;

    @ApiModelProperty(value = "申请人员列表")
    private List<RemindRecordVo> remindRecordVos;
}
