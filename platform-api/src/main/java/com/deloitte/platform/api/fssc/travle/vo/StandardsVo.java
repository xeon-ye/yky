package com.deloitte.platform.api.fssc.travle.vo;

import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-17
 * @Description : TasTravelStandards返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardsVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "各级补助费用")
    private BigDecimal overMoney;

}
