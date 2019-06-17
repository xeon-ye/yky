package com.deloitte.platform.api.oaservice.notice.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-16
 * @Description : OaDzggInterfaceTemp新增修改form对象
 * @Modified :
 */
@ApiModel("新增OaDzggInterfaceTemp表单")
@Data
public class OaDzggInterfaceTempForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "临时表id，主键")
    private String requestId;

    @ApiModelProperty(value = "通知公告编号，规则：DZGG+年月+3位流水")
    private String noticeNo;

    @ApiModelProperty(value = "申请日期")
    private LocalDateTime applyDate;

    @ApiModelProperty(value = "类型，可选：通知公告、规章制度")
    private String typeName;

    @ApiModelProperty(value = "分类，当“通知公告”时可选择：行政通知、人事任免、会议通知、规章制度通知、其他通知公告；当“规章制度”时可选择：办公制度、财务制度、人事制度、科技管理制度、其他制度")
    private String sortName;

    @ApiModelProperty(value = "读取状态，默认0，表示未读；新门户读取成功后，网站系统将其更新为1")
    private Integer isRead;

    @ApiModelProperty(value = "通知公告/规则制度标题")
    private String noticeTitle;

    @ApiModelProperty(value = "通知公告/规则制度正文")
    private String noticeContent;

    @ApiModelProperty(value = "通知来源")
    private String noticeSrc;

    @ApiModelProperty(value = "申请部门Code")
    private String applyOrgCode;

    @ApiModelProperty(value = "申请部门名称")
    private String applyOrgName;

    @ApiModelProperty(value = "申请员工编号")
    private String applyUserEmpno;

    @ApiModelProperty(value = "申请人员名称")
    private String applyUserName;

}
