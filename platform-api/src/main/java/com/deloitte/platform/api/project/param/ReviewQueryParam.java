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
 * @Description :  Review查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String reviewId;
    private String applicationId;
    private String budgetId;
    private String expenseId;
    private String performanceId;
    private String schoolPriority;
    private String firstLevelProject;
    private String reviewAdvice;
    private String reviewStatusName;
    private String reviewStatusCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Long orgId;
    private String orgPath;
    private String remark;
    private String approver;
    private LocalDateTime approverTime;
    private String opinion;
    private String firstLevelProjectCode;
    private String serviceNum;

}
