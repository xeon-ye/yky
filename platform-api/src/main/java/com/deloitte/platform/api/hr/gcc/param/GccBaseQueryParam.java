package com.deloitte.platform.api.hr.gcc.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccOnjobInformation查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccBaseQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String userName;
    private String choiceYear;
    private String projectName;
    private String unit;
    private String projectCategory;

}
