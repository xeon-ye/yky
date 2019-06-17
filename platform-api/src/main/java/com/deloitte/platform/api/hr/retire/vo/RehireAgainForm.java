package com.deloitte.platform.api.hr.retire.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-12
 * @Description : RehireAgain新增修改form对象
 * @Modified :
 */
@ApiModel("新增RehireAgain表单")
@Data
public class RehireAgainForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "再返聘理由")
    private String rehireagainExplain;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "状态 0 保存 1提交")
    private String states;

    @ApiModelProperty(value = "退休在聘明细")
    private List<RehireAgaininfoForm> againinfoFormList;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentForm> attachments;
}
