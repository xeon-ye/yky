package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : LIJUN
 * @Date : Create in 2019-04-12
 * @Description :  MprEvaFundsBudget查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MprEvaFundsBudgetQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String year;
    private String taskName;
    private String budget;
    private String expenses;
    private String exacutiveRate;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
