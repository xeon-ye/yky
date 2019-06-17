package com.deloitte.platform.api.oaservice.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : fuqiao
 * @Date : Create in 2019-03-26
 * @Description :  OaScheduleHistory查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OaScheduleHistoryQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private String id;
    private int rowNum;
    private String businessId;
    private String workId;
    private String workName;
    private String workDesc;
    private String workStatus;
    private String showFlag;
    private String userId;
    private String userName;
    private String deptId;
    private String deptName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String personType;
    private String noticeType;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;

}
