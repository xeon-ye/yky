package com.deloitte.platform.api.fssc.rep.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description :  RecieveLineMsg查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecieveLineMsgQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("单据编号")
    private String documentNum;
    @ApiModelProperty("款项大类id")
    private Long inComeMainTypeId;
    @ApiModelProperty("款项小类id")
    private Long inComeSubTypeId;
    @ApiModelProperty(value = "付款单位id")
    private Long payUnitId;
    @ApiModelProperty(value = "收款单位id")
    private Long recieveUnitId;
    @ApiModelProperty("收款时间开始")
    private LocalDateTime startTime;
    @ApiModelProperty("收款时间结束")
    private LocalDateTime endTime;
    @ApiModelProperty("部门id")
    private Long deptId;
}
