package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccTalentProject查询from对象
 * @Modified :
 */
@ApiModel("GccTalentProject查询表单")
@Data
public class GccTalentProjectQueryForm extends BaseQueryForm<GccTalentProjectQueryParam>  {

    @ApiModelProperty(value = "项目性质")
    private String projectNature;

    @ApiModelProperty(value = "项目级别")
    private String projectLevel;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "是否有效 0 否，1是")
    private String status;

    @ApiModelProperty(value = "主管部门")
    private Long departmentCode;

    @ApiModelProperty(value = "是否需要申报 0否 1是")
    private String declare;

   /* @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "编号")
    private Long code;




    @ApiModelProperty(value = "是否需要考核  0否，1是")
    private String assessed;

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
    private Long updateBy;

    @ApiModelProperty(value = "是否拨付0 否，1是")
    private String allocated;

    @ApiModelProperty(value = "拨付时间")
    private LocalDateTime allocatedTime;

    @ApiModelProperty(value = "到账时间")
    private String accoutTime;

    @ApiModelProperty(value = "是否到账0 否，1是")
    private String whetherAccount;

    @ApiModelProperty(value = "项目简称")
    private String shortName;
*/

}
