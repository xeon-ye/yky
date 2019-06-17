package com.deloitte.platform.api.fssc.base.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-07
 * @Description :   BaseContentCommitmentUnit查询from对象
 * @Modified :
 */
@ApiModel("BaseContentCommitmentUnit查询表单")
@Data
public class BaseContentCommitmentUnitQueryForm extends BaseQueryForm<BaseContentCommitmentUnitQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "承诺书内容ID")
    private Long contentCommitmentId;

    @ApiModelProperty(value = "组织单位名称")
    private String orgUnitName;

    @ApiModelProperty(value = "组织单位ID")
    private Long orgUnitId;

    @ApiModelProperty(value = "是否有效")
    private String validFlag;

    @ApiModelProperty(value = "组织单位编码")
    private String orgUnitCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
