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
 * @Date : Create in 2019-05-31
 * @Description :   Improvements查询from对象
 * @Modified :
 */
@ApiModel("Improvements查询表单")
@Data
public class ImprovementsQueryForm extends BaseQueryForm<ImprovementsQueryParam>  {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "设施改造ID")
    private String improvementsId;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "明细名称")
    private String projectAbs;

    @ApiModelProperty(value = "投入时间")
    private LocalDateTime inputDate;

    @ApiModelProperty(value = "建筑面积")
    private String coveredArea;

    @ApiModelProperty(value = "修缮面积")
    private String repairArea;

    @ApiModelProperty(value = "修缮工作内容")
    private String repairWork;

    @ApiModelProperty(value = "实施周期")
    private String implCycle;

    @ApiModelProperty(value = "中央财政经费")
    private String fundingCenter;

    @ApiModelProperty(value = "主管部门经费")
    private String fundingDirector;

    @ApiModelProperty(value = "其他经费")
    private String fundingOther;

    @ApiModelProperty(value = "经费合计")
    private String fundingTotal;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
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
}
