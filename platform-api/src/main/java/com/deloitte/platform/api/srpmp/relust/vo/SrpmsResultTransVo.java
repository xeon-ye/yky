package com.deloitte.platform.api.srpmp.relust.vo;
import java.time.LocalDateTime;
import java.util.List;

import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeVO;
import com.deloitte.platform.common.core.entity.vo.BaseVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description : SrpmsResultTrans返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsResultTransVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "成果转化id")
    private String id;

    @ApiModelProperty(value = "成果id")
    private String resultId;

    @ApiModelProperty(value = "成果名称")
    private String resultName;
	
    @ApiModelProperty(value = "成果转化名称")
    private String resultTransName;

    @ApiModelProperty(value = "转化方式")
    private String transWay;

    @ApiModelProperty(value = "合同号")
    private String contractNum;

    @ApiModelProperty(value = "合同金额")
    private Long contractAmount;

    @ApiModelProperty(value = "合同签订日")
    private String contractSigningDay;

    @ApiModelProperty(value = "转化费来源")
    private String transFeeSource;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "审批历史")
    private List<TaskNodeVO> approveHistory;

    @ApiModelProperty(value = "附件")
    private List<SrpmsProjectAttachmentVo> attachmentFile;

}
