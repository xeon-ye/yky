package com.deloitte.services.fssc.business.bpm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessValueForm {


    private String request;
    private Long projectId;
    private String totalAmount;
    private String mainTypeCode;
    private Long documentId;
    private String documentType;
    private Long createBy;
    private String deptCode;
    private String projectCode;
    private String level;
    private String  unitCode;
    private String hasContract;

}
