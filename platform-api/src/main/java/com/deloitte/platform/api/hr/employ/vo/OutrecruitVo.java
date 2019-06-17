package com.deloitte.platform.api.hr.employ.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-10
 * @Description : Outrecruit返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutrecruitVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "拟聘用岗位")
    private String postStation;

    @ApiModelProperty(value = "编制数")
    private String orgNum;

    @ApiModelProperty(value = "现有数")
    private String nowNum;

    @ApiModelProperty(value = "聘用人数")
    private String recruitNum;

    @ApiModelProperty(value = "正式薪资")
    private String salaryFormal;

    @ApiModelProperty(value = "试用期工资")
    private String salaryTry;

    @ApiModelProperty(value = "聘用来源")
    private String recruitSource;

    @ApiModelProperty(value = "试用期开始时间")
    private LocalDateTime tryStartdate;

    @ApiModelProperty(value = "试用期结束时间")
    private LocalDateTime tryEnddate;

    @ApiModelProperty(value = "聘用开始时间")
    private LocalDateTime recruitStartdate;

    @ApiModelProperty(value = "聘用结束时间")
    private LocalDateTime recruitStartenddate;

    @ApiModelProperty(value = "聘任原因")
    private String applyReason;

    @ApiModelProperty(value = "职责描述")
    private String applyDes;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "申请ID")
    private String empId;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentVo> attachments;

}
