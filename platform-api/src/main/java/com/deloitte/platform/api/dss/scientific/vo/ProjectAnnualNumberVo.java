package com.deloitte.platform.api.dss.scientific.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ProjectCategory
 * @Description:
 * @Auther: wangyanyun
 * @Date: 2019-03-05
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAnnualNumberVo {
    @ApiModelProperty(value = "院校级")
    private Integer academy;
    @ApiModelProperty(value = "国家级")
    private Integer national;
    @ApiModelProperty(value = "省部级")
    private Integer provincial;
    @ApiModelProperty(value = "横向")
    private Integer transverse;
    @ApiModelProperty(value = "其他")
    private Integer other;
    @ApiModelProperty(value = "项目年度总数")
    private Integer totalNum;
    @ApiModelProperty(value = "年度")
    private String annual;
}
