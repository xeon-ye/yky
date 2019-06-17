package com.deloitte.platform.api.oaservice.meeting.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description : OaMeetingHeader新增修改form对象
 * @Modified :
 */
@ApiModel("新增会议室预定表单")
@Data
public class OaMeetingForm  extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会议主要信息")
    private OaMeetingHeaderForm oaMeetingHeaderForm;

    @ApiModelProperty(value = "会议参会人员")
    private List<OaMeetingMembersForm> oaMeetingMembersFormList;

    @ApiModelProperty(value = "会议室地址")
    private List<OaMeetingAddressForm>  oaMeetingAddressFormList;
}
