package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : tankui
 * @Date : Create in 2019-04-13
 * @Description :  HrZpcpEmployNotice查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrZpcpEmployNoticeQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String unit;
    private Long stationId;
    private String employName;
    private String noticeName;
    private String noticeContent;
    private LocalDateTime publicDate;
    private String isPublic;
    private Long enclosure;
    private String organizationCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private String batch;
    private String year;

}
