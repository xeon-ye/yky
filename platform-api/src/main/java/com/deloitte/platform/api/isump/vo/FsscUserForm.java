package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhangdi
 * @Date : Create in 2019-04-15
 * @Description : FsscUser新增修改form对象
 * @Modified :
 */
@ApiModel("新增FsscUser表单")
@Data
public class FsscUserForm extends BaseForm {
    private static final long serialVersionUID = 1L;


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

    @ApiModelProperty(value = "涉税事由")
    private String taxCause;

    @ApiModelProperty(value = "收款人类型")
    private String payeeType;

}
