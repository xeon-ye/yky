package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-19
 * @Description : HrZpcpCheck返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZpcpCheckAndNumberVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评审信息")
    private HrZpcpCheckVo checkVo;

    @ApiModelProperty(value = "审核人数信息")
    private ZpcpCheckNumberVo checkNumberVo;


}
