package com.deloitte.platform.api.project.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description : Ou返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OuVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "单位情况ID")
    private String ouId;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "单位编码")
    private String ouCode;

    @ApiModelProperty(value = "单位名称")
    private String ouName;

    @ApiModelProperty(value = "所属部门CODE")
    private String departmentCode;

    @ApiModelProperty(value = "所属部门")
    private String department;

    @ApiModelProperty(value = "法人代表ID")
    private String corporateRepreId;

    @ApiModelProperty(value = "联系人ID")
    private String contactsId;

    @ApiModelProperty(value = "编制人数")
    private String ouOrganization;

    @ApiModelProperty(value = "实有人数")
    private String actualStaffNumber;

    @ApiModelProperty(value = "专职科研人员数")
    private String numberResearchers;

    @ApiModelProperty(value = "离退休人员数")
    private String numberOfRetirees;

    @ApiModelProperty(value = "35-50岁中青年科研人员")
    private String researchersAged3550;

    @ApiModelProperty(value = "院士（人）")
    private String academicianQuantity;

    @ApiModelProperty(value = "在读博士生（人）")
    private String phdQuantity;

    @ApiModelProperty(value = "在读硕士生（人）")
    private String masterQuantity;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "拓展字段")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    private String orgPath;

    @ApiModelProperty(value = "法人代表名称")
    private String corporateRepreName;

    @ApiModelProperty(value = "联系人名称")
    private String contactsName;

}
