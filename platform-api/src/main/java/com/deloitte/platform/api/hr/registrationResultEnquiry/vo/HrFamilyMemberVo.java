package com.deloitte.platform.api.hr.registrationResultEnquiry.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description : HrFamilyMember返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrFamilyMemberVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "社会人员个人基本情况ID")
    private Long socialId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "关系")
    private String relation;

    @ApiModelProperty(value = "年龄")
    private Long age;

    @ApiModelProperty(value = "所在单位及部门")
    private String companyAndDepartment;

    @ApiModelProperty(value = "职务")
    private String duty;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "毕业生个人基本情况ID")
    private Long graduateId;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

}
