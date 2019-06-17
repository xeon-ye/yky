package com.deloitte.platform.api.fssc.report.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :  ReportMergeRelation查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportMergeRelationQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long reportId;
    private String reportType;
    private Long mergeReportId;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;

}
