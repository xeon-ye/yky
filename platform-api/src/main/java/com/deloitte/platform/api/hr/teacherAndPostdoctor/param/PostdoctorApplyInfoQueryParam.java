package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  PostdoctorApplyInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostdoctorApplyInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long postdoctorApplyId;
    private LocalDateTime applyPushTime;
    private String pushArchivesInfo;
    private String pushRemark;
    private String attachmentPushUrl;
    private LocalDateTime applyDelayTime;
    private String delayDuration;
    private String delayRemark;
    private String attachmentDelayUrl;
    private Long newTeacherId;
    private String newTeacherName;
    private String replaceRemark;
    private String attachmentReplaceUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createBy;
    private Long updateBy;
    private String orgCode;

}
