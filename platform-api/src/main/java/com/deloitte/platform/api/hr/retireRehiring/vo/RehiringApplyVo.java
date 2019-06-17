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
 * @Description : RetireRehiringApply返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RehiringApplyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "返聘原因")
    private String rehiringReason;

    @ApiModelProperty(value = "备用字段(审批转态)")
    private String approval;

    @ApiModelProperty(value = "经办人员工编号")
    private String managerCode;

    @ApiModelProperty(value = "0保存,1.提交")
    private String status;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long createBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "1.部门年度申请,2.部门月度申请,3人力资源处申请")
    private String applyType;

    @ApiModelProperty(value = "附件对象数组")
    private List<HrAttachmentVo> attachmentVo;

    @ApiModelProperty(value = "申请中人员列表")
    private List<RemindRecordVo> remindRecordVo;


}
