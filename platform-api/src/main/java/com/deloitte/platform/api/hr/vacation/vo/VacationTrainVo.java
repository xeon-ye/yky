package com.deloitte.platform.api.hr.vacation.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-02
 * @Description : VacationTrain返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationTrainVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

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
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "流程单号")
    private String processNum;

    @ApiModelProperty(value = "员工唯一标识")
    private String employeeId;

    @ApiModelProperty(value = "休假天数")
    private Long days;

    @ApiModelProperty(value = "附件名称")
    private String fileName;

    @ApiModelProperty(value = "附件存储地址")
    private String fileUrl;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "部门")
    private String depCode;

    @ApiModelProperty(value = "职位")
    private String positionCode;

    @ApiModelProperty(value = "教职工ID")
    private String empCode;

    @ApiModelProperty(value = "处级级别")
    private String chiefRank;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentVo> attachments;

    @ApiModelProperty(value = "说明")
    private String explain;

    @ApiModelProperty(value = "1 处长以下 2 处长")
    private String flag;

}
