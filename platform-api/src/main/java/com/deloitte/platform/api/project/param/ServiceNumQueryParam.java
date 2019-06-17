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
 * @Date : Create in 2019-05-25
 * @Description :  ServiceNum查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceNumQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectId;
    private String applicationId;
    private String applyNum;
    private String applyCancelNum;
    private String reviewNum;
    private String replyNum;
    private String changeNum;
    private String bussNum;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private String serviceOnly;
    private Long curYear;
    private Integer num;

}
