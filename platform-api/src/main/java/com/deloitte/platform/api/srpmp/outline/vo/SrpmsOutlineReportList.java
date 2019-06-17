package com.deloitte.platform.api.srpmp.outline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-15
 * @Description : SrpmsOutlineNewTitle返回的VO对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineFormList")
@Data
public class SrpmsOutlineReportList extends SrpmsOutlineFormListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题录-组装对象first")
    private List<Map<String, Object>> firstCountList;

    @ApiModelProperty(value = "题录-组装对象second")
    private List<Map<String, Object>> secondCountList;

    @ApiModelProperty(value = "题录-组装对象three")
    private List<Map<String, Object>> threeCountList;

    @ApiModelProperty(value = "题录-组装对象four")
    private List<Map<String, Object>> fourCountList;

}
