package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 科研成果 --单位成果转化鉴定
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class DeptAchievementVo extends BaseVo{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "依托单位ID")
    private Long deptId;

    @ApiModelProperty(value = "依托单位")
    private String deptName;

    @ApiModelProperty(value = "成果转化")
    private Integer transformation;

    @ApiModelProperty(value = "成果鉴定")
    private Integer authenticate;

}
