package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SignInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String contractId;
    private String contractName;
    private LocalDateTime ourPrintTime;
    private String ourPrintContractNum;
    private String ourSignPerson;
    private LocalDateTime ourSignTime;
    private String ourSignContractNum;
    private String otherContractName;
    private String otherLegalPerson;
    private String otherSignPerson;
    private LocalDateTime otherSignTime;
    private String otherAuthorization;
    private String otherSignContractNum;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String isUsed;
    private String spareField1;
    private String spareField2;
    private String spareField3;
    private LocalDateTime spareField4;
    private Long spareField5;
    private String stampWay;
    private String signStage;
    private String ourSubjectInfo;
    private String subjectCode;
    private String type;
    private String otherAuthorizationUrl;
    private String otherAuthorizationName;

}
