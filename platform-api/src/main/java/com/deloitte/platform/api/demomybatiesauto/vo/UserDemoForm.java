package com.deloitte.platform.api.demomybatiesauto.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

/**
 * @Author : jack
 * @Date : Create in 2019-03-20
 * @Description : UserDemo新增修改form对象
 * @Modified :
 */
@ApiModel("新增UserDemo表单")
@Data
public class UserDemoForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "heheheh")
    //jack-todo:

    @ApiModelProperty(value = "姓名")
    private String name;

    @Range(min = 19,max = 40)
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;

}
