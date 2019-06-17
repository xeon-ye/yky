package com.deloitte.platform.api.hr.retire.vo;
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
 * @Date : Create in 2019-04-12
 * @Description : RehireAgaininfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RehireAgaininfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String empName;

    @ApiModelProperty(value = "性别")
    private String empSex;

    @ApiModelProperty(value = "年龄")
    private Integer empAge;

    @ApiModelProperty(value = "部门")
    private String empDep;

    @ApiModelProperty(value = "原有职务")
    private String empOriginal;

    @ApiModelProperty(value = "技术职务")
    private String empTechnology;

    @ApiModelProperty(value = "拟定退休日期")
    private String rehireDate;

    @ApiModelProperty(value = "拟返聘部门")
    private String returnDep;

    @ApiModelProperty(value = "拟返聘岗位")
    private String returnPosition;

    @ApiModelProperty(value = "返聘开始时间")
    private LocalDateTime returnStartdate;

    @ApiModelProperty(value = "返聘结束时间")
    private LocalDateTime returnEnddate;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "返聘申请表ID")
    private String againId;

}
