package com.deloitte.platform.api.oaservice.meeting.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description : OaMeetingRoom返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OaMeetingRoomVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "会议室地址")
    private String roomName;

    @ApiModelProperty(value = "会议室编号")
    private String roomNum;

    @ApiModelProperty(value = "是否强制备案")
    private String forceBack;

    @ApiModelProperty(value = "是否后勤统管")
    private String logisticsManager;

    @ApiModelProperty(value = "会议室名称")
    private String address;

    @ApiModelProperty(value = "可容纳人数")
    private Double maxPersons;

    @ApiModelProperty(value = "所属机构")
    private String deptId;

    @ApiModelProperty(value = "所属机构名称")
    private String deptName;

    @ApiModelProperty(value = "会议室状态")
    private String roomStatus;

    @ApiModelProperty(value = "会议室设备")
    private String roomResource;

    @ApiModelProperty(value = "会议室负责人")
    private String roomDutyId;

    @ApiModelProperty(value = "会议室负责人姓名")
    private String roomDutyName;

    @ApiModelProperty(value = "排序号")
    private Long orderNum;

    @ApiModelProperty(value = "会议室描述")
    private String roomDesc;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后更新人")
    private String updateBy;

    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;

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
