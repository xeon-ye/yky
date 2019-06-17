package com.deloitte.platform.api.hr.ebs.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;


/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :   EmployeeMesContract查询from对象
 * @Modified :
 */
@ApiModel("EmployeeMesContract查询表单")
@Data
public class EmployeeMesContractQueryForm extends BaseQueryForm<EmployeeMesContractQueryParam> {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "合同编号")
    private String conNo;

    @ApiModelProperty(value = "合同起始日期")
    private LocalDateTime conStartDate;

    @ApiModelProperty(value = "合同终止日期")
    private LocalDateTime conEndDate;

    @ApiModelProperty(value = "合同类型（1聘用合同，2固定期限劳动合同，3无固定期限劳动合同，4劳务协议，5劳务派遣协议）")
    private String conType;

    @ApiModelProperty(value = "用工类型（1事业单位编制内聘用，2劳动关系，3劳务协议，4兼职/借调，4退休返聘，5人才派遣）")
    private String empType;

    @ApiModelProperty(value = "合同期限")
    private String conPer;

    @ApiModelProperty(value = "合同状态（1进行中，2已结束）")
    private String status;

    @ApiModelProperty(value = "合同主体")
    private String conSub;

    @ApiModelProperty(value = "经费来源（1财政拨款，2经费自理）")
    private String souFund;

    @ApiModelProperty(value = "项目经费金额（元）")
    private Double projectFound;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private Long careteBy;

    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    @ApiModelProperty(value = "组织机构编号")
    private String orgCode;

    @ApiModelProperty(value = "人员信息CODE")
    private String userCode;
}
