package com.deloitte.platform.api.fssc.engine.manual.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description :  AvManualVoucherLine查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvManualVoucherLineQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long jeLineNum;
    private String documentNum;
    private String accountStructure;
    private String accountStructureCode;
    private String accountStructureDesc;
    private String voucherType;
    private Long createBy;
    private String createUserName;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    private Long jeHeaderId;
    private Long ledgerId;
    private String periodName;
    private LocalDateTime effectiveDate;
    private Double enteredDr;
    private Double enteredCr;
    private Double accountedDr;
    private Double accountedCr;
    private String description;
    private String lineTypeCode;
    private String status;
    private String postStatus;
    private String segment1;
    private String segment2;
    private String segment3;
    private String segment4;
    private String segment5;
    private String segment6;
    private String segment7;
    private String segment8;
    private String segment9;
    private String segment10;
    private String segment11;
    private String segment12;
    private String segment13;
    private String segment14;
    private String segment15;
    private String segment16;
    private String segment17;
    private String segment18;
    private String segment19;
    private String segment20;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String ext6;
    private String ext7;
    private String ext8;
    private String ext9;
    private String ext10;
    private String ext11;
    private String ext12;
    private String ext13;
    private String ext14;
    private String ext15;
    private String errorMessage;

}
