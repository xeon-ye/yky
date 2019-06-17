package com.deloitte.platform.api.hr.employ.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ：Mr.Zhong
 * @Date ：Created in 2019/5/9 13:38
 */
@ApiModel("各部门招聘申请需求")
@Data
public class RecruitEmpForm {

    private String id;

    @ApiModelProperty(value = "招聘需求详情")
    List<RecruitDemandForm> recruitDemandForms;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentForm> attachments;

    private String state;

    private String empId;

    private String processNum;
}
