package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.deloitte.platform.api.hr.common.util.DateFarmatUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : 修改Postdoctor进站表单对象
 * @Modified :
 */
@ApiModel("修改Postdoctor进站表单")
@Data
public class PostdoctorForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "员工编号")
    private String userCode;

    @ApiModelProperty(value = "博士后编号")
    private String postdoctorCode;

    @ApiModelProperty(value = "身份类型（1统招统分，2在职人员，3博新计划进站，4其他）")
    private String cardType;

    @ApiModelProperty(value = "进站单位")
    private String attachUnit;

    @ApiModelProperty(value = "博士后状态 （1在站，2出站，3退站，4延期）")
    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "院校办理进站时间（格式:2019-04-01）")
    private LocalDate schoolPullTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "博管会进站时间（格式:2019-04-01）")
    private LocalDate committeePullTime;

    @ApiModelProperty(value = "现人事档案存放单位")
    private String nowArchivesDeposit;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "合同开始时间（格式:2019-04-01）")
    private LocalDate contractStartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "合同结束时间（格式:2019-04-01）")
    private LocalDate contractEndTime;

    @ApiModelProperty(value = "是否交合同进行备案（1是，2否）")
    private String isPutArchives;

    @ApiModelProperty(value = "博士后文化衫领取（号码）")
    private String shirtSize;

    @ApiModelProperty(value = "流动站设站单位（非设站单位）")
    private String stationBuild;

    @ApiModelProperty(value = "流动站（一级学科）")
    private String stationName;

    @ApiModelProperty(value = "专业（二级学科）")
    private String stationSubName;

    @ApiModelProperty(value = "研究计划题目")
    private String researchSubject;

    @ApiModelProperty(value = "招收类型（1流动站自主招收，2与工作站联合培养）")
    private String recruitType;

    @ApiModelProperty(value = "合作导师code")
    private String receiveTeacherId;

    @ApiModelProperty(value = "合作导师")
    private String receiveTeacherName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "进站时间（格式:2019-04-01）")
    private LocalDate pullTime;

    @ApiModelProperty(value = " 姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "国籍")
    private String nationality;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "婚姻状况")
    private String maritalStatus;

    @ApiModelProperty(value = "出生地")
    private String homeplace;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期（格式:2019-04-01）")
    private LocalDate birthDate;

    @ApiModelProperty(value = "证件类型")
    private String idType;

    @ApiModelProperty(value = "证件号码")
    private String idCode;

    @ApiModelProperty(value = "博士后类别（1科研博士后，2临床博士后）")
    private String category;

    @ApiModelProperty(value = "户口簿首页地址")
    private String registeredAdress;

    @ApiModelProperty(value = "所属派出所")
    private String rulePoliceStation;

    @ApiModelProperty(value = "现居住地详细地址或单位")
    private String currentAddress;

    @ApiModelProperty(value = "户口迁否（1是，2否）")
    private String isTransfer;

    @ApiModelProperty(value = "拟落户省市 ")
    private String settledCity;

    @ApiModelProperty(value = "专业技术职务")
    private String expertPost;

    @ApiModelProperty(value = "留学博士出国前是否注销户口（1是，2否）")
    private String backIsTransfer;

//    @ApiModelProperty(value = "毕业学校")
//    private String graduationSchool;

    @ApiModelProperty(value = "在职单位")
    private String originalUnit;

    @ApiModelProperty(value = "联系电话")
    private Long telNumber;

    @ApiModelProperty(value = "通信地址（含邮编）")
    private String postalAddress;

    @ApiModelProperty(value = "博士毕业单位 ")
    private String graduationUnit;

    @ApiModelProperty(value = "获博士学位国别")
    private String doctorNationality;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "博士学位论文答辩通过时间（格式:2019-04-01）")
    private LocalDate doctorDefenceTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "博士学位证书签发时间（格式:2019-04-01） ")
    private LocalDate doctorGrantTime;

    @ApiModelProperty(value = "学科（一级学科)")
    private String doctorOneSubject;

    @ApiModelProperty(value = "专业（二级学科） ")
    private String doctorSubSubject;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "合同附件URL")
    private String attachmentContractUrl;

    @ApiModelProperty(value = "合同附件名称")
    private String contractFileName;

}
