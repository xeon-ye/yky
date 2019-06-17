package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-13
 * @Description :   GccFundsProject查询from对象
 * @Modified :
 */
@ApiModel("GccFundsProject查询表单")
@Data
public class GccFundsProjectQueryForm extends BaseQueryForm<GccFundsProjectQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "申报项目名称")
    private String projectName;

    @ApiModelProperty(value = "申报项目编号")
    private Long projectId;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "是否项目拨款")
    private String projectFunding;

    @ApiModelProperty(value = "申请情况")
    private String apply;

    @ApiModelProperty(value = "经费到账")
    private String fundsAccount;

    @ApiModelProperty(value = "到账金额")
    private String arrivalAmount;

    @ApiModelProperty(value = "到账时间")
    private LocalDateTime accountingDate;

    @ApiModelProperty(value = "拨款情况")
    private String fundingSituation;

    @ApiModelProperty(value = "拨款金额")
    private String allocateFunds;

    @ApiModelProperty(value = "拨款时间")
    private LocalDateTime allocateDate;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;
}
