package com.deloitte.platform.api.dss.scientific.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 科研成果论文数量展示
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HarvestPaperVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位code")
    private String deptCode;

    @ApiModelProperty(value = "单位名称")
    private String deptName;

    @ApiModelProperty(value = "总数量")
    private Long totalNum;

    @ApiModelProperty(value = "sci数量")
    private Long sciNum;

    @ApiModelProperty(value = "IF数量")
    private Long ifNum;
}
