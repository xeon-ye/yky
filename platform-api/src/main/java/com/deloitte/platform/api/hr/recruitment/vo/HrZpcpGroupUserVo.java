package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description : HrZpcpGroupUser返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrZpcpGroupUserVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "部门编码")
    private String deptName;

    @ApiModelProperty(value = "部门编码")
    private String depCode;

    @ApiModelProperty(value = "院校,0:院内,1:校外")
    private String Institution;

    @ApiModelProperty(value = "院校名称")
    private String InstitutionName;

    @ApiModelProperty(value = "岗位编码")
    private String positionCode;

    @ApiModelProperty(value = "职称名称")
    private String titleName;

    @ApiModelProperty(value = "专家级别")
    private  String expertLevel;

    @ApiModelProperty(value = "有效状态,0无效,1有效")
    private  String status;

    @ApiModelProperty(value = "院校编码")
    private  String institution;

    @ApiModelProperty(value = "职称编码")
    private  String titelCode;

    @ApiModelProperty(value = "专家级别名称")
    private  String expertLevelName;

    @ApiModelProperty(value = "组织机构编码")
    private  String organizationCode;

    private  String postName;

    private  String isvalid;
}
