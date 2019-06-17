package com.deloitte.platform.api.hr.ebs.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :   EmployeeMesWork查询from对象
 * @Modified :
 */
@ApiModel("EmployeeMesWork查询表单")
@Data
public class EmployeeMesWorkQueryForm extends BaseQueryForm<EmployeeMesWorkQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "头像地址")
    private String headPhoto;

    @ApiModelProperty(value = "开始日期")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束日期")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "工作单位")
    private String workUnit;

    @ApiModelProperty(value = "部门")
    private String department;

    @ApiModelProperty(value = "岗位/职务")
    private String departPostion;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "员工自助ID")
    private String empMesId;

    @ApiModelProperty(value = "流程申请ID")
    private String mesTid;
}
