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
 * @Date : Create in 2019-05-06
 * @Description :  IncomeOfCarryForward查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeOfCarryForwardQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long mainTypeId;
    private String documentNum;
    private String documentType;
    private String projectCode;
    private String projectName;
    private Long unitId;
    private Long deptId;
    private String unitName;
    private String deptName;
    private LocalDateTime enterDate;
    private Double money;
    private String status;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long documentId;
    private String etx1;
    private String etx2;
    private String etx3;
    private String etx4;
    private String etx5;
    private Long jeHeaderId;
    private String mainTypeName;

}
