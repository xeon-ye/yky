package com.deloitte.platform.api.hr.vacation.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-02
 * @Description : VacationTrain新增修改form对象
 * @Modified :
 */
@ApiModel("新增VacationTrain表单")
@Data
public class VacationTrainForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "休假类型")
    private String leaveType;

    @ApiModelProperty(value = "请假开始日期")
    private LocalDateTime leaveStartDate;

    @ApiModelProperty(value = "请假结束日期")
    private LocalDateTime leaveEndDate;

    @ApiModelProperty(value = "是否销假 1 销假 2未销假")
    private String iscancel;

    @ApiModelProperty(value = "审批状态")
    private String approvalState;

    @ApiModelProperty(value = "教职工编码")
    private String teachNum;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "流程单号")
    private String processNum;

    @ApiModelProperty(value = "员工唯一标识")
    private String employeeId;

    @ApiModelProperty(value = "休假天数")
    private Long days;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentForm> attachments;

    @ApiModelProperty(value = "主键Id")
    private String id;

    @ApiModelProperty(value = "处级级别")
    private String chiefRank;

    private String explain;

}
