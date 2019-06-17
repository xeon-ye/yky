package com.deloitte.platform.api.hr.vacation.param;
import com.deloitte.platform.api.hr.common.util.DateFarmatUtil;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-02
 * @Description :   VacationTrain查询from对象
 * @Modified :
 */
@ApiModel("VacationTrain查询表单")
@Data
public class VacationTrainQueryForm extends BaseQueryForm<VacationTrainQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

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

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "部门编码")
    private String depCode;

    @ApiModelProperty(value = "岗位编码")
    private String positionCode;

    @ApiModelProperty(value = "处级级别")
    private String chiefRank;

    private String name;
}
