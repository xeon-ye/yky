package com.deloitte.platform.api.oaservice.noticeper.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : jianghaixun
 * @Date : Create in 2019-05-29
 * @Description :  OaNoticePermission查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OaNoticePermissionQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String type;
    private Long objectId;
    private String orgCode;
    private String deptCode;
    private LocalDateTime createTime;

}
