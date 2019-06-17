package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :   CheckRelationContent查询from对象
 * @Modified :
 */
@ApiModel("CheckRelationContent查询表单")
@Data
public class CheckRelationContentQueryForm extends BaseQueryForm<CheckRelationContentQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "考核关系id")
    private String checkRelationId;

    @ApiModelProperty(value = "被评价人分组id")
    private String checkBeAppraiserId;

    @ApiModelProperty(value = "评价人分组id")
    private String checkAppraiserId;

    @ApiModelProperty(value = "评价人权重")
    private Long appraiserWeight;

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
