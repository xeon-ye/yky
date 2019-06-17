package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ResultColumnVo extends BaseVo{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "论文")
    private String paper;
    @ApiModelProperty(value = "成果")
    private String result;
    @ApiModelProperty(value = "依托单位")
    private String addres;
    @ApiModelProperty(value = "专利")
    private String patent;
    @ApiModelProperty(value = "总数")
    private String height;
    @ApiModelProperty(value = "依托单位id")
    private String code;
}
