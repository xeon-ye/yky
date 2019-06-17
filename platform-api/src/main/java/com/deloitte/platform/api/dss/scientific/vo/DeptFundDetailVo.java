package com.deloitte.platform.api.dss.scientific.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 单位项目经费执行率展示Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptFundDetailVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目Id")
    private Long projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "明细执行率json")
    private String deptFundDetail;

    @ApiModelProperty(value = "均值")
    private Double average;

}
