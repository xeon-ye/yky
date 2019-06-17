package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-12
 * @Description : GccEmployContract返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccEmployContractVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "教职工编号")
    private String userId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "所属单位")
    private String unit;

    @ApiModelProperty(value = "合同开始日期")
    private LocalDate contractStartdate;

    @ApiModelProperty(value = "合同结束日期")
    private LocalDate contractEnddate;

    @ApiModelProperty(value = "是否需签合同")
    private String signContract;

    @ApiModelProperty(value = "附件")
    private String enclosure;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private String createBy;

    @ApiModelProperty(value = "修改人id")
    private String updateBy;

    @ApiModelProperty(value = "合同到期日期 -1为过期,0表示1-3,1为未到期 ")
    private int betweenMonth;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentVo attachmentVo;

    @ApiModelProperty(value = "附件信息对象 -离职证明")
    private HrAttachmentVo attachmentVo1;

    @ApiModelProperty(value = "附件信息对象 -合同稿")
    private HrAttachmentVo attachmentVo2;
}
