package com.deloitte.platform.api.project.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/17 15:11
 * @Description :
 * @Modified:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValueTypeVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "集值编码")
    private String valueCode;

    @ApiModelProperty(value = "集值名称")
    private String valueName;

    @ApiModelProperty(value = "集值描述")
    private String valueDes;

}
