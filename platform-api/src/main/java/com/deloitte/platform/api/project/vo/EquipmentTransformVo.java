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
 * @Date : Create in 2019-05-31
 * @Description : EquipmentTransform返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentTransformVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "设备改造升级ID")
    private String equTransId;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "明细描述")
    private String equAbstract;

    @ApiModelProperty(value = "购置时间")
    private LocalDateTime equDate;

    @ApiModelProperty(value = "原价值")
    private String equValue;

    @ApiModelProperty(value = "利用的主要技术和升级改造的主要内容摘要")
    private String equUse;

    @ApiModelProperty(value = "实施周期")
    private String equCycle;

    @ApiModelProperty(value = "中央财政经费")
    private String fundingCenter;

    @ApiModelProperty(value = "主管部门经费")
    private String fundingDirector;

    @ApiModelProperty(value = "其他经费")
    private String fundingOther;

    @ApiModelProperty(value = "经费合计")
    private String fundingTotal;

    @ApiModelProperty(value = "创建")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "拓展字段")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    private String orgPath;

}
