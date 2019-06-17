package com.deloitte.platform.api.oaservice.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-23
 * @Description : OaScheduleShareConfig新增修改form对象
 * @Modified :
 */
@ApiModel("新增OaScheduleShareConfig表单")
@Data
public class OaScheduleShareConfigForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "发起共享人id")
    private String userId;

    @ApiModelProperty(value = "发起共享人姓名")
    private String userName;

    @ApiModelProperty(value = "共享人")
    private String shareToId;

    @ApiModelProperty(value = "共享人姓名")
    private String shareToName;

    @ApiModelProperty(value = "共享人所在部门id")
    private String shareToDeptId;

    @ApiModelProperty(value = "共享人所在部门名称")
    private String shareToDeptName;

    @ApiModelProperty(value = "权限：VIEW:查看/EDIT:编辑")
    private String permissions;

    @ApiModelProperty(value = "是否有效：Y/N")
    private String enableFlag;

    @ApiModelProperty(value = "备用字段")
    private String ext1;

    @ApiModelProperty(value = "备用字段")
    private String ext2;

    @ApiModelProperty(value = "备用字段")
    private String ext3;

    @ApiModelProperty(value = "备用字段")
    private String ext4;

    @ApiModelProperty(value = "备用字段")
    private String ext5;

}
