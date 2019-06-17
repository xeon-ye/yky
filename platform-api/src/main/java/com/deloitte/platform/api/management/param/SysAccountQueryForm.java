package com.deloitte.platform.api.management.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jack
 * @Date : Create in 2019-04-18
 * @Description :   SysAccount查询from对象
 * @Modified :
 */
@ApiModel("SysAccount查询表单")
@Data
public class SysAccountQueryForm extends BaseQueryForm<SysAccountQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "随机盐")
    private String salt;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "0：正常,2：锁定,9：逻辑删除")
    private String state;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "微信openid")
    private String wxOpenid;
}
