package com.deloitte.platform.api.srpmp.project.base.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : pengchao
 * @Date : Create in 2019-03-09
 * @Description :  SrpmsProjectExpert查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectExpertQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long projectId;
    private String expertId;
    private String expertNum;
    private String expertName;
    private String expertSex;
    private String expertTitle;
    private String expertOrg;
    private String expertSubject;
    private String expertField;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String expertTel;
    private String type;

}
