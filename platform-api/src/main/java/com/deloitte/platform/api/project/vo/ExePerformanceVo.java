package com.deloitte.platform.api.project.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-06-06
 * @Description : ExePerformance返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExePerformanceVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "批复id")
    private String replyId;

    @ApiModelProperty(value = "项目执行id")
    private String executionId;

    @ApiModelProperty(value = "一级指标")
    private String indicators1;

    @ApiModelProperty(value = "二级指标")
    private String indicators2;

    @ApiModelProperty(value = "三级指标")
    private String indicators3;

    @ApiModelProperty(value = "年度指标值")
    private String indicatorsYear;

    @ApiModelProperty(value = "1至7月执行情况")
    private String exeCondition1and7;

    @ApiModelProperty(value = "全年执行情况")
    private String exeConditionYear;

    @ApiModelProperty(value = "经费保障")
    private String fundingSec;

    @ApiModelProperty(value = "制度保障")
    private String systemSec;

    @ApiModelProperty(value = "人员保障")
    private String personSec;

    @ApiModelProperty(value = "硬件保障")
    private String hardwareSec;

    @ApiModelProperty(value = "其他保障")
    private String otherSec;

    @ApiModelProperty(value = "原因说明")
    private String reasonInstruction;

    @ApiModelProperty(value = "完成可能性code")
    private Long targetPlanCode;

    @ApiModelProperty(value = "完成可能行name")
    private String targetPlanName;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    private String ext3;

    @ApiModelProperty(value = "拓展4")
    private String et4;

    @ApiModelProperty(value = "orgid")
    private Long orgId;

}
