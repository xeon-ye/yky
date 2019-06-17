package com.deloitte.platform.api.fssc.performance.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : jaws
 * @Date : Create in 2019-04-08
 * @Description :  PerformanceIndex查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceIndexQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String code;
    private String name;
    private String validFlag;
    private Long indexLibraryId;
    private String valueJudgeCondition;
    private String valueUnitId;
    private String valueLevel;
    private Long level1Id;
    private Long level2Id;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Long orgId;
    private String orgPath;

}
