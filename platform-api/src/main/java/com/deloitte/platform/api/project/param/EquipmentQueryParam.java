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
 * @Description :  Equipment查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String equId;
    private String applicationId;
    private String projectAbs;
    private String model;
    private String equAddress;
    private String equUse;
    private String equNum;
    private String equCycle;
    private String fundingCenter;
    private String fundingDirector;
    private String fundingOther;
    private String fundingTotal;
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

}
