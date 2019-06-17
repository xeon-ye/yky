package com.deloitte.platform.api.oaservice.meeting.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description : OaMeetingMembers新增修改form对象
 * @Modified :
 */
@ApiModel("新增OaMeetingMembers表单")
@Data
public class OaMeetingMembersForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "会议主键")
    private String meetingId;

    @ApiModelProperty(value = "参会人员ID")
    private String userId;

    @ApiModelProperty(value = "参会人员姓名")
    private String userName;

    @ApiModelProperty(value = "参会人员所属部门")
    private String deptId;

    @ApiModelProperty(value = "参会人员所属部门名称")
    private String deptName;

    @ApiModelProperty(value = "联系电话")
    private String telphone;

    @ApiModelProperty(value = "联系邮箱")
    private String email;

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
