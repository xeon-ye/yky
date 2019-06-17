package com.deloitte.platform.api.oaservice.news.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProcessParam  implements Serializable {

    @ApiModelProperty(value = "节点标识")
    private String taskKey;

    @ApiModelProperty(value = "操作者总计")
    private int totalNum;

    @ApiModelProperty(value = "已提交")
    private int submitted;

    @ApiModelProperty(value = "已查看")
    private int checked;

    @ApiModelProperty(value = "未查看")
    private int notChecked;

    @ApiModelProperty(value = "返回的节点数据")
    private List<RespProcessParam> processParamList;

}
