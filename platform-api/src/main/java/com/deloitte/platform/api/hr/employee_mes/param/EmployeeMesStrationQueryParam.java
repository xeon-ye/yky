package com.deloitte.platform.api.hr.employee_mes.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-05-14
 * @Description :  EmployeeMesStration查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesStrationQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Double id;
    private String company;
    private String dep;
    private String segment1;
    private String segment2;
    private String segment3;
    private LocalDateTime segment4;
    private String segment5;
    private String segment6;
    private LocalDateTime segment7;
    private String segment8;
    private String segment9;
    private String empMesId;
    private String applyState;
    private String createTime;
    private String updateTime;
    private String careteBy;
    private String updateBy;
    private String column1;

}
