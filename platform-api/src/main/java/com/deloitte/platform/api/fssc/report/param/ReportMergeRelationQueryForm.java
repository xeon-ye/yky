package com.deloitte.platform.api.fssc.report.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :   ReportMergeRelation查询from对象
 * @Modified :
 */
@ApiModel("ReportMergeRelation查询表单")
@Data
public class ReportMergeRelationQueryForm extends BaseQueryForm<ReportMergeRelationQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "报表ID")
    private Long reportId;

    @ApiModelProperty(value = "报表类型")
    private String reportType;

    @ApiModelProperty(value = "合并后报表ID")
    private Long mergeReportId;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    private String ext5;
}
