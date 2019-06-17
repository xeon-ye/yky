package com.deloitte.platform.api.fssc.pe.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description :  PerSelfTarget查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerSelfTargetQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private String firstPer;
    private String secondPer;
    private String thiredPer;
    private Long thiredUnitId;
    private String thiredUnitCode;
    private String thiredUnitName;
    private String yearPerValue;
    private String realCompleteValue;
    private BigDecimal scoreValue;
    private BigDecimal scored;
    private String reason;
    private Long documentId;
    private String documentType;
    private Long version;
    private String ex1;
    private String ex2;
    private String ex3;
    private String ex4;
    private String ex5;

}
