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
 * @Date : Create in 2019-06-06
 * @Description :  ChangePro查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeProQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String changeId;
    private String projectId;
    private String applicationId;
    private String changeAdv;
    private String changeCode;
    private String changeName;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private Long orgId;
    private String orgPath;
    private String serviceNum;
    private LocalDateTime changeDate;
    private String replyId;
    private String maintenceid;
    private String changeStatusCode;
    private String changeStatusName;

}
