package com.deloitte.platform.api.hr.train.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-02
 * @Description : PersonTrainApply返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonTrainApplyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "培训项目")
    private String trainProject;

    @ApiModelProperty(value = "培训类别")
    private String trainType;

    @ApiModelProperty(value = "培训开始时间")
    private LocalDateTime trainStartDate;

    @ApiModelProperty(value = "培训结束时间")
    private LocalDateTime trainEndDate;

    @ApiModelProperty(value = "主办单位")
    private String hostUnit;

    @ApiModelProperty(value = "主办单位级别")
    private String hostUnitLevel;

    @ApiModelProperty(value = "培训形式")
    private String trainMode;

    @ApiModelProperty(value = "备注")
    private String remak;

    @ApiModelProperty(value = "状态（0保存、1提交）")
    private String state;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "员工表ID")
    private String empId;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentVo> attachments;

}
