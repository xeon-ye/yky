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
 * @Date : Create in 2019-04-12
 * @Description : RehireReturn新增修改form对象
 * @Modified :
 */
@ApiModel("新增RehireReturn表单")
@Data
public class RehireReturnForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "再返聘理由说明")
    private String rehirereturnExplain;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "状态 0保存 1提交")
    private String states;

    @ApiModelProperty(value = "退休在聘明细")
    private List<RehireReturninfoForm> rehireReturninfoForms;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentForm> attachments;

}
