package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : FlowStationTreeVo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowStationTermVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流动站编码")
    private String stationCode;

    @ApiModelProperty(value = "流动站名称")
    private String stationName;




}
