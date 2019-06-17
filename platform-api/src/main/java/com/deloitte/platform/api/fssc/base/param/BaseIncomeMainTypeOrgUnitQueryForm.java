package com.deloitte.platform.api.fssc.base.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description :   收入大类-组织单位关系查询表单
 * @Modified :
 */
@ApiModel("收入大类-组织单位关系查询表单")
@Data
public class BaseIncomeMainTypeOrgUnitQueryForm extends BaseQueryForm<BaseIncomeMainTypeQueryOrgUnitParam>  {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "收入大类ID")
    private Long incomeMainTypeId;

    @ApiModelProperty(value = "组织单位ID")
    private Long orgUnitId;

    @ApiModelProperty(value = "组织单位编码")
    private String orgUnitCode;

    @ApiModelProperty(value = "组织单位名称")
    private String orgUnitName;

    @ApiModelProperty(value = "是否有效")
    private String validFlag;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateBy;

    @ApiModelProperty(value = "更新人")
    private String updateTime;

}
