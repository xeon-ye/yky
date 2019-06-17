package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : PostdoctorProject返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorProjectVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类型（1人才项目，2基金项目）")
    private Integer projectType;

    @ApiModelProperty(value = "类别（1香江学者计划招收，2“中德”计划招收，3国际交流派出计划招收，4创新人才支持计划招收，5社会科学基金，6自然科学基金）")
    private Integer type;

    @ApiModelProperty(value = "性质（1一等，2二等，3站前，4站后）")
    private Integer nature;

    @ApiModelProperty(value = "资助时长（年）")
    private Integer duration;

    @ApiModelProperty(value = "资助额度(万元)")
    private BigDecimal limitFunds;

    @ApiModelProperty(value = "已拨付金额(万元)")
    private BigDecimal payLimitMoney;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否有效（1有效，0无效）")
    private Integer isValid;

    @ApiModelProperty(value = "项目性质基金分类（1自然科学基金，2社会科学基金）")
    private Integer fundType;

    @ApiModelProperty(value = "")
    private Integer[] queryType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;


}
