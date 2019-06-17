package com.deloitte.platform.api.srpmp.outline.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-07
 * @Description : Dept返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsDeptVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位code")
    private String id;

    @ApiModelProperty(value = "单位名称")
    private String label;

}
