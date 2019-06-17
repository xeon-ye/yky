package com.deloitte.platform.api.fssc.engine.automatic.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : chenx
 * @Date : Create in 2019-03-30
 * @Description :  AvLedgerUnitRelation查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvLedgerUnitRelationQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String balanceCode;
    private Long ledgerId;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Long createBy;
    private LocalDateTime createDate;

}
