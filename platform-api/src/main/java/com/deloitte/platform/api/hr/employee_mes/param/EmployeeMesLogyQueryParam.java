package com.deloitte.platform.api.hr.employee_mes.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : woo
 * @Date : Create in 2019-05-18
 * @Description :  EmployeeMesLogy查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesLogyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String segment1;
    private String segment2;
    private String segment3;
    private LocalDateTime segment5;
    private String segment6;
    private String segment4;
    private LocalDateTime segment7;
    private String segment8;
    private LocalDateTime updateTime;
    private String applyState;
    private String empMesId;
    private String updateBy;
    private String careteBy;
    private LocalDateTime createTime;
    private String company;
    private String dept;
    private String column1;

}
