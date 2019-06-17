package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : yangyq
 * @Date : Create in 2019-04-25
 * @Description :  StandardTemplate查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardTemplateQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String templateCode;
    private String templateName;
    private String attributeCode;
    private String attribute;
    private String orgCode;
    private String org;
    private String contractTypeCode;
    private String contractType;
    private String statue;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String isUsed;
    private String spareField1;
    private String spareField2;
    private String spareField3;
    private LocalDateTime spareField4;
    private Long spareField5;
    private String attamentId;
    private String fileName;
    private String fileUrl;
    private String standardRemark;

}
