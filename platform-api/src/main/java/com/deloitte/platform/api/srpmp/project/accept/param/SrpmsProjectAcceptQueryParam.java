package com.deloitte.platform.api.srpmp.project.accept.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description :  SrpmsProjectAccept查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectAcceptQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long projectId;
    private String projectNum;
    private String projectName;
    private String projectType;
    private String deptName;
    private LocalDateTime projectActionDateStart;
    private LocalDateTime projectActionDateEnd;
    private String status;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
