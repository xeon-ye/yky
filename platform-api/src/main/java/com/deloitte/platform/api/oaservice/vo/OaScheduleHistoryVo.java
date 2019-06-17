package com.deloitte.platform.api.oaservice.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-03-26
 * @Description : OaScheduleHistory返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OaScheduleHistoryVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "行号")
    private int rowNum;

    @ApiModelProperty(value = "业务单据标识")
    private String businessId;

    @ApiModelProperty(value = "日程主键")
    private String workId;

    @ApiModelProperty(value = "日程历史简述")
    private String workName;

    @ApiModelProperty(value = "日程历史内容")
    private String workDesc;

    @ApiModelProperty(value = "日程状态")
    private String workStatus;

    @ApiModelProperty(value = "是否显示")
    private String showFlag;

    @ApiModelProperty(value = "日程对象")
    private String userId;

    @ApiModelProperty(value = "日程对象名称")
    private String userName;

    @ApiModelProperty(value = "日程对象所属部门ID")
    private String deptId;

    @ApiModelProperty(value = "日程对象所属部门名称")
    private String deptName;

    @ApiModelProperty(value = "日程开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "日程结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "日程历史创建人")
    private String createBy;

    @ApiModelProperty(value = "日程历史创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "历史记录最后更新人")
    private String updateBy;

    @ApiModelProperty(value = "历史记录日程最后更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "个人日程类型")
    private String personType;

    @ApiModelProperty(value = "提醒方式")
    private String noticeType;

    @ApiModelProperty(value = "备用字段")
    private String ext1;

    @ApiModelProperty(value = "备用字段")
    private String ext2;

    @ApiModelProperty(value = "备用字段")
    private String ext3;

    @ApiModelProperty(value = "备用字段")
    private String ext4;

    @ApiModelProperty(value = "备用字段")
    private String ext5;

}
