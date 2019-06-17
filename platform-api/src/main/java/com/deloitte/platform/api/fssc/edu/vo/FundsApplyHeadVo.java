package com.deloitte.platform.api.fssc.edu.vo;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.api.fssc.file.vo.FileVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-29
 * @Description : FundsApplyHead返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundsApplyHeadVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "创建人ID申请人")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名申请人")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "归属单位code")
    private String unitCode;

    @ApiModelProperty(value = "归属单位ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long unitId;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "管理部门code")
    private String deptCode;

    @ApiModelProperty(value = "管理部门ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long deptId;

    @ApiModelProperty(value = "管理部门名称")
    private String deptName;

    @ApiModelProperty(value = "申请经费")
    private String fundType;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "申请总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "行明细")
    private List<FundsApplyLineVo> fundsApplyLineVos;

    @ApiModelProperty(value = "审批历史")
    private List<BpmProcessTaskVo> taskVos;

    @ApiModelProperty(value = "文件列表")
    private List<FileVo> fileVos;
}
