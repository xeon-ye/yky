package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : zhengchun
 * @Date : Create in 2019-05-26
 * @Description :  ProUser查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProUserQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectId;
    private String userId;
    private String userName;
    private String projectCode;
    private String userNo;
    private String userDep;
    private String userKs;
    private String userPostCode;
    private String userPostName;
    private String userMarkCode;
    private String userMarkName;
    private String phone;
    private String email;
    private String tel;
    private String major;
    private String majorCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;

}
