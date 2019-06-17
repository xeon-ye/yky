package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : zhengchun
 * @Date : Create in 2019-04-02
 * @Description :  ApprovalOpinion查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalOpinionQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String processTaskId;
    private String contractId;
    private String opinionType;
    private String opinion;
    private String approvalerCode;
    private String approvaler;
    private String replyText;
    private String replyPersonCode;
    private String replyPerson;
    private String opinionFileId;
    private String opinionFileUrl;
    private String opinionFileName;
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

}
