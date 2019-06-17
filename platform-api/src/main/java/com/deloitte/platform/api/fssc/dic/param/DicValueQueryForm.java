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
 * @Description :   DicValue查询from对象
 * @Modified :
 */
@ApiModel("DicValue查询表单")
@Data
public class DicValueQueryForm extends BaseQueryForm<DicValueQueryParam>  {


    @ApiModelProperty(value = "字典值ID")
    private Long id;

    @ApiModelProperty(value = "字典主表类型ID")
    private Long dicParentId;

    @ApiModelProperty(value = "字段代码")
    private String dicCode;

    @ApiModelProperty(value = "字段名称")
    private String dicName;

    @ApiModelProperty(value = "字典值")
    private String dicValue;

    @ApiModelProperty(value = "字段描述")
    private String dicDesciption;

    @ApiModelProperty(value = "排序编号")
    private Double dicOrder;

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

    @ApiModelProperty(value = "字典类型代码dicCodeCopy")
    private String dicCodeCopy;
}
