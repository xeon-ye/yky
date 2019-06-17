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
 * 横纵向-题录新获项目
 * </p>
 *
 * @author Apeng
 * @since 2019-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_RESULT_NEW_TITLE")
@ApiModel(value="SrpmsResultNewTitle对象", description="横纵向-题录新获项目")
public class SrpmsResultNewTitle extends Model<SrpmsResultNewTitle> {

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

    @ApiModelProperty(value = "项目类别")
    @TableField("PRO_CATEGORY")
    private String proCategory;

    @ApiModelProperty(value = "参与程度")
    @TableField("PRO_ENTER_TYPE")
    private String proEnterType;

    @ApiModelProperty(value = "项目状态")
    @TableField("PRO_STATE")
    private String proState;

    @ApiModelProperty(value = "项目总经费")
    @TableField("PRO_TOTAL_OUTLAY")
    private Double proTotalOutlay;

    @ApiModelProperty(value = "项目到位经费")
    @TableField("PRO_RECEIVE_OUTLAY")
    private Double proReceiveOutlay;

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

    public static final String PRO_CATEGORY = "PRO_CATEGORY";

    public static final String PRO_ENTER_TYPE = "PRO_ENTER_TYPE";

    public static final String PRO_STATE = "PRO_STATE";

    public static final String PRO_TOTAL_OUTLAY = "PRO_TOTAL_OUTLAY";

    public static final String PRO_RECEIVE_OUTLAY = "PRO_RECEIVE_OUTLAY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
