package com.deloitte.platform.api.srpmp.relust.param;
import java.sql.Clob;
import java.time.LocalDateTime;

import com.deloitte.platform.common.core.entity.param.BaseParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description :  SrpmsResult查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsResultQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String resultNum;
    private String resultName;
    private String resultType;
    private String transFlag;	
    private String projectNum;
    private String projectName;
    private String deptName;
    private String personName;
    private String detail;
    private String status;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
