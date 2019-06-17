package com.deloitte.platform.api.fssc.engine.manual.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @Author : chenx
 * @Date : Create in 2019-04-20
 * @Description : 主要运用会计引擎逻辑判断解析使用
 * @Modified :
 */
@Data
public class AvJudgementVo {
    @ApiModelProperty(value = "字段和表名")
    private String field;
    @ApiModelProperty(value = "判断条件")
    private String judgment;
    @ApiModelProperty(value = "值")
    private String value;
    @ApiModelProperty(value = "与下一级的关系")
    private String retaltion;
}
