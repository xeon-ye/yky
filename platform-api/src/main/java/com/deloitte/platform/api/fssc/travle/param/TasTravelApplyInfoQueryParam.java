package com.deloitte.platform.api.fssc.travle.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description :  TasTravleApplyInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasTravelApplyInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long createBy;
    private String createUserName;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long version;
    private String documentNum;
    private String documentStatus;
    private Long unitId;
    private Long deptId;
    private Long projectId;
    private Long projectUnitId;
    private String projectUnitCode;
    private String projectUnitName;
    private Long mainTypeId;
    private Double totalSum;
    private String isAgreedPromis;
    private String remark;
    private String unitName;
    private String deptName;
    private String mainTypeName;
    private String projectName;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String ext6;
    private String ext7;
    private String ext8;
    private String ext9;
    private String ext10;
    private String ext11;
    private String ext12;
    private String ext13;
    private String ext14;
    private String ext15;
    private String unitCode;
    private String deptCode;
    private String projectCode;
    private String mainTypeCode;
    private String currencyCode;
    private String whetherLeaveBj;
    private String whetherBorrow;
    private String whetherReception;
    private Long orgId;
    private String orgPath;
    private Long travelPeople;
    private String isBorrowConnect;
    private String isReimburConnect;
    private Long supportFileNum;

}
