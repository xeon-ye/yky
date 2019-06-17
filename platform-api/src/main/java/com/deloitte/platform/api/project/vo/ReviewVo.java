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
 * @Date : Create in 2019-05-25
 * @Description : Review返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "评审ID")
    private String reviewId;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "预算ID")
    private String budgetId;

    @ApiModelProperty(value = "支出ID")
    private String expenseId;

    @ApiModelProperty(value = "绩效ID")
    private String performanceId;

    @ApiModelProperty(value = "院级排序")
    private String schoolPriority;

    @ApiModelProperty(value = "一级项目")
    private String firstLevelProject;

    @ApiModelProperty(value = "评审意见")
    private String reviewAdvice;

    @ApiModelProperty(value = "评审状态name")
    private String reviewStatusName;

    @ApiModelProperty(value = "评审状态code")
    private String reviewStatusCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "拓展字段1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段5")
    private String ext5;

    @ApiModelProperty(value = "数据权限维度字段ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "数据权限维度字段ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "审批人")
    private String approver;

    @ApiModelProperty(value = "审批时间")
    private LocalDateTime approverTime;

    @ApiModelProperty(value = "意见")
    private String opinion;

    @ApiModelProperty(value = "一级项目code")
    private String firstLevelProjectCode;

    @ApiModelProperty(value = "业务单号流水号")
    private String serviceNum;

}
