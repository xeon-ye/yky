package com.deloitte.platform.api.isump.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhangdi
 * @Date : Create in 2019-04-15
 * @Description :   FsscUser查询from对象
 * @Modified :
 */
@ApiModel("FsscUser查询表单")
@Data
public class FsscUserQueryForm extends BaseQueryForm<FsscUserQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "工资卡号")
    private String wageCardNumber;

    @ApiModelProperty(value = "公务卡号")
    private String officialCardNumber;

    @ApiModelProperty(value = "工资卡银行名称")
    private String wageCardBankname;

    @ApiModelProperty(value = "公务卡银行名称")
    private String officialCardBankname;

    @ApiModelProperty(value = "工资卡联行号")
    private String wageCardLinknumber;

    @ApiModelProperty(value = "公务卡联行号")
    private String officialCardLinknumber;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "首次来华时间")
    private LocalDateTime firstVisitDate;

    @ApiModelProperty(value = "预计离境时间")
    private LocalDateTime expectedDepartDate;

    @ApiModelProperty(value = "任职受雇从业类型")
    private String employType;

    @ApiModelProperty(value = "任职受雇从业日期")
    private LocalDateTime employDate;

    @ApiModelProperty(value = "有无境内住所(0：无 1：有)")
    private Integer dwellingPlace;

    @ApiModelProperty(value = "是否个人居民(0：否 1：是)")
    private Integer personalResident;

    @ApiModelProperty(value = "涉税事由")
    private String taxCause;

    @ApiModelProperty(value = "收款人类型")
    private String payeeType;
}
