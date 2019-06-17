package com.deloitte.platform.api.oaservice.fastway.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-17
 * @Description :  FastWay查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastWayQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String picUrl;
    private String applyName;
    private String applySort;
    private LocalDateTime applyDatetime;
    private LocalDateTime applyUpdatetime;
    private String applyUrl;

}
