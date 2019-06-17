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
 * @Date : Create in 2019-06-14
 * @Description :  Projects查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectsQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectId;
    private String projectCode;
    private String projectName;
    private String projectAttribute;
    private String projectTypeCode;
    private String projectTypeName;
    private String planYear;
    private String cycle;
    private String projectHeaderId;
    private String projectHeaderName;
    private String finHeaderId;
    private String projectConnectStaffId;
    private String organizationId;
    private String organizationName;
    private String projectCatgory;
    private String proCancelDes;
    private String proChange;
    private String priority;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String orgId;
    private String orgPath;
    private String projectStatusCode;
    private String projectStatusName;
    private String entrustId;
    private String assumeId;
    private String projectMark;
    private String finHeaderName;
    private String projectConnectStaffName;
    private String projectCatgoryCode;
    private String entrustName;
    private String assumeName;
    private String applicationMark;
    private String zipCode;
    private String ouChargeStaffId;
    private String ouChargeStaffName;
    private String projectHeaderPost;
    private String projectHeaderTel;
    private String proConnectStaffPost;
    private String proConnectStaffTel;
    private String adress;
    private String department;
    private String departmentCode;
    private String appOpartionOu;
    private String replyNewMark;
    private String subactCatagoryCode;
    private String subactCatagoryName;
    private String organizationCode;
    private String projectCatagoryCode;
    private String projectCatagoryName;
    private String createUserName;
    private String createUserId;

}
