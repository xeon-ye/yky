package com.deloitte.platform.api.srpmp.project.base.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-26
 * @Description :   SrpmsProjectDept查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectDept查询表单")
@Data
public class SrpmsProjectDeptQueryForm extends BaseQueryForm<SrpmsProjectDeptQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "单位名称")
    private String deptName;

    @ApiModelProperty(value = "邮编")
    private String zipCode;

    @ApiModelProperty(value = "通讯地址")
    private String address;

    @ApiModelProperty(value = "联系人")
    private String contactsName;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "传真号码")
    private String faxNumber;

    @ApiModelProperty(value = "单位性质CODE")
    private String deptQuality;

    @ApiModelProperty(value = "单位主管部门")
    private String deptManDepartment;

    @ApiModelProperty(value = "单位隶属")
    private String deptSubjection;

    @ApiModelProperty(value = "单位法人姓名")
    private String deptLegalPersonName;

    @ApiModelProperty(value = "单位所属地区省")
    private String provinceCode;

    @ApiModelProperty(value = "单位所属地区市")
    private String cityCode;

    @ApiModelProperty(value = "单位所属地区县")
    private String countyCode;

    @ApiModelProperty(value = "零余额账户-单位开户名称")
    private String bankAccountNameLoose;

    @ApiModelProperty(value = "零余额账户-银行名称")
    private String bankNameLoose;

    @ApiModelProperty(value = "零余额账户-银行账号")
    private String bankAccountNumberLoose;

    @ApiModelProperty(value = "零余额账户-单位开户名称")
    private String bankAccountNameBase;

    @ApiModelProperty(value = "零余额账户-银行名称")
    private String bankNameBase;

    @ApiModelProperty(value = "零余额账户-银行账号")
    private String bankAccountNumberBase;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "源单位ID")
    private Long sourceDeptId;

    @ApiModelProperty(value = "单位组织机构代码")
    private String orgCode;
}
