package com.deloitte.services.srpmp.relust.entity;

import java.io.Serializable;
import java.sql.Clob;
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
 * 科研成果
 * </p>
 *
 * @author pengchao
 * @since 2019-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_RESULT")
@ApiModel(value="SrpmsResult对象", description="科研成果")
public class SrpmsResult extends Model<SrpmsResult> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "成果id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "成果入库编号")	
    @TableField("RESULT_NUM")
    private String resultNum;

    @ApiModelProperty(value = "成果名称")
    @TableField("RESULT_NAME")
    private String resultName;

    @ApiModelProperty(value = "成果类型")
    @TableField("RESULT_TYPE")
    private String resultType;

    @ApiModelProperty(value = "是否转化")
    @TableField("TRANS_FLAG")
    private String transFlag;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NUM")
    private String projectNum;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "单位CODE")
    @TableField("DEPT_CODE")
    private String deptCode;

    @ApiModelProperty(value = "所属单位")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "完成人")
    @TableField("PERSON_NAME")
    private String personName;

    @ApiModelProperty(value = "明细")
    @TableField("DETAIL")
    private String detail;

    @ApiModelProperty(value = "状态")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "创建时间")
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

    public static final String RESULT_NUM = "RESULT_NUM";

    public static final String RESULT_NAME = "RESULT_NAME";

    public static final String RESULT_TYPE = "RESULT_TYPE";

    public static final String TRANS_FLAG = "TRANS_FLAG";

    public static final String PROJECT_NUM = "PROJECT_NUM";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String DEPT_CODE = "DEPT_CODE";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String PERSON_NAME = "PERSON_NAME";

    public static final String DETAIL = "DETAIL";

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
