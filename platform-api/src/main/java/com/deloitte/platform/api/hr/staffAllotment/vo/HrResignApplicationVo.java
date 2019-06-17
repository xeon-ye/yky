package com.deloitte.platform.api.hr.staffAllotment.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-03
 * @Description : HrResignApplication返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrResignApplicationVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "申请人编码")
    private String staffCode;

    @ApiModelProperty(value = "申请人日期")
    private LocalDateTime dat;

    @ApiModelProperty(value = "辞职原因：下拉选项")
    private String reason;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "辞职说明")
    private String exp;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

    @ApiModelProperty(value = "档案接收单位")
    private String fileReceivingUnit;

    @ApiModelProperty(value = "离职去向")
    private String leaveTo;

    @ApiModelProperty(value = "附件列表")
    private ArrayList<HrAttachmentForm> attachments;

}
