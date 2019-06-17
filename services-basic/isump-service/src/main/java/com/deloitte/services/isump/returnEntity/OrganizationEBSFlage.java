package com.deloitte.services.isump.returnEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangdi
 * @Date 29/05/2019
 */
@Data
@ApiModel(value="OrgizationEBSFlage对象", description="用于组织信息对接ebs接口数据")
public class OrganizationEBSFlage {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "组织编码")
    private String code ;

    @ApiModelProperty(value = "组织名称")
    private String  name;

    @ApiModelProperty(value = "报账系统回写的详细错误信息")
    private String errorMessage ;
}
