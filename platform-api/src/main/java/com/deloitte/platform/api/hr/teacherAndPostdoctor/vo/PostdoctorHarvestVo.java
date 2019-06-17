package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : PostdoctorHarvest返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorHarvestVo extends PostdoctorApplyBaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学术报告")
    private List<PostdoctorHAcademicVo> postdoctorHAcademicVoList;

    @ApiModelProperty(value = "著作")
    private List<PostdoctorHBookVo> postdoctorHBookVoList;

    @ApiModelProperty(value = "基金")
    private List<PostdoctorHFundVo> postdoctorHFundVoList;

    @ApiModelProperty(value = "奖励及荣誉")
    private List<PostdoctorHHonorVo> postdoctorHHonorVoList;

    @ApiModelProperty(value = "论文")
    private List<PostdoctorHPaperVo> postdoctorHPaperVoList;

    @ApiModelProperty(value = "专利")
    private List<PostdoctorHPatentVo> postdoctorHPatentVoList;

    @ApiModelProperty(value = "项目课题")
    private List<PostdoctorHProjectVo> postdoctorHProjectVoList;

}
