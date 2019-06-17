package com.deloitte.platform.api.oaservice.notice.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :  OaCalender查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OaCalenderQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String applyDeptId;
    private String applyDeptName;
    private String applyUserId;
    private String applyUserName;
    private LocalDateTime applyDatetime;
    private String urgentLevel;
    private Integer delFlag;
    private String approvalStatus;
    private Integer isneedBussiness;
    private LocalDateTime updateDatetime;
    private String updateUserName;
    private String updateUserId;
    private String orderNum;

}
