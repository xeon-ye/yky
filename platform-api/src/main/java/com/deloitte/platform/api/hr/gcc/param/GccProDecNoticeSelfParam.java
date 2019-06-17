package com.deloitte.platform.api.hr.gcc.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccProDecNotice查询from对象
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccProDecNoticeSelfParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    private String noticeName;

    private String projectName;

    private String userId;

    private String status;
}
