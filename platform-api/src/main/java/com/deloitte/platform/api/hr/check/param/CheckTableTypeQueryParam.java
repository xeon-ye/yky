package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :  CheckTableType查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckTableTypeQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String checkTableTypeName;
    private String remark;
    private String isValid;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
