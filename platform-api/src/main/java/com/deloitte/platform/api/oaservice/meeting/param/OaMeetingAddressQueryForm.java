package com.deloitte.platform.api.oaservice.meeting.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :   OaMeetingAddress查询from对象
 * @Modified :
 */
@ApiModel("OaMeetingAddress查询表单")
@Data
public class OaMeetingAddressQueryForm extends BaseQueryForm<OaMeetingAddressQueryParam>  {


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "会议主键")
    private String meetingId;

    @ApiModelProperty(value = "会议室主键")
    private String roomId;

    @ApiModelProperty(value = "会议室编号")
    private String roomNum;

    @ApiModelProperty(value = "是否强制备案")
    private String forceBack;

    @ApiModelProperty(value = "是否后勤统管")
    private String logisticsManager;

    @ApiModelProperty(value = "会议室地址")
    private String address;

    @ApiModelProperty(value = "会议室名称")
    private String roomName;

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
