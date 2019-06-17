package com.deloitte.platform.api.fssc.report.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :  ReportTotalQuery查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportTotalQueryQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long unitId;
    private String unitCode;
    private String reportType;
    private String reportName;
    private Long reportId;
    private String year;
    private String month;
    private String reportStatus;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String periodType;
    private String mergeFlag;

}
