package com.deloitte.services.fssc.engine.dockingEbs.entity;

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
 * @since 2019-03-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="AvBaseFromEbs对象", description="基础数据")
public class AvCOAFromEbs {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据类型(ACCOUNT/CURRENCY/VOUCHER)")
    private Long chartOfAccountsId;

    @ApiModelProperty(value = "数据类型(ACCOUNT/CURRENCY/VOUCHER)")
    private Long segmentNum;

    @ApiModelProperty(value = "数据类型(ACCOUNT/CURRENCY/VOUCHER)")
    private String segmentCode;

    @ApiModelProperty(value = "数据类型(ACCOUNT/CURRENCY/VOUCHER)")
    private String segmentDesc;

    @ApiModelProperty(value = "值级")
    private String segmentType;
}
