package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :   CheckTemplate查询from对象
 * @Modified :
 */
@ApiModel("CheckTemplate查询表单")
@Data
public class CheckTemplateQueryForm extends BaseQueryForm<CheckTemplateQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "考核模板名称")
    private String checkTemplateName;

    @ApiModelProperty(value = "考核表类型")
    private String checkTableTypeId;

    @ApiModelProperty(value = "评估方式")
    private String assessMode;


    @ApiModelProperty(value = "权重")
    private Long weight;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
