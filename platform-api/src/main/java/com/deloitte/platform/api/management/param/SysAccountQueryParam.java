package com.deloitte.platform.api.management.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : jack
 * @Date : Create in 2019-04-18
 * @Description :  SysAccount查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysAccountQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private String avatar;
    private Long userId;
    private Long deptId;
    private String state;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String wxOpenid;

}
