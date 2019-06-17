package com.deloitte.platform.api.oaservice.applycenter.vo;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BpmProcessVo extends BpmProcessTaskVo {

    @ApiModelProperty(value = "金额")
    private String money;
}
