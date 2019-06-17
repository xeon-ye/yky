package com.deloitte.platform.api.hr.ebs.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : JETVAE
 * @Date : Create in 2019-06-04
 * @Description :   EmployeeArchives查询from对象
 * @Modified :
 */
@ApiModel("EmployeeArchives查询表单")
@Data
public class EmployeeArchivesQueryForm extends BaseQueryForm<EmployeeArchivesQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "人员信息CODE")
    private String userCode;

    @ApiModelProperty(value = "档案编号")
    private String fileNo;

    @ApiModelProperty(value = "转入日期")
    private LocalDateTime traDate;

    @ApiModelProperty(value = "原单位")
    private String oriUnit;

    @ApiModelProperty(value = "原单位经办人")
    private String oriUnitMan;

    @ApiModelProperty(value = "转入接收人")
    private String traRec;

    @ApiModelProperty(value = "整理时间")
    private LocalDateTime finTime;

    @ApiModelProperty(value = "整理人")
    private String finMan;

    @ApiModelProperty(value = "转出日期")
    private LocalDateTime traOutDate;

    @ApiModelProperty(value = "接收单位")
    private String recUnit;

    @ApiModelProperty(value = "转出经办人")
    private String oriOutMan;

    @ApiModelProperty(value = "转出接收人")
    private String oriOutRec;

    @ApiModelProperty(value = "现存放地点")
    private String stoLoc;

    @ApiModelProperty(value = "备注")
    private String remark;
}
