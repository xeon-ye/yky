package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description :  Ou查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OuQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String ouId;
    private String applicationId;
    private String ouCode;
    private String ouName;
    private String departmentCode;
    private String department;
    private String corporateRepreId;
    private String contactsId;
    private String ouOrganization;
    private String actualStaffNumber;
    private String numberResearchers;
    private String numberOfRetirees;
    private String researchersAged3550;
    private String academicianQuantity;
    private String phdQuantity;
    private String masterQuantity;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Long orgId;
    private String orgPath;
    private String corporateRepreName;
    private String contactsName;

}
