package com.deloitte.platform.api.fssc.performance.param;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-08
 * @Description :   PerformanceIndex查询from对象
 * @Modified :
 */
@ApiModel("PerformanceIndex查询表单")
@Data
public class PerformanceIndexQueryForm extends BaseQueryForm<PerformanceIndexQueryParam>  {


    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "启用标志")
    private String validFlag;

    @ApiModelProperty(value = "关联指标库ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long indexLibraryId;

    @ApiModelProperty(value = "关联指标库编码")
    private String indexLibraryCode;

    @ApiModelProperty(value = "关联指标库名称")
    private String indexLibraryName;

    @ApiModelProperty(value = "指标判断条件")
    private String valueJudgeCondition;

    @ApiModelProperty(value = "指标单位ID")
    private String valueUnitId;

    @ApiModelProperty(value = "指标单位编码")
    private String valueUnitCode;

    @ApiModelProperty(value = "指标单位名称")
    private String valueUnitName;

    @ApiModelProperty(value = "指标级别")
    private String valueLevel;

    @ApiModelProperty(value = "对应一级指标")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long level1Id;

    @ApiModelProperty(value = "对应二级指标")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long level2Id;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

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

    @ApiModelProperty(value = "组织ID")
    private String orgId;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "排序字段")
    private String sort;

    @ApiModelProperty(value = "排序方向")
    private String sortOrder;
}
