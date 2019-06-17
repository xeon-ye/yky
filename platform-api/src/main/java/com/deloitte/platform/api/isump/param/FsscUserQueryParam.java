package com.deloitte.platform.api.isump.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : zhangdi
 * @Date : Create in 2019-04-15
 * @Description :  FsscUser查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FsscUserQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String wageCardNumber;
    private String officialCardNumber;
    private String wageCardBankname;
    private String officialCardBankname;
    private String wageCardLinknumber;
    private String officialCardLinknumber;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime firstVisitDate;
    private LocalDateTime expectedDepartDate;
    private String employType;
    private LocalDateTime employDate;
    private Integer dwellingPlace;
    private Integer personalResident;
    private String taxCause;
    private String payeeType;

}
