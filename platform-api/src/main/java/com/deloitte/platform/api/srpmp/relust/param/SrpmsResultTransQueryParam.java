package com.deloitte.platform.api.srpmp.relust.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description :  SrpmsResultTrans查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsResultTransQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long resultId;
    private String resultName;
    private String resultTransName;
    private String transWay;
    private String contractNum;
    private Long contractAmount;
    private String contractSigningDay;
    private String transFeeSource;
    private String remarks;
    private String status;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
