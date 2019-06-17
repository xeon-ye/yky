package com.deloitte.platform.api.oaservice.news.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-02
 * @Description :
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessStatusVo extends BaseVo {

    @ApiModelProperty(value = "总人次")
    private int totalNum;

    @ApiModelProperty(value = "已提交")
    private int submitted;

    @ApiModelProperty(value = "未提交")
    private int notSubmitted;

    @ApiModelProperty(value = "已查看")
    private int checked;

    @ApiModelProperty(value = "未查看")
    private int notChecked;

    @ApiModelProperty(value = "接收bpm返回的数据之后的重新封装")
    private List<ProcessParam> respList;

}
