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
 * @Description :  ProcessExecuterTransfer查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessExecuterTransferQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long contractId;
    private String executerCode;
    private String executer;
    private String newExecuterCode;
    private String newExecuter;
    private String processId;
    private String processInstanceId;
    private String statue;
    private LocalDateTime sendTime;
    private LocalDateTime createTime;
    private String spareField1;
    private String spareField2;
    private String spareField3;
    private LocalDateTime spareField4;
    private Long spareField5;
    private String orgId;
    private String org;
    private String oldOrgId;
    private String oldOrg;
    private String excuterInfomation;
    private String oldExcuterInfomation;

}
