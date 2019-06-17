package com.deloitte.platform.api.fssc.unit.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description :  UnitInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long unitCode;
    private String unitName;
    private String unitType;
    private String unitArea;
    private String concatUserName;
    private String concatWay;
    private String createUserName;
    private String isValid;
    private String auditStatus;
    private String taxRegistNum;
    private String recieveMoneyType;
    private String address;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Long createBy;
    private Long updateBy;
    private String commonCreditCode;
}
