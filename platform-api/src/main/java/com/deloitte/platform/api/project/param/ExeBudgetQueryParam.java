package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : zhengchun
 * @Date : Create in 2019-05-25
 * @Description :  ExeBudget查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExeBudgetQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectId;
    private String applicationId;
    private String executionId;
    private String budgetCode;
    private String budgetName;
    private String budgetAmount;
    private String exeAmount;
    private String budgetYear;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;

}
