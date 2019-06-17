package com.deloitte.platform.api.oaservice.notice.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description :  OaNotice查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OaNoticeQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String urgentLevel;
    private String noticeTypeCode;
    private String noticeTypeName;
    private String applyOrgCode;
    private String applyOrgName;
    private String applyUserId;
    private String applyUserName;
    private LocalDateTime applyDatetime;
    private String noticeSortCode;
    private String noticeSortName;
    private Long noticeHit;
    private String noticeContent;
    private Integer delFlag;
    private String approvalStatus;
    private Integer isneedBussiness;
    private LocalDateTime updateDatetime;
    private String updateUserName;
    private String updateUserId;
    private String orderNum;
    private String applyDeptCode;
    private String deptPer;

}
