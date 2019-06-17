package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-22
 * @Description : HrarchivesinfoApply返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrarchivesinfoApplyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "对象ID")
    private String empId;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "0 查档人员 1档案对象")
    private String type;

    @ApiModelProperty(value = "人事档案申请表ID")
    private String hrAid;

    @ApiModelProperty(value = "复印数")
    private String copynum;

    @ApiModelProperty(value = "归还时间")
    private LocalDateTime returnTime;

    @ApiModelProperty(value = "${field.comment}")
    private String companyname;

    @ApiModelProperty(value = "${field.comment}")
    private String deptname;

    @ApiModelProperty(value = "${field.comment}")
    private String postname;

    @ApiModelProperty(value = "${field.comment}")
    private String polit;

    private String name;

}
