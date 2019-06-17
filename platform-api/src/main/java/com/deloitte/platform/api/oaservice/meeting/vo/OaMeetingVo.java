package com.deloitte.platform.api.oaservice.meeting.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description : 会议室预定查询返回的vo
 * @Modified :
 */
@ApiModel("会议室预定表单")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OaMeetingVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会议主要信息")
    private OaMeetingHeaderVo oaMeetingHeaderVo;

    @ApiModelProperty(value = "会议参会人员")
    private List<OaMeetingMembersVo> oaMeetingMembersVoList;

    @ApiModelProperty(value = "会议室地址")
    private List<OaMeetingAddressVo>  oaMeetingAddressVoList;
}
