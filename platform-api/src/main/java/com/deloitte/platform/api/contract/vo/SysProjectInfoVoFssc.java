package com.deloitte.platform.api.contract.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : SysProjectInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysProjectInfoVoFssc extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private String id;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目金额")
    private Long projectAmount;

    @ApiModelProperty(value = "所属组织ID")
    private String orgCode;

    @ApiModelProperty(value = "所属组织")
    private String org;

    @ApiModelProperty(value = "项目负责人ID")
    private String projectManagerCode;

    @ApiModelProperty(value = "项目负责人")
    private String projectManager;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUsed;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "备用字段")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    private String spareField3;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "备用字段")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    private Long spareField5;

    @ApiModelProperty(value = "合同与项目关联表id")
    private Long basicProjectMapId;
}
