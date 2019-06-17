package com.deloitte.platform.api.oaservice.meeting.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description : OaMeetingAddress新增修改form对象
 * @Modified :
 */
@ApiModel("新增OaMeetingAddress表单")
@Data
public class OaMeetingAddressForm extends BaseForm {
    private static final long serialVersionUID = 1L;


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
