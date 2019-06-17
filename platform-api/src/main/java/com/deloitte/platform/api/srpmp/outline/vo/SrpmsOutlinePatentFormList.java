package com.deloitte.platform.api.srpmp.outline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-15
 * @Description : SrpmsOutlinePatent返回的FORM对象
 * @Modified :
 */
@ApiModel("SrpmsOutlinePatentFormList")
@Data
public class SrpmsOutlinePatentFormList {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题录-专利-新增列表")
    private List<SrpmsOutlinePatentForm> addOutlineList;

    @ApiModelProperty(value = "题录-专利-删除列表")
    private List<SrpmsOutlinePatentForm> deleteOutlineList;

}
