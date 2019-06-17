package com.deloitte.platform.api.srpmp.project.task.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-17
 * @Description : SrpmsProjectTaskTranLong返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranLongProjectTypeVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "横纵向项目code")
    private String id;

    @ApiModelProperty(value = "横纵向项目名称")
    private String label;

}
