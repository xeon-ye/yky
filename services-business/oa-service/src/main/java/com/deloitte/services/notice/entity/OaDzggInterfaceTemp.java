package com.deloitte.services.notice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通知公告类流程对接新门户接口表
 * </p>
 *
 * @author jianghaixun
 * @since 2019-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_DZGG_INTERFACE_TEMP")
@ApiModel(value="OaDzggInterfaceTemp对象", description="通知公告类流程对接新门户接口表")
public class OaDzggInterfaceTemp extends Model<OaDzggInterfaceTemp> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "临时表id，主键")
    //@TableId(value = "流程实例ID", type = IdType.ID_WORKER)
    @TableField("REQUEST_ID")
    private String requestId;

    @ApiModelProperty(value = "通知公告编号，规则：DZGG+年月+3位流水")
    @TableField("NOTICE_NO")
    private String noticeNo;

    @ApiModelProperty(value = "申请日期")
    @TableField("APPLY_DATE")
    private LocalDateTime applyDate;

    @ApiModelProperty(value = "类型，可选：通知公告、规章制度")
    @TableField("TYPE_NAME")
    private String typeName;

    @ApiModelProperty(value = "分类，当“通知公告”时可选择：行政通知、人事任免、会议通知、规章制度通知、其他通知公告；当“规章制度”时可选择：办公制度、财务制度、人事制度、科技管理制度、其他制度")
    @TableField("SORT_NAME")
    private String sortName;

    @ApiModelProperty(value = "读取状态，默认0，表示未读；新门户读取成功后，网站系统将其更新为1")
    @TableField("IS_READ")
    private Integer isRead;

    @ApiModelProperty(value = "通知公告/规则制度标题")
    @TableField("NOTICE_TITLE")
    private String noticeTitle;

    @ApiModelProperty(value = "通知公告/规则制度正文")
    @TableField("NOTICE_CONTENT")
    private String noticeContent;

    @ApiModelProperty(value = "通知来源")
    @TableField("NOTICE_SRC")
    private String noticeSrc;

    @ApiModelProperty(value = "申请部门code")
    @TableField("APPLY_ORG_CODE")
    private String applyOrgCode;

    @ApiModelProperty(value = "申请部门名称")
    @TableField("APPLY_ORG_NAME")
    private String applyOrgName;

    @ApiModelProperty(value = "申请员工名称")
    @TableField("APPLY_USER_NAME")
    private String applyUserName;

    @ApiModelProperty(value = "申请员工编号")
    @TableField("APPLY_USER_EMPNO")
    private String applyUserEmpno;

    public static final String REQUEST_ID = "REQUEST_ID";

    public static final String NOTICE_NO = "NOTICE_NO";

    public static final String APPLY_DATE = "APPLY_DATE";

    public static final String TYPE_NAME = "TYPE_NAME";

    public static final String SORT_NAME = "SORT_NAME";

    public static final String IS_READ = "IS_READ";

    public static final String NOTICE_TITLE = "NOTICE_TITLE";

    public static final String NOTICE_CONTENT = "NOTICE_CONTENT";

    public static final String NOTICE_SRC = "NOTICE_SRC";

    public static final String APPLY_ORG_CODE = "APPLY_ORG_CODE";

    public static final String APPLY_ORG_NAME = "APPLY_ORG_NAME";

    public static final String APPLY_USER_NAME = "APPLY_USER_NAME";

    public static final String APPLY_USER_EMPNO = "APPLY_USER_EMPNO";

    @Override
    protected Serializable pkVal() {
        return this.requestId;
    }

}
