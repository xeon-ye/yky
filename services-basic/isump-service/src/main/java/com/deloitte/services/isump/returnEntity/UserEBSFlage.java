package com.deloitte.services.isump.returnEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangdi
 * @Date 29/05/2019
 */
@Data
@ApiModel(value="UserEBSFlage对象", description="用于员工信息对接ebs接口数据")
public class UserEBSFlage {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工编码")
    private String empNo ;

    @ApiModelProperty(value = "身份证号")
    private String  idCard;

    @ApiModelProperty(value = "报账系统回写的详细错误信息")
    private String errorMessage ;
}
