package com.deloitte.platform.api.fssc.base.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-22
 * @Description :   BasePersonIncomeType查询from对象
 * @Modified :
 */
@ApiModel("BasePersonIncomeTypeQueryForm查询表单")
@Data
public class BasePersonIncomeTypeQueryForm extends BaseQueryForm<BasePersonIncomeTypeQueryParam>  {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "单据类型ID")
    @NotNull(message = "单据类型ID不能为空")
    private Long documentTypeId;

    @ApiModelProperty(value = "类型编码")
    private String typeCode;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "类型说明")
    private String typeExplain;

    @ApiModelProperty(value = "是否有效")
    private String validFlag;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;
}
