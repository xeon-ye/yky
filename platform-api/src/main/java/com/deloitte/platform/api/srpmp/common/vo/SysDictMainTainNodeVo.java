package com.deloitte.platform.api.srpmp.common.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-05-06
 * @Description : SysDict维护返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDictMainTainNodeVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "字典名称")
    private String keyName;

    @ApiModelProperty(value = "是否有效")
    private boolean activeFlag;

}
