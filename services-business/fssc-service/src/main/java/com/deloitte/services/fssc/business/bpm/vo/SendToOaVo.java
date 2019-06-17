package com.deloitte.services.fssc.business.bpm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
public class SendToOaVo {

    private String processInstanceId;

    private String processDefKey;

    @ApiModelProperty(value = "推送人 copyEmpNo , remark")
    @NotNull
    private List<Map<String,String>> copyEmpNos;

    @ApiModelProperty(value = "单据ID")
    @NotNull
    private Long documentId;

    private String documentNum;

    private String documentType;

}
