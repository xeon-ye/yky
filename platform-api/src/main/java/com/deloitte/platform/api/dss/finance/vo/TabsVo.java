package com.deloitte.platform.api.dss.finance.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : chitose
 * @Date : Create in 2019-05-06
 * @Description : Tabs数据
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TabsVo extends BaseVo {



    @ApiModelProperty(value = "描述")
    private String des;

    @ApiModelProperty(value = "颜色")
    private String color;

    @ApiModelProperty(value = "小值")
    private BigDecimal left;

    @ApiModelProperty(value = "大值")
    private BigDecimal right;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "机构编码")
    private String comCode;

    @ApiModelProperty(value = "机构名称")
    private String comDes;

    @ApiModelProperty(value = "年累计收入")
    private BigDecimal ytd;

    @ApiModelProperty(value = "当月收入")
    private BigDecimal ptd;

    @ApiModelProperty(value = "组织明细")
    private List<TabsVo> tabsVoList;




}
