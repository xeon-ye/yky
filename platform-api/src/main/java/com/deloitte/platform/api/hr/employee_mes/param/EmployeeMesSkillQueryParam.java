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
 * @Description :  EmployeeMesSkill查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesSkillQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String campany;
    private String dept;
    private String segment1;
    private String segment2;
    private LocalDateTime segment3;
    private String segment5;
    private String segment4;
    private String applyState;
    private String empMesId;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;

}
