package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccTalentProject查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccTalentProjectQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private String projectLevel;
    private String name;
    private String projectNature;
    private String status;
    private String departmentCode;
   /* private Long id;
    private Long code;
    private String declare;
    private String assessed;
    private String remarks;
    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private String allocated;
    private LocalDateTime allocatedTime;
    private String accoutTime;
    private String whetherAccount;
    private String shortName;*/

}
