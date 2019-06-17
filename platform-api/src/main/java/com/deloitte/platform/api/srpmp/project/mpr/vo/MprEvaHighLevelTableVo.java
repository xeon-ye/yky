package com.deloitte.platform.api.srpmp.project.mpr.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaHighLevelTable返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MprEvaHighLevelTableVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = " 主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "人才类型")
    private String talentType;

    @ApiModelProperty(value = "批准编号")
    private String approvalNumber;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "当选时间")
    private String electedDate;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}
