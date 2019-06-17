package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-26
 * @Description : ZpcpAuditRecord返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZpcpAuditRecordVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "申报表id")
    private Long declareId;

    @ApiModelProperty(value = "2.聘任工作组审核未通过,3.聘任工作组审核通过,4学术委员会审核未通过,5.学术委员会审核通过,6.教授委员会审核未通过,7.教授委员会审核通过,8.教职聘任委员会审核未通过,9.教职聘任委员会审核通过")
    private String checkStatus;

    @ApiModelProperty(value = "审核意见")
    private String auditOpinion;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private String createBy;

    @ApiModelProperty(value = "更新人id")
    private String updateBy;

    @ApiModelProperty(value = "组织机构")
    private String organizationCode;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
