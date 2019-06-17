package com.deloitte.platform.api.oaservice.applycenter.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description :  ApplyCenter查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyCenterQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long applyId;
    private String picUrl;
    private String applyName;
    private String applySort;
    private String isVisiable;
    private LocalDateTime applyDatetime;
    private LocalDateTime applyUpdatetime;
    private String typeName;
    private String delFlag;
    private String applyUrl;
    private String applyCode;
    private String systemCode;

}
