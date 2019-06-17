package com.deloitte.platform.api.oaservice.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-03
 * @Description :   OaAssistantMapping查询from对象
 * @Modified :
 */
@ApiModel("OaAssistantMapping查询表单")
@Data
public class OaAssistantMappingQueryForm extends BaseQueryForm<OaAssistantMappingQueryParam>  {


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "秘书ID")
    private String userId;

    @ApiModelProperty(value = "秘书名称")
    private String userName;

    @ApiModelProperty(value = "领导ID")
    private String leaderId;

    @ApiModelProperty(value = "领导名称")
    private String leaderName;

    @ApiModelProperty(value = "领导所属部门ID")
    private String leaderDeptId;

    @ApiModelProperty(value = "领导所属部门名称")
    private String leaderDeptName;

    @ApiModelProperty(value = "是否有效")
    private String enableFlag;

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
