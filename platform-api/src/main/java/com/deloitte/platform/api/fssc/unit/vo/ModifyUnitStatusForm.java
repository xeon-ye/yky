package com.deloitte.platform.api.fssc.unit.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class ModifyUnitStatusForm {
    @ApiModelProperty(value = "id集合")
    private List<Long> ids;
    @ApiModelProperty(value = "修改之后的状态,即需要修改到什么状态 无效:N 有效:Y 已提交:SUBMIT 新建:NEW")
    private String status;
    @ApiModelProperty(value = "拒绝原因")
    private String refusedReason;
    @ApiModelProperty(value = "通过还是拒绝  通过:PASS  拒绝:REFUSED")
    private String passOrRefused;
}
