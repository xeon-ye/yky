package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : yangyq
 * @Date : Create in 2019-04-20
 * @Description :  MonitorInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitorInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String monitorName;
    private LocalDateTime planFinishTime;
    private Long planFinishNum;
    private String isUsed;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private Long contractId;
    private String contractName;
    private String spareField1;
    private String spareField2;
    private String spareField3;
    private LocalDateTime spareField4;
    private Long spareField5;
    private LocalDateTime actFinishTime;
    private Long actFinishNum;

}
