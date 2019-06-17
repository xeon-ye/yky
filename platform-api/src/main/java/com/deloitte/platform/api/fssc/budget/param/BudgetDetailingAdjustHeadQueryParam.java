package com.deloitte.platform.api.fssc.budget.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : jaws
 * @Date : Create in 2019-05-06
 * @Description :  BudgetDetailingAdjustHead查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDetailingAdjustHeadQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String documentNum;
    private String documentStatus;
    private Long unitId;
    private String unitCode;
    private String unitName;
    private Long deptId;
    private String deptCode;
    private String deptName;
    private String fundType;
    private String relatedDocumentNum;
    private Long relatedDocumentId;
    private String relatedDocumentType;
    private Double lineNum;
    private Double applyTotal;
    private String remark;
    private Long orgId;
    private String orgPath;
    private String applyForPerson;
    private String applyForPersonName;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Double version;

}
