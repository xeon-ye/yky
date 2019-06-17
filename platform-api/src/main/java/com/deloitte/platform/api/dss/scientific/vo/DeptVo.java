package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptVo extends BaseVo
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "单位ID")
    private long deptId;
    @ApiModelProperty(value = "单位名称")
    private String deptName;
}
