package com.deloitte.platform.api.fssc.performance.vo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-08
 * @Description : PerformanceIndex新增修改form对象
 * @Modified :
 */
@ApiModel("新增PerformanceIndex表单")
@Data
public class PerformanceIndexForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "启用标志")
    private String validFlag;

    @ApiModelProperty(value = "关联指标库")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long indexLibraryId;

    @ApiModelProperty(value = "指标判断条件")
    private String valueJudgeCondition;

    @ApiModelProperty(value = "指标单位ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long valueUnitId;

    @ApiModelProperty(value = "指标级别")
    private String valueLevel;

    @ApiModelProperty(value = "对应一级指标ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long level1Id;

    @ApiModelProperty(value = "对应一级指标编码")
    private String level1Code;

    @ApiModelProperty(value = "对应二级指标ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long level2Id;

    @ApiModelProperty(value = "对应二级指标编码")
    private String level2Code;

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

}
