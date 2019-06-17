package com.deloitte.platform.api.hr.employ.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-09
 * @Description : ApplyinginfoFlow新增修改form对象
 * @Modified :
 */
@ApiModel("新增ApplyinginfoFlow表单")
@Data
public class ApplyinginfoFlowForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "${field.comment}")
    private String afid;

    @ApiModelProperty(value = "备注")
    private String remake;

    @ApiModelProperty(value = "当前节点")
    private String currentnode;

    @ApiModelProperty(value = "当前节点状态")
    private String currentnodeState;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    private String id;

}
