package com.deloitte.platform.api.srpmp.outline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-15
 * @Description : SrpmsOutlineAcaExcForm返回的from对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineAcaExcFormList")
@Data
public class SrpmsOutlineAcaExcFormList {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题录-学术交流-新增列表")
    private List<SrpmsOutlineAcaExcForm> addOutlineList;

    @ApiModelProperty(value = "题录-学术交流-删除列表")
    private List<SrpmsOutlineAcaExcForm> deleteOutlineList;

}
