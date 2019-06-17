package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-06-12
 * @Description :   Performance查询from对象
 * @Modified :
 */
@ApiModel("Performance查询表单")
@Data
public class PerformanceQueryForm extends BaseQueryForm<PerformanceQueryParam>  {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "项目绩效ID")
    private String performanceId;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "项目日期")
    private LocalDateTime projectDate;

    @ApiModelProperty(value = "年度绩效目标")
    private String annualPerformanceTarget;

    @ApiModelProperty(value = "指标类别（年度/中期）")
    private String indexType;

    @ApiModelProperty(value = "一级指标")
    private String index1st;

    @ApiModelProperty(value = "二级指标")
    private String index2nd;

    @ApiModelProperty(value = "三级指标")
    private String index3st;

    @ApiModelProperty(value = "三级指标CODE")
    private String index3stCode;

    @ApiModelProperty(value = "指标值")
    private String indexPer;

    @ApiModelProperty(value = "项目绩效_总体目标_ID")
    private String performanceTargetId;

    @ApiModelProperty(value = "批复ID")
    private String replayId;

    @ApiModelProperty(value = "创建")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "拓展字段")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    private String orgPath;

    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @ApiModelProperty(value = "项目大类CODE")
    private String idxMainCode;

    @ApiModelProperty(value = "项目大类名称")
    private String idxMainName;

    @ApiModelProperty(value = "项目小类CODE")
    private String idxSubCode;

    @ApiModelProperty(value = "项目小类名称")
    private String idxSubName;

    @ApiModelProperty(value = "指标库CODE")
    private String idxLibCode;

    @ApiModelProperty(value = "指标库名称")
    private String idxLibName;

    @ApiModelProperty(value = "一级指标CODE")
    private String index1stCode;

    @ApiModelProperty(value = "二级指标CODE")
    private String index2ndCode;

    @ApiModelProperty(value = "三级指标值的值")
    private String per;

    @ApiModelProperty(value = "三级指标值的值CODE")
    private String perCode;
}
