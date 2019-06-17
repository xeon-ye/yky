package com.deloitte.platform.api.fssc.base.vo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description : 收入大类新增修改form对象
 * @Modified :
 */
@ApiModel("新增收入大类表单")
@Data
public class BaseIncomeMainTypeForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID",notes = "修改时,必须传,否则做新增处理")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "编码",required = true)
    private String code;

    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @ApiModelProperty(value = "是否父值",required = true,notes = "N:否,Y:是")
    private String parentFlag;

    @ApiModelProperty(value = "父值编码",required = true)
    private String parentCode;

    @ApiModelProperty(value = "是否有效",required = true,notes = "N:否,Y:是")
    private String validFlag;

    @ApiModelProperty(value = "生效日期")
    private LocalDateTime validDate;

    @ApiModelProperty(value = "失效日期")
    private LocalDateTime invalidDate;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

}
