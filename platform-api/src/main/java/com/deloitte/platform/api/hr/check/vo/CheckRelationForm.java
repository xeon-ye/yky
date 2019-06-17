package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckRelation新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckRelation表单")
@Data
public class CheckRelationForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核期间id")
    private Long checkTimeId;

    @ApiModelProperty(value = "考核工作id")
    private Long checkWorkId;

    @ApiModelProperty(value = "考核组织id")
    private Long checkOrgId;

    @ApiModelProperty(value = "考核关系名称")
    private String relationName;

    @ApiModelProperty(value = "考核表类型id")
    private Long checkTableTypeId;

    @ApiModelProperty(value = "考核模板id")
    private Long checkTemplateId;

    @ApiModelProperty(value = "排序")
    private Long orderNumber;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "考核关系列表内容")
    private List<CheckRelationContentForm> relationContentList;

}
