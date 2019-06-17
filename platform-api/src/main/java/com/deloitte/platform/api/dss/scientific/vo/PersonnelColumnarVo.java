package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 科研人才柱状数据展示vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelColumnarVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位")
    private String deptName;

    @ApiModelProperty(value = "该单位所有人数")
    private Long totalNum;

    @ApiModelProperty(value = "高级人数")
    private Long highLever;

    @ApiModelProperty(value = "中级人数")
    private Long midLever;


    @ApiModelProperty(value = "初级人数")
    private Long lowLever;


    @ApiModelProperty(value = "博士后人数")
    private Long postdoctor;
}
