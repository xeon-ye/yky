package com.deloitte.platform.api.hr.common.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-15
 * @Description : ProcessNumber返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessNumberVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "流程单号代表项目")
    private String name;

    @ApiModelProperty(value = "流程单号前缀")
    private String processNumberCode;

    @ApiModelProperty(value = "流程单号中间日期")
    private String processNumberDate;

    @ApiModelProperty(value = "${field.comment}")
    private String processNumberLast;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

}
