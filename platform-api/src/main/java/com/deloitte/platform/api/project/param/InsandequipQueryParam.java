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
 * @Date : Create in 2019-05-31
 * @Description :  Insandequip查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsandequipQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Long orgId;
    private String orgPath;
    private Long id;
    private String insandequipId;
    private String applicationId;
    private String classifiedStatisticsCode;
    private String classifiedStatisticsName;
    private Long totalNumber;
    private Long useNumber;
    private Long originalValue;
    private Long useOriginalValue;

}
