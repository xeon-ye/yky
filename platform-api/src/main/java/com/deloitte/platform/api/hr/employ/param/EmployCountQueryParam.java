package com.deloitte.platform.api.hr.employ.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-04-08
 * @Description :  EmployCount查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployCountQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long accountId;
    private String name;
    private String idcard;
    private String appDep;
    private String appPosition;
    private LocalDateTime entryTime;
    private String state;
    private Long flowId;
    private LocalDateTime createTime;
    private String careteBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String year;

}
