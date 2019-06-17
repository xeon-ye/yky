package com.deloitte.platform.api.srpmp.outline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-15
 * @Description : SrpmsOutlineNewTitle返回的FORM对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineErrorList")
@Data
public class SrpmsOutlineErrorList {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题录-错误数据列表")
    private List listError;

    @ApiModelProperty(value = "题录-点击保存返回错误信息")
    private String errorMsg;

    @ApiModelProperty(value = "题录-返回错误状态")
    private boolean errorFlag;

}
