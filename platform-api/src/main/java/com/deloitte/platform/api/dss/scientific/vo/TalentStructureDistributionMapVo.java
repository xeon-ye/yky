package com.deloitte.platform.api.dss.scientific.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @ClassName: TalentStructureDistributionMapVo
 * @Description:
 * @Auther: wangyanyun
 * @Date: 2019-03-11
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class TalentStructureDistributionMapVo {

   private Integer totalNum;//总数
   private Integer Low45Num;//小于等于45岁
   private Integer over45Num;//大于45岁
   private Integer highLever;//高级人才
   private Integer midLever;//中级人才
   private Integer lowLever;//初级人才
   private Integer otherOne;//其他人才
   private Integer doctor;//博士
   private Integer master;//硕士
   private Integer undergraduate;//本科
   private Integer otherTwo;//其他
   private Integer researcher;//研究人员
   private Integer technicist;//技术人员
   private Integer postDoctor;//博士后
   private String deptName;//依托单位
   private Integer innovate;//创新工程
   private Integer academy;//院基科费
   private Integer school;//校基科费
   private Integer reform;//改革经费
   private Integer national;//国家级
   private Integer provincial;//省部级
   private Integer transverse;//横向
   private Integer other;//其他
}
