package com.deloitte.platform.api.dss.scientific.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 科研项目，立项项目成员饼状图
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ProjectPersonPieChartVo {

   @ApiModelProperty("总人数")
   private Integer totalNum;

   @ApiModelProperty("小于等于45岁")
   private Integer Low45Num;

   @ApiModelProperty("大于45岁")
   private Integer over45Num;

   @ApiModelProperty("高级人才")
   private Integer highLever;

   @ApiModelProperty("中级人才")
   private Integer midLever;

   @ApiModelProperty("初级人才")
   private Integer lowLever;

   @ApiModelProperty("其他人才")
   private Integer otherOne;

}
