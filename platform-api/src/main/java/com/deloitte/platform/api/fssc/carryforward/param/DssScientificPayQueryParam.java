package com.deloitte.platform.api.fssc.carryforward.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : chenx
 * @Date : Create in 2019-06-11
 * @Description :  DssScientificPay查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DssScientificPayQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String fromSystem;
    private String documentNum;
    private String payDate;
    private Double funds;
    private String proCode;
    private String taskCode;
    private String budgetYear;
    private String phyletic;
    private String phyleticName;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String status;
    private Long economicClassificationId;
    private String  remark;

}
