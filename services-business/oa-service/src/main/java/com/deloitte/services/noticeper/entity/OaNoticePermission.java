package com.deloitte.services.noticeper.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * 公告规章制度权限
 * </p>
 *
 * @author jianghaixun
 * @since 2019-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_NOTICE_PERMISSION")
@ApiModel(value="OaNoticePermission对象", description="公告规章制度权限")
public class OaNoticePermission extends Model<OaNoticePermission> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限ID")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "业务类型")
    @TableField("TYPE")
    private String type;

    @TableField("OBJECT_ID")
    private Long objectId;

    @ApiModelProperty(value = "部门CODE")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "单位CODE")
    @TableField("DEPT_CODE")
    private String deptCode;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    public static final String ID = "ID";

    public static final String TYPE = "TYPE";

    public static final String OBJECT_ID = "OBJECT_ID";

    public static final String ORG_CODE = "ORG_CODE";

    public static final String DEPT_CODE = "DEPT_CODE";

    public static final String CREATE_TIME = "CREATE_TIME";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
