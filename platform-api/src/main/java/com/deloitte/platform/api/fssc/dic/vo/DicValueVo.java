package com.deloitte.platform.api.fssc.dic.vo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description : DicValue返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DicValueVo extends BaseVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典值ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
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

}
