package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 科研项目及到位经费分布 项目规模
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionalAgeVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "总人数")
    private Long totalNum;

    @ApiModelProperty(value = "45岁以上的人数")
    private Long over45Num;


    @ApiModelProperty(value = "45岁以下人数")
    private Long low45Num;


    @ApiModelProperty(value = "高级人数")
    private Long highNum;

}
