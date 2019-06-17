package com.deloitte.services.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 合同签署信息
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_SIGN_INFO")
@ApiModel(value="SignInfo对象", description="合同签署信息")
public class SignInfo extends Model<SignInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "合同编号")
    @TableField("CONTRACT_ID")
    private String contractId;

    @ApiModelProperty(value = "合同名称")
    @TableField("CONTRACT_NAME")
    private String contractName;

    @ApiModelProperty(value = "我方用印日期")
    @TableField("OUR_PRINT_TIME")
    private LocalDateTime ourPrintTime;

    @ApiModelProperty(value = "用印合同份数")
    @TableField("OUR_PRINT_CONTRACT_NUM")
    private String ourPrintContractNum;

    @ApiModelProperty(value = "我方签字人")
    @TableField("OUR_SIGN_PERSON")
    private String ourSignPerson;

    @ApiModelProperty(value = "我方签署时间")
    @TableField("OUR_SIGN_TIME")
    private LocalDateTime ourSignTime;

    @ApiModelProperty(value = "我方签字合同份数")
    @TableField("OUR_SIGN_CONTRACT_NUM")
    private String ourSignContractNum;

    @ApiModelProperty(value = "合同对方名称")
    @TableField("OTHER_CONTRACT_NAME")
    private String otherContractName;

    @ApiModelProperty(value = "对方法定代表人")
    @TableField("OTHER_LEGAL_PERSON")
    private String otherLegalPerson;

    @ApiModelProperty(value = "对方签字人")
    @TableField("OTHER_SIGN_PERSON")
    private String otherSignPerson;

    @ApiModelProperty(value = "对方签署时间")
    @TableField("OTHER_SIGN_TIME")
    private LocalDateTime otherSignTime;

    @ApiModelProperty(value = "对方授权委托书")
    @TableField("OTHER_AUTHORIZATION")
    private String otherAuthorization;

    @ApiModelProperty(value = "对方签署份数")
    @TableField("OTHER_SIGN_CONTRACT_NUM")
    private String otherSignContractNum;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    @TableField("IS_USED")
    private String isUsed;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_1")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_2")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_3")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_4")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_5")
    private Long spareField5;

    @ApiModelProperty(value = "打印方式")
    @TableField("STAMP_WAY")
    private String stampWay;

    @ApiModelProperty(value = "签署阶段")
    @TableField("SIGN_STAGE")
    private String signStage;

    @ApiModelProperty(value = "我方签约主体")
    @TableField("OUR_SUBJECT_INFO")
    private String ourSubjectInfo;

    @ApiModelProperty(value = "签约主体编码")
    @TableField("SUBJECT_CODE")
    private String subjectCode;

    @ApiModelProperty(value = "签约主体类型（1我方，2对方）")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "对方授权委托书URL")
    @TableField("OTHER_AUTHORIZATION_URL")
    private String otherAuthorizationUrl;


    @ApiModelProperty(value = "对方授权委托书名称")
    @TableField("OTHER_AUTHORIZATION_NAME")
    private String otherAuthorizationName;



    public static final String ID = "ID";

    public static final String CONTRACT_ID = "CONTRACT_ID";

    public static final String CONTRACT_NAME = "CONTRACT_NAME";

    public static final String OUR_PRINT_TIME = "OUR_PRINT_TIME";

    public static final String OUR_PRINT_CONTRACT_NUM = "OUR_PRINT_CONTRACT_NUM";

    public static final String OUR_SIGN_PERSON = "OUR_SIGN_PERSON";

    public static final String OUR_SIGN_TIME = "OUR_SIGN_TIME";

    public static final String OUR_SIGN_CONTRACT_NUM = "OUR_SIGN_CONTRACT_NUM";

    public static final String OTHER_CONTRACT_NAME = "OTHER_CONTRACT_NAME";

    public static final String OTHER_LEGAL_PERSON = "OTHER_LEGAL_PERSON";

    public static final String OTHER_SIGN_PERSON = "OTHER_SIGN_PERSON";

    public static final String OTHER_SIGN_TIME = "OTHER_SIGN_TIME";

    public static final String OTHER_AUTHORIZATION = "OTHER_AUTHORIZATION";

    public static final String OTHER_SIGN_CONTRACT_NUM = "OTHER_SIGN_CONTRACT_NUM";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String IS_USED = "IS_USED";

    public static final String SPARE_FIELD_1 = "SPARE_FIELD_1";

    public static final String SPARE_FIELD_2 = "SPARE_FIELD_2";

    public static final String SPARE_FIELD_3 = "SPARE_FIELD_3";

    public static final String SPARE_FIELD_4 = "SPARE_FIELD_4";

    public static final String SPARE_FIELD_5 = "SPARE_FIELD_5";

    public static  final String STAMP_WAY="STAMP_WAY";

    public  static final String SIGN_STAGE="SIGN_STAGE";

    public  static final String OUR_SUBJECT_INFO="OUR_SUBJECT_INFO";

    public  static final String SUBJECT_CODE="SUBJECT_CODE";

    public static final String TYPE = "TYPE";

    public static final String OTHER_AUTHORIZATION_NAME = "OTHER_AUTHORIZATION_NAME";

    public static final String OTHER_AUTHORIZATION_URL = "OTHER_AUTHORIZATION_URL";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
