package com.deloitte.platform.api.contract.vo;

import com.deloitte.platform.api.bpmservice.vo.BpmApprovalMatrixVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class NextNodeVO {

    @ApiModelProperty(value = "节点key")
    private String taskKey;

    @ApiModelProperty(value = "节点名称")
    private String taskNodeName;

    @ApiModelProperty(value = "路由条件")
    private String conditionText;

    @ApiModelProperty(value = "审批矩阵")
    private List<BpmApprovalMatrixVo> bpmApprovalMatrixVoList;

    @ApiModelProperty(value = "是否是会签节点")
    private boolean multi;

    @ApiModelProperty(value = "是否是最后节点")
    private boolean endNode;

    @ApiModelProperty(value = "是否是第一个节点")
    private boolean firstNode;

    @ApiModelProperty(value = "是否是拒绝")
    private boolean reject = true;

}
