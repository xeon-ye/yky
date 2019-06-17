package com.deloitte.platform.api.hr.employee_mes.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-08
 * @Description :   EmployeeMesTeach查询from对象
 * @Modified :
 */
@ApiModel("EmployeeMesTeach查询表单")
@Data
public class EmployeeMesTeachQueryForm extends BaseQueryForm<EmployeeMesTeachQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "入学日期")
    private LocalDateTime segment1;

    @ApiModelProperty(value = "毕/肄业日期")
    private LocalDateTime segment2;

    @ApiModelProperty(value = "毕/肄业学校名称")
    private String segment3;

    @ApiModelProperty(value = "毕业专业名称")
    private String segment5;

    @ApiModelProperty(value = "学历")
    private String segment6;

    @ApiModelProperty(value = "学位")
    private String segment8;

    @ApiModelProperty(value = "学制")
    private String segment12;

    @ApiModelProperty(value = "教育形式")
    private String segment11;

    @ApiModelProperty(value = "是否是最高学历")
    private String segment13;

    @ApiModelProperty(value = "是否是第一学历")
    private String segment14;

    @ApiModelProperty(value = "毕业类型")
    private String segment15;

    @ApiModelProperty(value = "是否留学回国人士")
    private String segment16;

    @ApiModelProperty(value = "留学国家")
    private String segment17;

    @ApiModelProperty(value = "留学类型")
    private String overseasStudy;

    @ApiModelProperty(value = "留学出国时间")
    private LocalDateTime segment18;

    @ApiModelProperty(value = "留学回国时间")
    private LocalDateTime segment19;

    @ApiModelProperty(value = "备注")
    private String segment20;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    private String empMesId;

    private String applyState;
}
