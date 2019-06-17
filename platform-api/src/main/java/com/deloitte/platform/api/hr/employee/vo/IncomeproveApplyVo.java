package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-08
 * @Description : IncomeproveApply返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeproveApplyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "用户表唯一标识")
    private String userid;

    @ApiModelProperty(value = "申请原因")
    private String applyReason;

    @ApiModelProperty(value = "申请单位")
    private String applyDep;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "备注")
    private String remake;

    @ApiModelProperty(value = "签字盖章")
    private String sign;

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

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "流程编号")
    private String processNum;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentVo> attachments;

    @ApiModelProperty(value = "使用标准模板 0不使用 1使用")
    private String worktemplate;

    @ApiModelProperty(value = "身份证")
    private String idNumber;

    @ApiModelProperty(value = "开始工作时间")
    private LocalDateTime startWorkTime;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "员工名字")
    private String empName;

    @ApiModelProperty(value = "申请名称")
    private String applyName;

}
