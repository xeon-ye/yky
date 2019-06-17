package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :   CheckAchUser查询from对象
 * @Modified :
 */
@ApiModel("CheckAchUser查询表单")
@Data
public class CheckAchUserQueryForm extends BaseQueryForm<CheckAchUserQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "考核关系id（冗余便于在测评的时候被关联）")
    private String checkRelationId;

    @ApiModelProperty(value = "业绩通知id")
    private String checkAchNotifyId;

    @ApiModelProperty(value = "教职工id")
    private String userId;

    @ApiModelProperty(value = "填写时间")
    private LocalDateTime writeTime;

    @ApiModelProperty(value = "评估状态")
    private String evaluateStatus;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
