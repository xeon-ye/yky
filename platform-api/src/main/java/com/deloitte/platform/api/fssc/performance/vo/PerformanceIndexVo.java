package com.deloitte.platform.api.fssc.performance.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-08
 * @Description : PerformanceIndex返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceIndexVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "启用标志")
    private String validFlag;

    @ApiModelProperty(value = "关联指标库")
    private String indexLibraryId;

    @ApiModelProperty(value = "关联指标库名称")
    private String indexLibraryName;

    @ApiModelProperty(value = "指标判断条件")
    private String valueJudgeCondition;

    @ApiModelProperty(value = "指标判断条件名称")
    private String valueJudgeConditionName;

    @ApiModelProperty(value = "指标单位ID")
    private String valueUnitId;

    @ApiModelProperty(value = "指标单位名称")
    private String valueUnitName;

    @ApiModelProperty(value = "指标级别")
    private String valueLevel;

    @ApiModelProperty(value = "对应一级指标ID")
    private String level1Id;

    @ApiModelProperty(value = "对应一级指标名称")
    private String level1Name;

    @ApiModelProperty(value = "对应二级指标ID")
    private String level2Id;

    @ApiModelProperty(value = "对应二级指标名称")
    private String level2Name;

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

    @ApiModelProperty(value = "子指标")
    List<PerformanceIndexVo> childIndexVoList;

}
