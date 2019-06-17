package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccTalentProjectDepart查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccTalentProjectDepartQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
/*    private Long id;
    private Long code;
    private String address;
    private String telephone;
    private String remarks;
    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private String shortName;*/
    private String status;
    private String departName;
}
