package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : hemingzheng
 * @Date : Create in 2019-04-16
 * @Description : ProcessOperatorTransfer返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessOperatorTransferVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private String id;

    @ApiModelProperty(value = "合同编号")
    private Long contractId;

    @ApiModelProperty(value = "发起人编号")
    private String operatorCode;

    @ApiModelProperty(value = "发起人")
    private String operator;

    @ApiModelProperty(value = "新经办人编号")
    private String newOperatorCode;

    @ApiModelProperty(value = "新经办人")
    private String newOperator;

    @ApiModelProperty(value = "经办人移交原因")
    private String transferResult;

    @ApiModelProperty(value = "流程id")
    private String processId;

    @ApiModelProperty(value = "流程实例id")
    private String processInstanceId;

    @ApiModelProperty(value = "审批状态")
    private String statue;

    @ApiModelProperty(value = "发起时间")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备用字段")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    private Long spareField5;



    @ApiModelProperty(value = "合同基本信息")
    private List<BasicInfoVo> listBasicInfoVo;

    @ApiModelProperty(value = "审批节点")
    private List<ContractProcessTaskVo> processTaskVoList;

    @ApiModelProperty(value = "合同打印方式 1是可以撤回  0是不可以撤回")
    private String canRollBack;

    @ApiModelProperty(value = "经办人部门ID")
    private String orgId;

    @ApiModelProperty(value = "经办人部门")
    private String org;

    @ApiModelProperty(value = "发起人部门Id")
    private String oldOrgId;

    @ApiModelProperty(value = "发起人部门")
    private String oldOrg;

    @ApiModelProperty(value = "新经办人联系方式")
    private String operatorInfomation;

    @ApiModelProperty(value = "经办人联系方式")
    private String oldOperatorInfomation;

}
