package com.deloitte.platform.api.fssc.engine.manual.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**

 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description :  AvManualVoucherHead查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvManualVoucherHeadQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String documentNum;
    private String documentStatus;
    private Long unitId;
    private String unitName;
    private String currencyCode;
    private Double cost;
    private Double totalOriginalAmount;
    private Double totalStandardAmount;
    private String postStatus;
    private Integer attachCount;
    private Long ledgerId;
    private String jeCategory;
    private String jeSource;
    private String periodName;
    private String name;
    private String accrualRevFlag;
    private LocalDateTime defaultEffectiveDate;
    private Long fromRecurringHeaderId;
    private LocalDateTime postedDate;
    private Long accrualRevJeHeaderId;
    private String description;
    private Double currencyConversionRate;
    private String currencyConversionType;
    private LocalDateTime currencyConversionDate;
    private Long createBy;
    private String createUserName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String ex1;
    private String ex2;
    private String ex3;
    private String ex4;
    private String ex5;
    private String ex6;
    private String ex7;
    private String ex8;
    private String ex9;
    private String ex10;
    private String ex11;
    private String ex12;
    private String ex13;
    private String ex14;
    private String ex15;
    private String carrId;
    private List<AvManualVoucherLineQueryParam> lineList;

}
