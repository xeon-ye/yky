package com.deloitte.platform.api.hr.ebs.vo;
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
 * @Date : Create in 2019-06-04
 * @Description : EmployeeMesTrain返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMesTrainVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "员工自助ID")
    private String empMesId;

    @ApiModelProperty(value = "流程申请ID")
    private String mesTid;

    @ApiModelProperty(value = "培训项目名称")
    private String trainProject;

    @ApiModelProperty(value = "培训起始日期")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "培训终止日期")
    private LocalDateTime endDate;

    @ApiModelProperty(value = "主办单位类别")
    private String sponsorCategory;

    @ApiModelProperty(value = "主办单位")
    private String sponsor;

    @ApiModelProperty(value = "培训类别")
    private String trainCategory;

    @ApiModelProperty(value = "培训形式")
    private String trainForm;

    @ApiModelProperty(value = "培训学时")
    private String trainHours;

    @ApiModelProperty(value = "培训成绩")
    private String trainRecord;

    @ApiModelProperty(value = "培训证书")
    private String trainCertificate;

    @ApiModelProperty(value = "证书有效起始日期")
    private LocalDateTime certificateStatrDate;

    @ApiModelProperty(value = "证书有效终止日期")
    private LocalDateTime certificateEndDate;

    @ApiModelProperty(value = "发证机关")
    private String authority;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
