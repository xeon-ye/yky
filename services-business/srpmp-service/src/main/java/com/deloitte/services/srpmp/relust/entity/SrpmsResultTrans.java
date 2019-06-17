package com.deloitte.services.srpmp.relust.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 科研成果转化
 * </p>
 *
 * @author pengchao
 * @since 2019-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_RESULT_TRANS")
@ApiModel(value="SrpmsResultTrans对象", description="科研成果转化")
public class SrpmsResultTrans extends Model<SrpmsResultTrans> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "成果转化id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;	

    @ApiModelProperty(value = "成果id")
    @TableField("RESULT_ID")
    private Long resultId;

    @ApiModelProperty(value = "成果名称")
    @TableField("RESULT_NAME")
    private String resultName;

    @ApiModelProperty(value = "成果转化编号")
    @TableField("RESULT_TRANS_NUM")
    private String resultTransNum;

    @ApiModelProperty(value = "成果转化名称")
    @TableField("RESULT_TRANS_NAME")
    private String resultTransName;

    @ApiModelProperty(value = "转化方式")
    @TableField("TRANS_WAY")
    private String transWay;

    @ApiModelProperty(value = "合同号")
    @TableField("CONTRACT_NUM")
    private String contractNum;

    @ApiModelProperty(value = "合同金额")
    @TableField("CONTRACT_AMOUNT")
    private Long contractAmount;

    @ApiModelProperty(value = "合同签订日")
    @TableField("CONTRACT_SIGNING_DAY")
    private String contractSigningDay;

    @ApiModelProperty(value = "转化费来源")
    @TableField("TRANS_FEE_SOURCE")
    private String transFeeSource;

    @ApiModelProperty(value = "备注")
    @TableField("REMARKS")
    private String remarks;

    @ApiModelProperty(value = "状态")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


    public static final String ID = "ID";

    public static final String RESULT_ID = "RESULT_ID";

    public static final String RESULT_NAME = "RESULT_NAME";

    public static final String RESULT_TRANS_NUM = "RESULT_TRANS_NUM";

    public static final String RESULT_TRANS_NAME = "RESULT_TRANS_NAME";

    public static final String TRANS_WAY = "TRANS_WAY";

    public static final String CONTRACT_NUM = "CONTRACT_NUM";

    public static final String CONTRACT_AMOUNT = "CONTRACT_AMOUNT";

    public static final String CONTRACT_SIGNING_DAY = "CONTRACT_SIGNING_DAY";

    public static final String TRANS_FEE_SOURCE = "TRANS_FEE_SOURCE";

    public static final String REMARKS = "REMARKS";

    public static final String STATUS = "STATUS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
