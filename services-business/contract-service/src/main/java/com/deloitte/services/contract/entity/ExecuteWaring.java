package com.deloitte.services.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 合同履行提醒配置表
 * </p>
 *
 * @author yangyq
 * @since 2019-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_EXECUTE_WARING")
@ApiModel(value="ExecuteWaring对象", description="合同履行提醒配置表")
public class ExecuteWaring extends Model<ExecuteWaring> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "公司编码")
    @TableField("DEPT_CODE")
    private String deptCode;

    @ApiModelProperty(value = "公司名称")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "部门编码")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "公司名称")
    @TableField("ORG_NAME")
    private String orgName;

    @ApiModelProperty(value = "履行提醒时间")
    @TableField("WARING_TIME")
    private Double waringTime;

    @ApiModelProperty(value = "提醒频率")
    @TableField("WARING_FREQUENCY")
    private Double waringFrequency;

    @ApiModelProperty(value = "说明")
    @TableField("REMARK")
    private String remark;

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


    public static final String ID = "ID";

    public static final String DEPT_CODE = "DEPT_CODE";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String ORG_CODE = "ORG_CODE";

    public static final String ORG_NAME = "ORG_NAME";

    public static final String WARING_TIME = "WARING_TIME";

    public static final String WARING_FREQUENCY = "WARING_FREQUENCY";

    public static final String REMARK = "REMARK";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
