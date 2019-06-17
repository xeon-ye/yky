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
 * @Date : Create in 2019-05-22
 * @Description :  ApprovalProcess查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalProcessQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String orgCode;
    private String processType;
    private String processDefineKey;
    private String processDefineName;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String isUsed;
    private String ext1;
    private String ext2;
    private String ext3;
    private LocalDateTime ext4;
    private Long ext5;

}
