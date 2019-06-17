package com.deloitte.platform.api.fssc.engine.automatic.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-25
 * @Description :   AvUtil查询from对象
 * @Modified :
 */
@ApiModel("AvUtil")
@Data
public class AvUtilQueryForm extends BaseQueryForm<AvUtilQueryParam> {
    private String dataCode;
    private String dataDesc;
    private String dataStatus;
    private Long ledgerId;
    private Integer start;
    private Integer end;

}
