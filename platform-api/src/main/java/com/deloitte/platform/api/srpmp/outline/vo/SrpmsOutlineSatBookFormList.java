package com.deloitte.platform.api.srpmp.outline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-15
 * @Description : SrpmsOutlineSatBook返回的FORM对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineSatBookFormList")
@Data
public class SrpmsOutlineSatBookFormList {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题录-科技著作-新增列表")
    private List<SrpmsOutlineSatBookForm> addOutlineList;

    @ApiModelProperty(value = "题录-科技著作-删除列表")
    private List<SrpmsOutlineSatBookForm> deleteOutlineList;

}
