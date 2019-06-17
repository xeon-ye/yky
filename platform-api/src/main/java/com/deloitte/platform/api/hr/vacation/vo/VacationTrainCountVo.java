package com.deloitte.platform.api.hr.vacation.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-15
 * @Description : VacationTrainCount返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationTrainCountVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "员工基础信息表ID")
    private String empId;

    @ApiModelProperty(value = "事假")
    private String matterLeave = "0";

    @ApiModelProperty(value = "病假")
    private String illnessLeave = "0";

    @ApiModelProperty(value = "婚假")
    private String marriageLeave = "0";

    @ApiModelProperty(value = "产假")
    private String maternityLeave = "0";

    @ApiModelProperty(value = "丧假")
    private String funeralLeave = "0";

    @ApiModelProperty(value = "探亲假")
    private String visitfamilyLeave = "0";

    @ApiModelProperty(value = "其他假")
    private String otherLeave = "0";

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

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "部门")
    private String depCode;

    @ApiModelProperty(value = "职位")
    private String positionCode;

    @ApiModelProperty(value = "教职工ID")
    private String empCode;


}
