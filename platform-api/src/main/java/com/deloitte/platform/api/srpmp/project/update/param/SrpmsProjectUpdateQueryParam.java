package com.deloitte.platform.api.srpmp.project.update.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : Apeng
 * @Date : Create in 2019-04-01
 * @Description :  SrpmsProjectUpdate查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectUpdateQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String updateNumber;
    private Long deptId;
    private Long projectId;
    private String projectCode;
    private String projectName;
    private String projectType;
    private String leadPersonName;
    private Long fileId;
    private String fileName;
    private String fileUrl;
    private String updateType;
    private String updateReason;
    private String updateState;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
