package com.deloitte.platform.api.srpmp.outline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-15
 * @Description : SrpmsOutlineAward返回的Form对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineAwardFormList")
@Data
public class SrpmsOutlineAwardFormList {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题录-奖励-新增列表")
    private List<SrpmsOutlineAwardForm> addOutlineList;

    @ApiModelProperty(value = "题录-奖励-删除列表")
    private List<SrpmsOutlineAwardForm> deleteOutlineList;

}
