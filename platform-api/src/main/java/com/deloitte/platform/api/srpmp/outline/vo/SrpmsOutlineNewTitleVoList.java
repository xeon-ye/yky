package com.deloitte.platform.api.srpmp.outline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-15
 * @Description : SrpmsOutlineNewTitle返回的VO对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineNewTitleFormList")
@Data
public class SrpmsOutlineNewTitleVoList {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题录-新获项目-新增列表")
    private List<SrpmsOutlineNewTitleVo> srpmsOutlineVoList;

    @ApiModelProperty(value = "总页数")
    private int totalPageNum;

    @ApiModelProperty(value = "当前页")
    private int currentPageNum;

}
