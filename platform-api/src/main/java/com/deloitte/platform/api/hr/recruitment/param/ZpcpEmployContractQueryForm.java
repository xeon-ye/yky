package com.deloitte.platform.api.hr.recruitment.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description :   ZpcpEmployContract查询from对象
 * @Modified :
 */
@ApiModel("ZpcpEmployContract查询表单")
@Data
public class ZpcpEmployContractQueryForm extends BaseQueryForm<ZpcpEmployContractQueryParam>  {

    @ApiModelProperty(value = "教职工编号")
    private Long empCode;

    @ApiModelProperty(value = "基本信息id")
    private Long infoId;

   /*
   @ApiModelProperty(value = "主键")
    private Long id;

   @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "所属单位")
    private String unit;

    @ApiModelProperty(value = "岗位")
    private String post;

    @ApiModelProperty(value = "合同类型")
    private String contractType;

    @ApiModelProperty(value = "合同开始日期")
    private LocalDateTime contractStartdate;

    @ApiModelProperty(value = "合同结束日期")
    private LocalDateTime contractEnddate;

    @ApiModelProperty(value = "聘用时长")
    private String appointmentTime;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;*/
}
