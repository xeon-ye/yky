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
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : Postdoctor返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "（进站信息）博士后编号")
    private String postdoctorCode;

    @ApiModelProperty(value = "（进站信息）员工编号")
    private String userCode;

    @ApiModelProperty(value = " （进站信息）姓名")
    private String name;

    @ApiModelProperty(value = "（进站信息）性别")
    private String sex;

    @ApiModelProperty(value = "（进站信息）国籍")
    private String nationality;

    @ApiModelProperty(value = "（进站信息）民族")
    private String nation;

    @ApiModelProperty(value = "（进站信息）婚姻状况")
    private String maritalStatus;

    @ApiModelProperty(value = "（进站信息）出生地")
    private String homeplace;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "（进站信息）出生日期")
    private LocalDate birthDate;

    @ApiModelProperty(value = "（进站信息）联系电话")
    private Long telNumber;

    @ApiModelProperty(value = "（进站信息）证件类型")
    private String idType;

    @ApiModelProperty(value = "（进站信息）证件号码")
    private String idCode;

    @ApiModelProperty(value = "（进站信息）身份类型（1统招统分，2在职人员，3博新计划进站，4其他）")
    private String cardType;

    @ApiModelProperty(value = "（进站信息）招收类型（1流动站自主招收，2与工作站联合培养）")
    private String recruitType;

    @ApiModelProperty(value = "（进站信息）博士后状态 （1在站，2出站，3退站，4延期）")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "（进站信息）进站时间")
    private LocalDate pullTime;

    @ApiModelProperty(value = "（进站信息）进站单位code")
    private String attachUnit;

    @ApiModelProperty(value = "博士后类别（1科研博士后，2临床博士后）")
    private String category;

    @ApiModelProperty(value = "进站单位")
    private String attachUnitName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "（进站信息）院校办理进站时间")
    private LocalDate schoolPullTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "（进站信息）博管会进站时间")
    private LocalDate committeePullTime;

    @ApiModelProperty(value = "（进站信息）现人事档案存放单位")
    private String nowArchivesDeposit;

    @ApiModelProperty(value = "（进站信息）是否交合同进行备案（1是，2否）")
    private String isPutArchives;

    @ApiModelProperty(value = "（进站信息）博士后文化衫领取（号码）")
    private String shirtSize;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "（进站信息）合同开始时间")
    private LocalDate contractStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "（进站信息）合同结束时间")
    private LocalDate contractEndTime;

    @ApiModelProperty(value = "（进站信息）流动站设站单位（非设站单位）")
    private String stationBuild;

    @ApiModelProperty(value = "（进站信息）流动站（一级学科）")
    private String stationName;

    @ApiModelProperty(value = "（进站信息）专业（二级学科）")
    private String stationSubName;

    @ApiModelProperty(value = "（进站信息）研究计划题目")
    private String researchSubject;

    @ApiModelProperty(value = "（进站信息）户口簿首页地址")
    private String registeredAdress;

    @ApiModelProperty(value = "（进站信息）所属派出所")
    private String rulePoliceStation;

    @ApiModelProperty(value = "（进站信息）现居住地详细地址或单位")
    private String currentAddress;

    @ApiModelProperty(value = "（进站信息）合作导师ID")
    private Long receiveTeacherId;

    @ApiModelProperty(value = "（进站信息）合作导师")
    private String receiveTeacherName;

    @ApiModelProperty(value = "（进站信息）在职单位")
    private String originalUnit;

    @ApiModelProperty(value = "（进站信息）通信地址（含邮编）")
    private String postalAddress;

    @ApiModelProperty(value = "（进站信息）博士毕业单位 ")
    private String graduationUnit;

    @ApiModelProperty(value = "（进站信息）获博士学位国别")
    private String doctorNationality;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "（进站信息）博士学位论文答辩通过时间")
    private LocalDate doctorDefenceTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "（进站信息）博士学位证书签发时间 ")
    private LocalDate doctorGrantTime;

    @ApiModelProperty(value = "（进站信息）学科（一级学科)")
    private String doctorOneSubject;

    @ApiModelProperty(value = "（进站信息）专业（二级学科） ")
    private String doctorSubSubject;

    @ApiModelProperty(value = "（进站信息）户口迁否（1是，2否）")
    private String isTransfer;

    @ApiModelProperty(value = "（进站信息）拟落户省市 ")
    private String settledCity;

    @ApiModelProperty(value = "（进站信息）专业技术职务")
    private String expertPost;

    @ApiModelProperty(value = "（进站信息）留学博士出国前是否注销户口（1是，2否）")
    private String backIsTransfer;

    @ApiModelProperty(value = "（进站信息）备注")
    private String remark;

    @ApiModelProperty(value = "流动站编号")
    private String stationCode;


//    @ApiModelProperty(value = "（出站信息）出站时间")
//    private LocalDateTime pushTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "（出站信息）院校办理出站时间")
    private LocalDate schoolPushTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "（出站信息）博管会出站时间")
    private LocalDate committeePushTime;

    @ApiModelProperty(value = "（出站信息）出站接收单位")
    private String pushUnit;

    @ApiModelProperty(value = "（出站信息）是否颁发博士后证书（1是，2否）")
    private Integer isGraduation;

    @ApiModelProperty(value = "（出站信息）户口是否已迁出（1是，2否）")
    private Integer postdoctorIsTransfer;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "（出站信息）户口迁出时间")
    private LocalDate postdoctorTransferTime;

    @ApiModelProperty(value = "（出站信息）迁出地")
    private String postdoctorTransferAddress;

    @ApiModelProperty(value = "（出站信息）备注论文")
    private String pushRemark;


    @ApiModelProperty(value = "（延期信息）是否延期（1是，0否）")
    private Integer isDelay;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "（延期信息）延期时间至")
    private LocalDate delayTime;

    @ApiModelProperty(value = "（延期信息）延期档案存处")
    private String delayArchivesDeposit;

    @ApiModelProperty(value = "延期时长（月）")
    private String delayDuration;

    @ApiModelProperty(value = "延期备注")
    private String delayRemark;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "（退站信息）退站时间")
    private LocalDate backTime;

    @ApiModelProperty(value = "（退站信息）退站原因")
    private String backRemark;

    @ApiModelProperty(value = "（退站信息）退站档案存处")
    private String backArchivesDeposit;

//    @ApiModelProperty(value = "毕业学校")
//    private String graduationSchool;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "合同附件URL")
    private String attachmentContractUrl;

    @ApiModelProperty(value = "合同附件名称")
    private String contractFileName;

    @ApiModelProperty(value = "延期附件名称")
    private String attachmentDelayUrl;

    @ApiModelProperty(value = "延期附件名称")
    private String delayFileName;

    @ApiModelProperty(value = "出站附件URL")
    private String attachmentPushUrl;

    @ApiModelProperty(value = "出站附件名称")
    private String pushFileName;

    @ApiModelProperty(value = "退站附件URL")
    private String attachmentBackUrl;

    @ApiModelProperty(value = "退站附件名称")
    private String backFileName;


}
