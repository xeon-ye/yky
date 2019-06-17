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
 * @Date : Create in 2019-05-23
 * @Description :  Value查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long valueId;
    private String valueCode;
    private String valueName;
    private Long valueStatus;
    private LocalDateTime successTime;
    private Long parId;
    private String parName;
    private LocalDateTime failureTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String createBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String updateBy;
    private String orgId;
    private String orgPath;
    private Long proId;
    private String valueDes;

}
