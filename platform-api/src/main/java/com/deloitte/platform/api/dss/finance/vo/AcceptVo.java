package com.deloitte.platform.api.dss.finance.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : chitose
 * @Date : Create in 2019-05-06
 * @Description : 统一接受数据
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcceptVo extends BaseVo {

    @ApiModelProperty(value = "期间")
    private String periodCode;

    @ApiModelProperty(value = "机构编码")
    private List<String> comCode;

    @ApiModelProperty(value = "项目类型Code")
    private String projectType;

    @ApiModelProperty(value = "指标类型")
    private String indexCode;

    @ApiModelProperty(value = "预算类型 1 收入 ， 2支出")
    private String budgetType;

    @ApiModelProperty(value = "指标类型数组")
    private List<String> indexCodes;
}
