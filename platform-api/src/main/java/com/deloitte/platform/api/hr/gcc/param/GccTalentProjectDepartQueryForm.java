package com.deloitte.platform.api.hr.gcc.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccTalentProjectDepart查询from对象
 * @Modified :
 */
@ApiModel("GccTalentProjectDepart查询表单")
@Data
public class GccTalentProjectDepartQueryForm extends BaseQueryForm<GccTalentProjectDepartQueryParam>  {
    @ApiModelProperty(value = "名称")
    private String departName;

    @ApiModelProperty(value = "是否有效")
    private String status;

   /*
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "编号")
    private Long code;


    @ApiModelProperty(value = "简称")
    private String shortName;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "联系电话")
    private String telephone;


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
    private Long updateBy;*/
}
