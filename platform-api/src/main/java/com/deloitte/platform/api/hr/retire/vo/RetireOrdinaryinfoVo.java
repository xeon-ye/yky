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
 * @Description : RetireOrdinaryinfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetireOrdinaryinfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "普通退休表ID")
    private Long ordinaryId;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String empName;

    @ApiModelProperty(value = "性别")
    private String empSex;

    @ApiModelProperty(value = "年龄")
    private String empAge;

    @ApiModelProperty(value = "部门")
    private String empDep;

    @ApiModelProperty(value = "岗位")
    private String empPosition;

    @ApiModelProperty(value = "拟定退休日期")
    private LocalDateTime retireDate;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

}
