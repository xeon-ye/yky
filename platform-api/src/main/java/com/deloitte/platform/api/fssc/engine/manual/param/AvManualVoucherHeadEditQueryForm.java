package com.deloitte.platform.api.fssc.engine.manual.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description :   AvManualVoucherHead查询from对象
 * @Modified :
 */
@ApiModel("AvManualVoucherHead查询表单")
@Data
public class AvManualVoucherHeadEditQueryForm {
    private Long id;
    private String pageType;
    @ApiModelProperty(value ="会计日期")
    private LocalDateTime reverseDate;
    @ApiModelProperty(value ="收入结转ID")
    private List<Long> carrayIdList;

    @ApiModelProperty(value ="是否收入结转")
    private String carrayStatus;
}
