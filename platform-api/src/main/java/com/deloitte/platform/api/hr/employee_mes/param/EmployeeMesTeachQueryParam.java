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
 * @Description :  EmployeeMesTeach查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesTeachQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private LocalDateTime segment1;
    private LocalDateTime segment2;
    private String segment3;
    private String segment5;
    private String segment6;
    private String segment8;
    private String segment12;
    private String segment11;
    private String segment13;
    private String segment14;
    private String segment15;
    private String segment16;
    private String segment17;
    private String overseasStudy;
    private LocalDateTime segment18;
    private LocalDateTime segment19;
    private String segment20;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String careteBy;
    private String updateBy;

}
