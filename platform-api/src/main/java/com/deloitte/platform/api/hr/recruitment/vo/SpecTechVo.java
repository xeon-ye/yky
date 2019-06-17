package com.deloitte.platform.api.hr.recruitment.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecTechVo extends BaseVo {

    @ApiModelProperty(value = "字段key")
    private String value;

    @ApiModelProperty(value = "字段名字")
    private String label;

    @ApiModelProperty(value = "二级对象")
    private List<SpecTechVo> children;

}
