package com.deloitte.platform.api.oaservice.attachment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-05-13
 * @Description : OaDzggInterfaceTempAttach新增修改form对象
 * @Modified :
 */
@ApiModel("新增OaDzggInterfaceTempAttach表单")
@Data
public class OaDzggInterfaceTempAttachForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "流程实例id")
    private String requestId;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件地址")
    private String fileAddress;

}
