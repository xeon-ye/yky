package com.deloitte.platform.api.dss.scientific.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ResearchProjectInPlaceFundVo
 * @Description:到位经费
 * @Auther: wangyanyun
 * @Date: 2019-03-11
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ResearchProjectInPlaceFundVo {

    @ApiModelProperty(value = "项目类别")
    private String category;

    @ApiModelProperty(value = "当年经费")
    private Double thatYearFund;

    @ApiModelProperty(value = "去年经费")
    private Double lastYearFund;

    @ApiModelProperty(value = "前年经费")
    private Double yearBeforeLastFund;

    @ApiModelProperty(value = "延续项目经费")
    private Double continuationProjectFund;

    @ApiModelProperty(value = "新获项目经费")
    private Double newProjectFund;

  @ApiModelProperty(value = "结题项目经费")
    private Double conclusionProjectFund;

    @ApiModelProperty(value = "在研项目经费")
    private Double researchProjectFund;
    @ApiModelProperty(value = "单位ID")
    private long deptId;


}
