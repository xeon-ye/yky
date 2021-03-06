package com.deloitte.platform.api.contract.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : SignInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增SignInfo表单")
@Data
public class SignInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "合同编号")
    private String contractId;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "我方用印日期")
    private LocalDateTime ourPrintTime;

    @ApiModelProperty(value = "用印合同份数")
    private String ourPrintContractNum;

    @ApiModelProperty(value = "我方签字人")
    private String ourSignPerson;

    @ApiModelProperty(value = "我方签署时间")
    private LocalDateTime ourSignTime;

    @ApiModelProperty(value = "我方签字合同份数")
    private String ourSignContractNum;

    @ApiModelProperty(value = "合同对方名称")
    private String otherContractName;

    @ApiModelProperty(value = "对方法定代表人")
    private String otherLegalPerson;

    @ApiModelProperty(value = "对方签字人")
    private String otherSignPerson;

    @ApiModelProperty(value = "对方签署时间")
    private LocalDateTime otherSignTime;

    @ApiModelProperty(value = "对方授权委托书")
    private String otherAuthorization;

    @ApiModelProperty(value = "对方签署份数")
    private String otherSignContractNum;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUsed;

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

    @ApiModelProperty(value = "打印方式")
    private String  stampWay;

    @ApiModelProperty(value = "签署阶段")
    private String signStage;

    @ApiModelProperty(value = "1 正文 2 附件 3 启动依据")
    private String fileType;

    @ApiModelProperty(value = "流程ID")
    private String approveId;

    @ApiModelProperty(value = "我方签约主体")
    private String ourSubjectInfo;

    @ApiModelProperty(value = "签约主体编码")
    private String subjectCode;

    @ApiModelProperty(value = "签署信息Id")
    private String signInfoId;

    @ApiModelProperty(value = "签约主体类型（1我方，2对方）")
    private String type;

    @ApiModelProperty(value = "对方授权委托书URL")
    private String otherAuthorizationUrl;

    @ApiModelProperty(value = "对方授权委托书名称")
    private String otherAuthorizationName;
}
