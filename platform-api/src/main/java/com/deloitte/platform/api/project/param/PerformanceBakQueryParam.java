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
 * @Date : Create in 2019-06-12
 * @Description :  PerformanceBak查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceBakQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String performanceId;
    private String applicationId;
    private LocalDateTime projectDate;
    private String annualPerformanceTarget;
    private String indexType;
    private String index1st;
    private String index2nd;
    private String index3st;
    private String indexPer;
    private String performanceTargetId;
    private String replayId;
    private LocalDateTime createTime;
    private String careteBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Long orgId;
    private String orgPath;
    private String projectId;
    private String index3stCode;
    private String idxMainCode;
    private String idxMainName;
    private String idxSubCode;
    private String idxSubName;
    private String libCode;
    private String libName;
    private String index1stCode;
    private String index2ndCode;
    private String per;
    private String perCode;

}
