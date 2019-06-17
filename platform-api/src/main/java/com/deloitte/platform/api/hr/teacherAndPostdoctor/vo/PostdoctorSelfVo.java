package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description : FlowStation返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorSelfVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博士后ID")
    private String id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "出生年月")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @ApiModelProperty(value = "博士后编号")
    private String postdoctorCode;

    @ApiModelProperty(value = "身份类型")
    private String cardType;

    @ApiModelProperty(value = "招收类型")
    private String recruitType;

    @ApiModelProperty(value = "院校办理进站时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate schoolPullTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "博管会进站时间")
    private LocalDate committeePullTime;

    @ApiModelProperty(value = "流动站（一级学科）")
    private String stationName;

    @ApiModelProperty(value = "专业（二级学科）")
    private String stationSubName;

    @ApiModelProperty(value = "进站单位")
    private String attachUnit;

    @ApiModelProperty(value = "合作导师")
    private String receiveTeacherName;

    @ApiModelProperty(value = "毕业学校")
    private String graduationSchool;

    @ApiModelProperty(value = "博士后状态 （1在站，2出站，3退站，4延期）")
    private String status;

    @ApiModelProperty(value = "原工作单位")
    private String originalUnit;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "合同开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractStartTime;

    @ApiModelProperty(value = "合同结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractEndTime;

    @ApiModelProperty(value = "现人事档案存放单位")
    private String nowArchivesDeposit;

    @ApiModelProperty(value = "是否交合同进行备案（1是，2否）")
    private String isPutArchives;

    @ApiModelProperty(value = "博士后文化衫领取（号码）")
    private String shirtSize;

    @ApiModelProperty(value = "博士毕业单位 ")
    private String graduationUnit;

    @ApiModelProperty(value = "获博士学位国别")
    private String doctorNationality;

    @ApiModelProperty(value = "博士学位论文答辩通过时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate doctorDefenceTime;

    @ApiModelProperty(value = "博士学位证书签发时间 ")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate doctorGrantTime;

    @ApiModelProperty(value = "学科（一级学科)")
    private String doctorOneSubject;

    @ApiModelProperty(value = "专业（二级学科） ")
    private String doctorSubSubject;

    @ApiModelProperty(value = "是否延期")
    private String isDelay;

    @ApiModelProperty(value = "延期时间至")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate delayTime;

    @ApiModelProperty(value = "延期档案存处")
    private String delayArchivesDeposit;

    @ApiModelProperty(value = "退站时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate backTime;

    @ApiModelProperty(value = "退站原因")
    private String backRemark;

    @ApiModelProperty(value = "退站档案存处")
    private String backArchivesDeposit;

    @ApiModelProperty(value = "院校办理出站时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate schoolPushTime;

    @ApiModelProperty(value = "博管会出站时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate committeePushTime;

    @ApiModelProperty(value = "出站接收单位")
    private String pushUnit;

    @ApiModelProperty(value = "是否颁发博士后证书（1是，2否）")
    private String isGraduation;

    @ApiModelProperty(value = "户口是否已迁出（1是，2否）")
    private String postdoctorIsTransfer;

    @ApiModelProperty(value = "户口迁出时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate postdoctorTransferTime;

    @ApiModelProperty(value = "迁出地")
    private String postdoctorTransferAddress;

    @ApiModelProperty(value = "备注论文")
    private String pushRemark;

}
