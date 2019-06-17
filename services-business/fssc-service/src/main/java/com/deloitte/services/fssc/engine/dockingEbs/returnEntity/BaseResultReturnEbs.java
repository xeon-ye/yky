package com.deloitte.services.fssc.engine.dockingEbs.returnEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * COA_凭证类别_币种 三种类型基础数据
 * </p>
 *
 * @author chenx
 * @since 2019-04-8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="baseResult对象", description="COA_凭证类别_币种 三种类型基础数据 处理结果返回")
public class BaseResultReturnEbs {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据类型(ACCOUNT/CURRENCY/VOUCHER)")
    private String dataType;

    @ApiModelProperty(value = "数据编码")
    private String dataCode;

    @ApiModelProperty(value = "导入标识，E导入失败；Y导入成功")
    private String  importFlag;

    @ApiModelProperty(value = "报账系统回写的详细错误信息")
    private String  errorMessage;

}
