package com.deloitte.platform.api.oaservice.meeting.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :  OaMeetingMembers查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OaMeetingMembersQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private String id;
    private String meetingId;
    private String userId;
    private String userName;
    private String deptId;
    private String deptName;
    private String telphone;
    private String email;
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
