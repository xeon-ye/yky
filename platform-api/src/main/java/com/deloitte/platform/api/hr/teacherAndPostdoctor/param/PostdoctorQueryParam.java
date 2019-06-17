package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**

 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  Postdoctor查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostdoctorQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userCode;
    private String postdoctorCode;
    private String name;
    private String sex;
    private String nationality;
    private String nation;
    private String maritalStatus;
    private String homeplace;
    private LocalDate birthDate;
    private Long telNumber;
    private String idType;
    private String idCode;
    private String cardType;
    private String recruitType;
    private Integer status;
    private LocalDateTime pullTime;
    private String attachUnit;
    private LocalDateTime schoolPullTime;
    private LocalDateTime committeePullTime;
    private LocalDateTime pushTime;
    private LocalDateTime schoolPushTime;
    private LocalDateTime committeePushTime;
    private String pushUnit;
    private Integer isGraduation;
    private Integer isDelay;
    private LocalDateTime delayTime;
    private String delayArchivesDeposit;
    private LocalDateTime backTime;
    private String backRemark;
    private String backArchivesDeposit;
    private Long receiveTeacherId;
    private String receiveTeacherName;
    private String remark;
    private LocalDateTime contractStartTime;
    private LocalDateTime contractEndTime;
    private String stationBuild;
    private String stationName;
    private String stationSubName;
    private String researchSubject;
    private String nowArchivesDeposit;
    private String isPutArchives;
    private String shirtSize;
    private String registeredAdress;
    private String rulePoliceStation;
    private String currentAddress;
    private String isTransfer;
    private String settledCity;
    private String expertPost;
    private String backIsTransfer;
    private String graduationSchool;
    private String originalUnit;
    private String postalAddress;
    private String graduationUnit;
    private String doctorNationality;
    private LocalDateTime doctorDefenceTime;
    private LocalDateTime doctorGrantTime;
    private String doctorOneSubject;
    private String doctorSubSubject;
    private Integer postdoctorIsTransfer;
    private LocalDateTime postdoctorTransferTime;
    private String postdoctorTransferAddress;
    private String pushRemark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createBy;
    private Long updateBy;
    private String orgCode;
    private String historyOperation;

}
