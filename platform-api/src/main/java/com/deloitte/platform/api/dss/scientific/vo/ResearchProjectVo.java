package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @ClassName: ResearchProjectVo
 * @Description: 科研项目
 * @Auther: wangyanyun
 * @Date: 2019-02-28
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ResearchProjectVo extends BaseVo {


    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "项目数量")
    private Double number;

    @ApiModelProperty(value = "项目类别")
    private String category;

    @ApiModelProperty(value = "项目数量比")
    private String quantityRatio;

    @ApiModelProperty(value = "金额")
    private Double money;

    @ApiModelProperty(value = "金额比")
    private String amountRatio;




}
