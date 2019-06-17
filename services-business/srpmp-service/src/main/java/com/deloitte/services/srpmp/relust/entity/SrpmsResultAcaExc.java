package com.deloitte.services.srpmp.relust.entity;

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
 * 成果管理-学术交流
 * </p>
 *
 * @author Apeng
 * @since 2019-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_RESULT_ACA_EXC")
@ApiModel(value="SrpmsResultAcaExc对象", description="成果管理-学术交流")
public class SrpmsResultAcaExc extends Model<SrpmsResultAcaExc> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单位CODE")
    @TableField("DEPT_CODE")
    private Long deptCode;

    @ApiModelProperty(value = "年")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "月")
    @TableField("MONTH")
    private String month;

    @ApiModelProperty(value = "项目编码")
    @TableField("PRO_NUM")
    private String proNum;

    @ApiModelProperty(value = "项目名称")
    @TableField("PRO_NAME")
    private String proName;

    @ApiModelProperty(value = "区域 10-国内 20-国外")
    @TableField("REGION")
    private String region;

    @ApiModelProperty(value = "主办/参办 10-参办 20-主办")
    @TableField("SPONSOR_FLAG")
    private String sponsorFlag;

    @ApiModelProperty(value = "参与单位")
    @TableField("JOIN_ORG")
    private String joinOrg;

    @ApiModelProperty(value = "会议类型")
    @TableField("JOIN_TYPE")
    private String joinType;

    @ApiModelProperty(value = "参与形式")
    @TableField("JOIN_CAT")
    private String joinCat;

    @ApiModelProperty(value = "举办时间")
    @TableField("HOLDING_TIME")
    private LocalDateTime holdingTime;

    @ApiModelProperty(value = "会议名称")
    @TableField("TEAM_NAME")
    private String teamName;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;


    public static final String ID = "ID";

    public static final String DEPT_CODE = "DEPT_CODE";

    public static final String YEAR = "YEAR";

    public static final String MONTH = "MONTH";

    public static final String PRO_NUM = "PRO_NUM";

    public static final String PRO_NAME = "PRO_NAME";

    public static final String REGION = "REGION";

    public static final String SPONSOR_FLAG = "SPONSOR_FLAG";

    public static final String JOIN_ORG = "JOIN_ORG";

    public static final String JOIN_TYPE = "JOIN_TYPE";

    public static final String JOIN_CAT = "JOIN_CAT";

    public static final String HOLDING_TIME = "HOLDING_TIME";

    public static final String TEAM_NAME = "TEAM_NAME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
