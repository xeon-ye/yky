package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-18
 * @Description : DictVo返回的VO对象
 * @Modified :
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictTermVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;
}
