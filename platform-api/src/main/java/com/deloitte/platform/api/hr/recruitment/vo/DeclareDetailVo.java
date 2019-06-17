package com.deloitte.platform.api.hr.recruitment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeclareDetailVo {

    @ApiModelProperty(value = "员工对象")
    private DeclareBaseVo declareBaseVo;
}
