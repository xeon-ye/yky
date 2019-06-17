package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccExternalHelp查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccExternalHelpQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private Long userName;
    private String aidType;
    private String sex;
    private String year;
    private String receiverUnit;
    private String recProvinces;
    private String graTeacher;
    private String projectName;
    private String projectBatch;
    private String projectCategory;
    private String card;
    private LocalDateTime birthday;
    private Integer age;
    private String nation;
    private String politics;
    private String education;
    private String degree;
    private String organization;
    private String majorSecond;
    private String professional;
    private String adminPost;
    private String majorSpecific;
    private String majorUnit;
    private String majorTeacher;
    private String technicalLeader;
    private String mobile;
    private String remarks;
    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;

}
