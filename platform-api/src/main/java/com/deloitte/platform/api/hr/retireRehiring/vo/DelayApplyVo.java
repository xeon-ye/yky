package com.deloitte.platform.api.hr.retireRehiring.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description : RetireDelayApply返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelayApplyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "职工编号")
    private String empCode;

    @ApiModelProperty(value = "通知记录表Id")
    private String recordId;

    @ApiModelProperty(value = "0.所院,1机关")
    private String institute;

    @ApiModelProperty(value = "近几年工作情况")
    private String recentWorking;

    @ApiModelProperty(value = "拟定担任工作")
    private String intendedWork;

    @ApiModelProperty(value = "个人意见(机关)")
    private String personalOpinion;

    @ApiModelProperty(value = "延缓退休时间")
    private LocalDate delayTime;

    @ApiModelProperty(value = "状态0保存,1提交")
    private String status;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long createBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "附件对象数组")
    private  List<HrAttachmentVo> attachmentVos;


}
