package com.deloitte.platform.api.oaservice.meeting.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :  OaMeetingHeader查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OaMeetingHeaderQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private String id;
    private String meetingNo;
    private String meetingTitle;
    private String meetingContent;
    private String emergency;
    private String createByName;
    private String deptId;
    private String deptName;
    private String contactUserId;
    private String contactUserName;
    private String contactTelphone;
    private String contactEmail;
    private String meetingStartDate;
    private String meetingEndDate;
    private String startTime;
    private String endTime;
    private String meetingType;
    private String outMembers;
    private String isBack;
    private String isNotice;
    private String meetingResouce;
    private String remarks;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;

}
