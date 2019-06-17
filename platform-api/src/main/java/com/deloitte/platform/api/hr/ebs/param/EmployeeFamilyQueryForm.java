package com.deloitte.platform.api.hr.ebs.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @Author : JETVAE
 * @Date : Create in 2019-06-04
 * @Description :   EmployeeFamily查询from对象
 * @Modified :
 */
@ApiModel("EmployeeFamily查询表单")
@Data
public class EmployeeFamilyQueryForm extends BaseQueryForm<EmployeeFamilyQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "人员信息CODE")
    private String userCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "1 男 2 女 ")
    private String sex;

    @ApiModelProperty(value = "成员关系（1父亲，2母亲，3配偶，4儿子，5女儿，6兄弟姐妹，7其他）")
    private String relationship;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthDate;

    @ApiModelProperty(value = "学习或工作单位")
    private String company;

    @ApiModelProperty(value = "职务")
    private String duties;

    @ApiModelProperty(value = "学历(1博士研究生,2硕士研究生,3大学本科,4大学专科,5中等专科,6技工学校,7高中,8初中,9小学)")
    private String education;

    @ApiModelProperty(value = "正式面貌(1中共党员,2中共预备党员,3共青团员,4民革会员,5民盟盟员,6民建会员,7民进会员,8农工党党员,9致公党党员,10九三学社社员,11台盟盟员,12无党派民主人士,13群众,14其他)")
    private String politicalOutlook;

    @ApiModelProperty(value = "联系电话")
    private String contactPhone;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private Long careteBy;

    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;

    @ApiModelProperty(value = "是否取得外国国籍、境外长期或永久居留权（1是，0否）")
    private String isResidence;

    @ApiModelProperty(value = "备注")
    private String remark;
}
