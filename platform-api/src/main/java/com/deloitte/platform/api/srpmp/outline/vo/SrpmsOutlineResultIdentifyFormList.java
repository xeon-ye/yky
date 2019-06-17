package com.deloitte.platform.api.srpmp.outline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-15
 * @Description : SrpmsOutlineResultIdentify返回的FORM对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineResultIdentifyFormList")
@Data
public class SrpmsOutlineResultIdentifyFormList extends SrpmsOutlineFormListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题录-成果鉴定-新增列表")
    private List<SrpmsOutlineResultIdentifyForm> addOutlineList;

    @ApiModelProperty(value = "题录-成果鉴定-删除列表")
    private List<SrpmsOutlineResultIdentifyForm> deleteOutlineList;

}
