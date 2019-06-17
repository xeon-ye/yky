package com.deloitte.platform.api.hr.gcc.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccProDecNotice查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccProDecNoticeQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String noticeName;
    private String releaseUnit;
    private String projectName;
    private String projectCategroy;
    private LocalDateTime releaseTime;
    private String noticeCotent;
    private String publishNow;
    private String applyYear;
    private String type;
    private String remarks;
    private Long enclosure;
    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private Long projectCategroyId;
    private Long projecId;
    private String batch;
    private String status;
    private String acaStart;
    private String schStart;
    private Long parentId;
}
