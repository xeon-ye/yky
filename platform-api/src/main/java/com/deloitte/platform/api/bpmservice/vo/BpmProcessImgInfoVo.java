package com.deloitte.platform.api.bpmservice.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * @Author : sazhou
 * @Date : Create in 2019-05-28
 * @Description :
 * @Modified :
 */
public class BpmProcessImgInfoVo extends BaseVo{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "x轴坐标")
    private double x;
    @ApiModelProperty(value = "y轴坐标")
    private double y;
    @ApiModelProperty(value = "环节高度")
    private double height;
    @ApiModelProperty(value = "环节宽度")
    private double width;
    @ApiModelProperty(value = "环节定义ID")
    private String taskKey;
    @ApiModelProperty(value = "环节定义名称")
    private String taskName;
    @ApiModelProperty(value = "审批人信息")
    private List<BpmProcessImgTaskVo> participants;

}
