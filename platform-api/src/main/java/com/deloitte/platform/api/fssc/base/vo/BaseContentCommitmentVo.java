package com.deloitte.platform.api.fssc.base.vo;

import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-07
 * @Description : BaseContentCommitment返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseContentCommitmentVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "单据名称")
    private String billName;

    @ApiModelProperty(value = "是否有效")
    private String validFlag;

    @ApiModelProperty(value = "承诺书内容")
    private String contentCommitment;

    @ApiModelProperty(value = "单据类型ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long documentTypeId;

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "有效日期")
    private LocalDateTime validDate;

    @ApiModelProperty(value = "无效日期")
    private LocalDateTime invalidDate;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "更新是你")
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
