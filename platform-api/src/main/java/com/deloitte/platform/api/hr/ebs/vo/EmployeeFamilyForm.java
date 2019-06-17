package com.deloitte.platform.api.hr.ebs.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : JETVAE
 * @Date : Create in 2019-06-04
 * @Description : EmployeeFamily新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeFamily表单")
@Data
public class EmployeeFamilyForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "1 男 2 女 ")
    private String sex;

    @ApiModelProperty(value = "成员关系（1父亲，2母亲，3配偶，4儿子，5女儿，6兄弟姐妹，7其他）")
    private String relationship;

    @ApiModelProperty(value = "出生日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

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

    @ApiModelProperty(value = "是否取得外国国籍、境外长期或永久居留权（1是，0否）")
    private String isResidence;

    @ApiModelProperty(value = "备注")
    private String remark;

}
