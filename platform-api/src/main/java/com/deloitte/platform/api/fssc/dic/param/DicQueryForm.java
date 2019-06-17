package com.deloitte.platform.api.fssc.dic.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description :   Dic查询from对象
 * @Modified :
 */
@ApiModel("Dic查询表单")
@Data
public class DicQueryForm extends BaseQueryForm<DicQueryParam>  {


    @ApiModelProperty(value = "字典类型ID")
    private Long id;

    @ApiModelProperty(value = "字段代码")
    private String eumCode;

    @ApiModelProperty(value = "字段名称")
    private String eumName;

    @ApiModelProperty(value = "字段描述")
    private String eumDesciption;

    @ApiModelProperty(value = "排序编号")
    private Double eumOrder;

    @ApiModelProperty(value = "关联表名(应用到哪个表)")
    private String eumConcatTab;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "预留字段1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    private String ext5;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "更新人")
    private Long updateBy;
}
