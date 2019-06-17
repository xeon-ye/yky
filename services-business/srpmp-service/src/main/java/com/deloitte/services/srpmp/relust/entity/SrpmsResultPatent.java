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
 * 成果管理-专利信息表
 * </p>
 *
 * @author Apeng
 * @since 2019-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_RESULT_PATENT")
@ApiModel(value="SrpmsResultPatent对象", description="成果管理-专利信息表")
public class SrpmsResultPatent extends Model<SrpmsResultPatent> {

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

    @ApiModelProperty(value = "申请专利号")
    @TableField("APPLICATION_NUM")
    private String applicationNum;

    @ApiModelProperty(value = "授权专利号")
    @TableField("PATENT_NUM")
    private String patentNum;

    @ApiModelProperty(value = "专利名称")
    @TableField("PATENT_NAME")
    private String patentName;

    @ApiModelProperty(value = "专利类别")
    @TableField("PATENT_CAT")
    private String patentCat;

    @ApiModelProperty(value = "专利是否授权")
    @TableField("AUTHORIZED_FLAG")
    private String authorizedFlag;

    @ApiModelProperty(value = "申请人")
    @TableField("PROPOSER")
    private String proposer;

    @ApiModelProperty(value = "申请时间")
    @TableField("APPLICATION_TIME")
    private LocalDateTime applicationTime;

    @ApiModelProperty(value = "授权时间")
    @TableField("AUTHORIZED_TIME")
    private LocalDateTime authorizedTime;

    @ApiModelProperty(value = "区域 10-全国 20-省内")
    @TableField("AUTHORIZED_REGION")
    private String authorizedRegion;

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

    public static final String APPLICATION_NUM = "APPLICATION_NUM";

    public static final String PATENT_NUM = "PATENT_NUM";

    public static final String PATENT_NAME = "PATENT_NAME";

    public static final String PATENT_CAT = "PATENT_CAT";

    public static final String AUTHORIZED_FLAG = "AUTHORIZED_FLAG";

    public static final String PROPOSER = "PROPOSER";

    public static final String APPLICATION_TIME = "APPLICATION_TIME";

    public static final String AUTHORIZED_TIME = "AUTHORIZED_TIME";

    public static final String AUTHORIZED_REGION = "AUTHORIZED_REGION";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
