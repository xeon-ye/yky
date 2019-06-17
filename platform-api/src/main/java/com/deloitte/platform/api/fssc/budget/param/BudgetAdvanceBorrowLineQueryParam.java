package com.deloitte.platform.api.fssc.budget.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 * @Author : jaws
 * @Date : Create in 2019-04-12
 * @Description :  BudgetAdvanceBorrowLine查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetAdvanceBorrowLineQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long documentId;
    private String documentType;
    private BigDecimal borrowAmount;
    private Long subTypeId;
    private BigDecimal budgetRemainAmount;
    private BigDecimal usableRemainAmount;
    private BigDecimal usedRemainAmount;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Long orgId;
    private String orgPath;

}
