package com.deloitte.platform.api.hr.retire.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-04-11
 * @Description :  RetireWoman查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetireWomanQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long empId;
    private String retire;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;

}
