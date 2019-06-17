package com.deloitte.platform.api.fssc.bpm.vo;

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

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-18
 * @Description : Process返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    private Long version;

    private String processUrl;

    @ApiModelProperty(value = "流程实例ID")
    private String processId;

    @ApiModelProperty(value = "本地流程定义ID")
    private Long processDefId;

    @ApiModelProperty(value = "流程状态")
    private String processStatus;

    @ApiModelProperty(value = "流程状态中文")
    private String processStatusName;

    @ApiModelProperty(value = "是否结束流程")
    private String isFinshed;

    @ApiModelProperty(value = "单据ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long documentId;

    @ApiModelProperty(value = "单据类型取表名")
    private String documentType;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据名称")
    private String documentName;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("审批页面")
    private String objectUrl;

}
