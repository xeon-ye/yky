package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : TeacherCertVo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertVo extends BaseVo {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "教师编号")
    private String teacherCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "所属单位")
    private String attachUnit;

    @ApiModelProperty(value = "证书名称")
    private String certName;

    @ApiModelProperty(value = "证书编号")
    private String certCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "生效日期")
    private LocalDate effectiveDate;

    @ApiModelProperty(value = "有效期（月）")
    private String validTerm;

    @ApiModelProperty(value = "证书状态（1有效，2无效）")
    private Integer certStatus;

    @ApiModelProperty(value = "颁发机构")
    private String awardCertOrg;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否上传证书标识")
    private String bz;

    @ApiModelProperty(value = "教师资格附件URL")
    private String attachmentTeacherUrl;
}
