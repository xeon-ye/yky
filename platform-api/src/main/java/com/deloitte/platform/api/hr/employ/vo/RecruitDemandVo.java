package com.deloitte.platform.api.hr.employ.vo;
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
 * @Date : Create in 2019-04-09
 * @Description : RecruitDemand返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitDemandVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "部门")
    private String dep;

    @ApiModelProperty(value = "编制")
    private String organization;

    @ApiModelProperty(value = "现有人员总数")
    private String numberallNow;

    @ApiModelProperty(value = "编内人数")
    private String numbersInside;

    @ApiModelProperty(value = "外聘编制")
    private String numbersOutside;

    @ApiModelProperty(value = "招聘当年年份")
    private String yearNow;

    @ApiModelProperty(value = "当年增加")
    private String yearIncrease;

    @ApiModelProperty(value = "当年减少")
    private String yearReduction;

    @ApiModelProperty(value = "近三年退休人")
    private String threeYearsRetire;

    @ApiModelProperty(value = "申请增员人数")
    private String applyNumbers;

    @ApiModelProperty(value = "增员岗位")
    private String increaseDep;

    @ApiModelProperty(value = "增员原因")
    private String reason;

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

}
