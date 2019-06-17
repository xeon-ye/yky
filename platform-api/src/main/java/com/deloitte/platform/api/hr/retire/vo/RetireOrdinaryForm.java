package com.deloitte.platform.api.hr.retire.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-11
 * @Description : RetireOrdinary新增修改form对象
 * @Modified :
 */
@ApiModel("新增RetireOrdinary表单")
@Data
public class RetireOrdinaryForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "员工明细ID组合")
    private String empIds;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentForm> attachments;

    @ApiModelProperty(value = "退休员工明细表")
    private List<RetireOrdinaryinfoForm> retireOrdinaryinfoForms;

}
