package com.deloitte.platform.api.srpmp.outline.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : pengchao
 * @Date : Create in 2019-02-20
 * @Description :  SrpmsOutlineBase查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsOutlineBaseQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long orgId;
    private String year;
    private String month;
    private String type;
    private Long total;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
