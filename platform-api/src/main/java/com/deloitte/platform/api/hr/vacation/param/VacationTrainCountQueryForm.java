package com.deloitte.platform.api.hr.vacation.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-15
 * @Description :   VacationTrainCount查询from对象
 * @Modified :
 */
@ApiModel("VacationTrainCount查询表单")
@Data
public class VacationTrainCountQueryForm extends BaseQueryForm<VacationTrainCountQueryParam>  {

    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "员工基础信息表ID")
    private String empId;

    @ApiModelProperty(value = "教职工编号")
    private String teachNum;

    @ApiModelProperty(value = "事假")
    private String matterLeave;

    @ApiModelProperty(value = "病假")
    private String illnessLeave;

    @ApiModelProperty(value = "婚假")
    private String marriageLeave;

    @ApiModelProperty(value = "产假")
    private String maternityLeave;

    @ApiModelProperty(value = "丧假")
    private String funeralLeave;

    @ApiModelProperty(value = "探亲假")
    private String visitfamilyLeave;

    @ApiModelProperty(value = "其他假")
    private String otherLeave;

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

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "部门编码")
    private String depCode;

    @ApiModelProperty(value = "岗位编码")
    private String positionCode;

    @ApiModelProperty(value = "处级级别")
    private String chiefRank;

    @ApiModelProperty(value = "职工名字")
    private String name;

    @ApiModelProperty(value = "请假开始日期")
    private LocalDateTime leaveStartDate;

    @ApiModelProperty(value = "请假结束日期")
    private LocalDateTime leaveEndDate;

}
