package com.deloitte.platform.api.fssc.report.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description : ReportMergeRelation新增修改form对象
 * @Modified :
 */
@ApiModel("新增ReportMergeRelation表单")
@Data
public class ReportMergeRelationForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "报表ID")
    private Long reportId;

    @ApiModelProperty(value = "报表类型")
    private String reportType;

    @ApiModelProperty(value = "合并后报表ID")
    private Long mergeReportId;

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
