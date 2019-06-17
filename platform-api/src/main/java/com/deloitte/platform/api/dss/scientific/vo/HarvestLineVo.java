package com.deloitte.platform.api.dss.scientific.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 科研成果折线数据展示vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HarvestLineVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "年份")
    private String yaerValue;

    @ApiModelProperty(value = "论文数量")
    private Long paperNum;

    @ApiModelProperty(value = "成果转化数量")
    private Long resultNum;

    @ApiModelProperty(value = "专利数量")
    private Long patentNum;

}
