package com.deloitte.platform.api.fssc.base.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-02
 * @Description :   BaseExpenseSubTypeUnit查询from对象
 * @Modified :
 */
@ApiModel("支出小类-组织单位关系查询表单")
@Data
public class BaseExpenseSubTypeUnitQueryForm extends BaseQueryForm<BaseExpenseSubTypeUnitQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "组织单位ID")
    private Long orgUnitId;

    @ApiModelProperty(value = "支出小类ID")
    private Long expenseSubTypeId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "组织单位名称")
    private String orgUnitName;

    @ApiModelProperty(value = "是否有效")
    private String validFlag;

    @ApiModelProperty(value = "组织单位编码")
    private String orgUnitCode;
}
