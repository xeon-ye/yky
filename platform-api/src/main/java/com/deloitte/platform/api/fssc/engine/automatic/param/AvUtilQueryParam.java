package com.deloitte.platform.api.fssc.engine.automatic.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**

 * @Author : chenx
 * @Date : Create in 2019-03-25
 * @Description :  AvUtil查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvUtilQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private String dataCode;
    private String dataDesc;
    private String dataStatus;
    private Long ledgerId;
}
