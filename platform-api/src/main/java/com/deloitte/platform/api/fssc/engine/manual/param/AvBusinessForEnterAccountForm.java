package com.deloitte.platform.api.fssc.engine.manual.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel("用于提交业务单据的时候实体")
@Data
public class AvBusinessForEnterAccountForm {
    private String documentType;
    private Long documentId;
    private LocalDateTime defaultEffectiveDate;
}
