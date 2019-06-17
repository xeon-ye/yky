package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : User返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "员工号")
    private String empNo;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "头衔，职称")
    private String honor;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "创建人ID")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    private String reserve;

    @ApiModelProperty(value = "备用字段2")
    private String version;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人ID")
    private String updateBy;

    @ApiModelProperty(value = "擅长领域")
    private String expertise;

    @ApiModelProperty(value = "学科")
    private String subject;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthDate;

    @ApiModelProperty(value = "职称")
    private String positionTitle;

    @ApiModelProperty(value = "最高学历")
    private String education;

    @ApiModelProperty(value = "从事专业")
    private String major;

    @ApiModelProperty(value = "固定电话")
    private String tel;

    @ApiModelProperty(value = "传真")
    private String fax;

    @ApiModelProperty(value = "证件类型")
    private String idCardType;

    @ApiModelProperty(value = "证件号码")
    private String idCard;

    @ApiModelProperty(value = "学位授予国别")
    private String educationCountry;

    @ApiModelProperty(value = "授予年份")
    private String educationYear;

    @ApiModelProperty(value = "所在单位")
    private String dept;

    @ApiModelProperty(value = "每年工作时间")
    private Integer workPerYear;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    @ApiModelProperty(value = "依托基地名称")
    private String liveBaseName;

    @ApiModelProperty(value = "入选人才计划")
    private String talentPlan;

//    private String[] talentPlanArray;
//
//    public String[] getTalentPlanArray(){
//        if(talentPlan.length()>0){
//            return talentPlan.substring(1,talentPlan.length()-2).split(",");
//        }else{
//            return new String[]{};
//        }
//    }

    @ApiModelProperty(value = "源人员ID")
    private String sourcePersonId;

    @ApiModelProperty(value = "学位")
    private String degree;

    @ApiModelProperty(value = "所在科室")
    private String officeName;

    @ApiModelProperty(value = "承担单位")
    private String projectCommitmentUnit;

    @ApiModelProperty(value = "国别及地区")
    private String country;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "院系")
    private String faculty;

    @ApiModelProperty(value = "用户的副账号列表")
    private List<DeputyAccountVo> deputyAccount;

    @ApiModelProperty(value = "所在单位名称")
    private String deptName;

    @ApiModelProperty(value = "所在单位类型 0：内部单位  1：供应商  2：外部注册单位")
    private String deptGroupType;

    @ApiModelProperty(value = "当前用户副账号")
    private DeputyAccountVo currentDeputyAccount;

    @ApiModelProperty(value = "账务用户信息")
    private List<FsscUserVo> fsscUser;

    @ApiModelProperty(value = "出生地")
    private String birthPlace;

    @ApiModelProperty(value = "政治面貌")
    private String ploSta;

    @ApiModelProperty(value = "管理岗位等级")
    private String managePositionRank;

    @ApiModelProperty(value = "默认副账号ID")
    private Long deputyAccountId;

    @ApiModelProperty(value = "研究成果")
    private String researchResults;

    @ApiModelProperty(value = "是否第一次登陆")
    private String firstLogin;

    // ====================add===============

    @ApiModelProperty(value = "政治面貌名称")
    private String ploStaName;

    @ApiModelProperty(value = "管理岗位等级名称")
    private String managePositionRankName;

    @ApiModelProperty(value = "职位级别")
    private String positionLevel;

    // ====================add===============

    @ApiModelProperty(value = "部门code")
    private String orgCode;
    @ApiModelProperty(value = "部门名称")
    private String orgName;

}
