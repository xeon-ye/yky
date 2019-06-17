package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccOnjobInformation查询from对象
 * @Modified :
 */
@ApiModel("GccOnjobInformation查询表单")
@Data
public class GccOnjobInformationQueryForm extends BaseQueryForm<GccOnjobInformationQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "编号")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "所属人才项目")
    private Long projectId;

    @ApiModelProperty(value = "人才项目状态")
    private String projectStatus;

    @ApiModelProperty(value = "人才项目类型")
    private Long projectTypeId;

    @ApiModelProperty(value = "人才项目申请时间")
    private LocalDateTime projectApplyTime;

    @ApiModelProperty(value = "到岗时间")
    private LocalDateTime onjobTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

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
