package com.deloitte.platform.api.isump.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : dzhang
 * @Date : Create in 2019-05-09
 * @Description : 返回简单 USER 字段，供外部接口调用
 * @Modified :
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInterfaceVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "员工号")
    private String empNo;


}
