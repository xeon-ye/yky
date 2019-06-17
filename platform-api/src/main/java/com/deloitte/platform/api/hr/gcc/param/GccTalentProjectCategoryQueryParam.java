package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccTalentProjectCategory查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccTalentProjectCategoryQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private String projectName;
    private String projectCategoryName;
    private String status;
    private Long projectCode;
   /* private Long id;
    private Long code;

    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private String remarks;*/

}
