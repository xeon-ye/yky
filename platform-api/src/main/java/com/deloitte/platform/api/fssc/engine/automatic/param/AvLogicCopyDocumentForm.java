package com.deloitte.platform.api.fssc.engine.automatic.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
public class AvLogicCopyDocumentForm {
    private String fromTypeCode;

    private String toTypeCode;

    private Long ledgerId;

    private  String typeCode;

}
