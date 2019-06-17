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
 * @Date : Create in 2019-05-17
 * @Description :  ExeChange查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExeChangeQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long changeId;
    private Long executionId;
    private String changeAdv;
    private Long changeStatusCode;
    private String changeStatusName;
    private LocalDateTime hisTime;
    private String hisBy;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private Long orgId;
    private String orgPath;

}
