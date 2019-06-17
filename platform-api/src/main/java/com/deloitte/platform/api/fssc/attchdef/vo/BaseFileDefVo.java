package com.deloitte.platform.api.fssc.attchdef.vo;

import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-09
 * @Description : BaseFileDef返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseFileDefVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "创建人ID申请人")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名申请人")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "单据名称")
    private String documentName;

    @ApiModelProperty(value = "支出大类ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long mainTypeId;

    @ApiModelProperty(value = "支出大类名称")
    private String mainTypeName;

    @ApiModelProperty(value = "收入大类ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long inComeMainTypeId;

    @ApiModelProperty(value = "收入大类名称")
    private String inComeMainTypeName;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "附件类型集合")
    private String attchNames;

    @ApiModelProperty(value = "行信息")
    private List<BaseFileDefLineVo> baseFileDefLineVos;

}
