package com.deloitte.services.fssc.engine.dockingEbs.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(value="AvAccountDocSeqNumFromEbs对象", description="会计凭证处理结果回传")
public class AvAccountDocSeqNumFromEbs {
    @ApiModelProperty(value = "导入标识，E数据有误；Y导入成功\n")
    private String  IMPORT_FLAG;

    @ApiModelProperty(value = "MacRP核心系统处理日期，由MacRP核心系统回写,YYYY-MM-DD HH:MM:SS\n")
    private LocalDateTime IMPORT_DATE;

    @ApiModelProperty(value = "报账系统凭证头号\n")
    private Long  SOURCE_HEAD_ID;

    @ApiModelProperty(value = "MacRP核心系统的凭证编号，用于Oracle回写\n")
    private String DOC_SEQ_NUM;

    @ApiModelProperty(value = "导入请求ID，用于Oracle回写\n")
    private Long REQUEST_ID;

    private List<AvErrorMessageFromEbs> LINES;


}
