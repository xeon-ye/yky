package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-07
 * @Description : Dept返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位code")
    private String code;
    public String getCode(){
        return orgCode;
    }

    @ApiModelProperty(value = "ID")
    private String id;

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

    @ApiModelProperty(value = "单位code")
    private String deptCode;

    @ApiModelProperty(value = "成立时间")
    private LocalDateTime establishedTime;

    @ApiModelProperty(value = "所有制形式")
    private String ownershipType;

    @ApiModelProperty(value = "主管单位名称")
    private String adminName;

    @ApiModelProperty(value = "单位法人性别")
    private String deptLegalPersonGender;

    @ApiModelProperty(value = "单位法人生日")
    private LocalDateTime deptLegalPersonBothday;

    @ApiModelProperty(value = "单位法人专业")
    private String deptLegalPersonJob;

    @ApiModelProperty(value = "单位法人最高学历")
    private String deptLegalPersonEducation;

    @ApiModelProperty(value = "单位法人职称")
    private String deptLegalPersonJobtitle;

    @ApiModelProperty(value = "主要联系人名称")
    private String mainName;

    @ApiModelProperty(value = "主要联系人性别")
    private String mainGender;

    @ApiModelProperty(value = "主要联系人生日")
    private LocalDateTime mainBothday;

    @ApiModelProperty(value = "主要联系人职称")
    private String mainJob;

    @ApiModelProperty(value = "主要联系人电话")
    private String mainTell;

    @ApiModelProperty(value = "主要联系人邮箱")
    private String mainEmail;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "单位类型")
    private String deptType;

    @ApiModelProperty(value = "统一社会信用代码")
    private String creditCode;

    @ApiModelProperty(value = "纳税人识别号")
    private String taxpayerNumber;

    @ApiModelProperty(value = "开户银行")
    private String bankAccount;

    @ApiModelProperty(value = "账户名称")
    private String bankName;

    @ApiModelProperty(value = "银行账号")
    private String bankAccountNumber;

    @ApiModelProperty(value = "类型（0：内部  1：外部）")
    private Integer groupType;

    @ApiModelProperty(value = "主要联系人移动电话")
    private String mainPhone;

    @ApiModelProperty(value = "附件信息列表")
    private List<AttachmentVo> attachments;

    /**
     * 科研需要添加的3个单位有关的字段
     */
    @ApiModelProperty(value = "单位ID")
    private String deptId;

    @ApiModelProperty(value = "单位电话")
    private String deptPhone;

    @ApiModelProperty(value = "单位邮箱")
    private String deptEmail;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "所属区域")
    private String region;

    @ApiModelProperty(value = "备注")
    private String remarks;

    public String getDeptId(){
        return StringUtils.isNotEmpty(deptId)?deptId:String.valueOf(id);
    }
    public String getDeptPhone(){
        return StringUtils.isNotEmpty(deptPhone)?deptPhone:phone;
    }
    public String getDeptEmail(){
        return StringUtils.isNotEmpty(deptEmail)?deptEmail:email;
    }

}
