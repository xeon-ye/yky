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
 * @Date : Create in 2019-06-06
 * @Description :  ExePerformance查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExePerformanceQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectId;
    private String replyId;
    private String executionId;
    private String indicators1;
    private String indicators2;
    private String indicators3;
    private String indicatorsYear;
    private String exeCondition1and7;
    private String exeConditionYear;
    private String fundingSec;
    private String systemSec;
    private String personSec;
    private String hardwareSec;
    private String otherSec;
    private String reasonInstruction;
    private Long targetPlanCode;
    private String targetPlanName;
    private String note;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private String et4;
    private Long orgId;

}
