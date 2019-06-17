package com.deloitte.platform.api.oaservice.meeting.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :   OaMeetingMembers查询from对象
 * @Modified :
 */
@ApiModel("OaMeetingMembers查询表单")
@Data
public class OaMeetingMembersQueryForm extends BaseQueryForm<OaMeetingMembersQueryParam>  {


    @ApiModelProperty(value = "主键")
    private String id;

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

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后更新人")
    private String updateBy;

    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;

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
