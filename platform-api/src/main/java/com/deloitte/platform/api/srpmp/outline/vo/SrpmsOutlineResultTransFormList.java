package com.deloitte.platform.api.srpmp.outline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-15
 * @Description : SrpmsOutlineResultTrans返回的FORM对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineResultTransFormList")
@Data
public class SrpmsOutlineResultTransFormList {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题录-成果转化-新增列表")
    private List<SrpmsOutlineResultTransForm> addOutlineList;

    @ApiModelProperty(value = "题录-成果转化-删除列表")
    private List<SrpmsOutlineResultTransForm> deleteOutlineList;

}
