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
 * @Date : Create in 2019-05-08
 * @Description :  EmployeeMesPilit查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesPilitQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String segment1;
    private LocalDateTime segment2;
    private LocalDateTime segment3;
    private String segment4;
    private String segment5;
    private String segment6;
    private String segment7;
    private String segment8;
    private String segment9;
    private LocalDateTime segment10;
    private String segment11;
    private LocalDateTime segment12;
    private String segment13;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String careteBy;
    private String updateBy;

}
