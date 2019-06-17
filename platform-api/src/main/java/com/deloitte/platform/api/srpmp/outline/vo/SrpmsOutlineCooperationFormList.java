package com.deloitte.platform.api.srpmp.outline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-15
 * @Description : SrpmsOutlineCooperation返回的FROM对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineCooperationFormList")
@Data
public class SrpmsOutlineCooperationFormList {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题录-国合项目-新增列表")
    private List<SrpmsOutlineCooperationForm> addOutlineList;

    @ApiModelProperty(value = "题录-国合项目-删除列表")
    private List<SrpmsOutlineCooperationForm> deleteOutlineList;

}
