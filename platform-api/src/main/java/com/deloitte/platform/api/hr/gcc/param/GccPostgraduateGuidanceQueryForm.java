package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccPostgraduateGuidance查询from对象
 * @Modified :
 */
@ApiModel("GccPostgraduateGuidance查询表单")
@Data
public class GccPostgraduateGuidanceQueryForm extends BaseQueryForm<GccPostgraduateGuidanceQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "人员编号")
    private Long userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "博士生毕业人数")
    private Long doctoralGraduatesNum;

    @ApiModelProperty(value = "博士再度人数")
    private Long doctoralCandidates;

    @ApiModelProperty(value = "硕士生毕业人数")
    private Long graduateStudentsNum;

    @ApiModelProperty(value = "硕士生再度人数")
    private Long degreeCandidates;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;
}
