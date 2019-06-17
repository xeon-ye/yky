package com.deloitte.services.fssc.engine.dockingEbs.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 账薄信息同步基础数据
 * </p>
 *
 * @author chenx
 * @since 2019-04-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="AvBaseFromEbs对象", description="COA_凭证类别_币种 三种类型基础数据")

public class AvLedgerFromEbs {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账薄Id")
    private Long ledgerId;

    @ApiModelProperty(value = "账目名称")
    private String name ;

    @ApiModelProperty(value = "账薄说明")
    private String description ;

    @ApiModelProperty(value = "数据编码")
    private Long chartOfAccountsId ;

    @ApiModelProperty(value = "数据编码")
    private String currencyCode ;

    private String shortName;
}
