package com.deloitte.platform.api.oaservice.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : fuqiao
 * @Date : Create in 2019-04-23
 * @Description :  OaScheduleShareConfig查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OaScheduleShareConfigQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String userName;
    private String shareToId;
    private String shareToName;
    private String shareToDeptId;
    private String shareToDeptName;
    private String permissions;
    private String enableFlag;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;

}
