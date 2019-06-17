package com.deloitte.platform.api.project.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-06-06
 * @Description : ExePerformance新增修改form对象
 * @Modified :
 */
@ApiModel("新增ExePerformance表单")
@Data
public class ExePerformanceForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "批复id")
    private String replyId;

    @ApiModelProperty(value = "项目执行id")
    private String executionId;

    @ApiModelProperty(value = "一级指标")
    private String indicators1;

    @ApiModelProperty(value = "二级指标")
    private String indicators2;

    @ApiModelProperty(value = "三级指标")
    private String indicators3;

    @ApiModelProperty(value = "年度指标值")
    private String indicatorsYear;

    @ApiModelProperty(value = "1至7月执行情况")
    private String exeCondition1and7;

    @ApiModelProperty(value = "全年执行情况")
    private String exeConditionYear;

    @ApiModelProperty(value = "经费保障")
    private String fundingSec;

    @ApiModelProperty(value = "制度保障")
    private String systemSec;

    @ApiModelProperty(value = "人员保障")
    private String personSec;

    @ApiModelProperty(value = "硬件保障")
    private String hardwareSec;

    @ApiModelProperty(value = "其他保障")
    private String otherSec;

    @ApiModelProperty(value = "原因说明")
    private String reasonInstruction;

    @ApiModelProperty(value = "完成可能性code")
    private Long targetPlanCode;

    @ApiModelProperty(value = "完成可能行name")
    private String targetPlanName;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    private String ext3;

    @ApiModelProperty(value = "拓展4")
    private String et4;

    @ApiModelProperty(value = "orgid")
    private Long orgId;

}
