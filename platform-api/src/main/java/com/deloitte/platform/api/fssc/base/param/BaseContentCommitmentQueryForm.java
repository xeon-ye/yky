package com.deloitte.platform.api.fssc.base.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-07
 * @Description :   BaseContentCommitment查询from对象
 * @Modified :
 */
@ApiModel("BaseContentCommitment查询表单")
@Data
public class BaseContentCommitmentQueryForm extends BaseQueryForm<BaseContentCommitmentQueryParam> {

    @ApiModelProperty(value = "ID}")
    private Long id;

    @ApiModelProperty(value = "$单据名称")
    private String billName;

    @ApiModelProperty(value = "是否有效")
    private String validFlag;

    @ApiModelProperty(value = "承诺书内容")
    private String contentCommitment;

    @ApiModelProperty(value = "单据类型ID")
    private Long documentTypeId;

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "有效日期")
    private LocalDateTime validDate;

    @ApiModelProperty(value = "失效日期")
    private LocalDateTime invalidDate;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "EXT1")
    private String ext1;

    @ApiModelProperty(value = "EXT2")
    private String ext2;

    @ApiModelProperty(value = "EXT3")
    private String ext3;

    @ApiModelProperty(value = "EXT4")
    private String ext4;

    @ApiModelProperty(value = "EXT5")
    private String ext5;
}
