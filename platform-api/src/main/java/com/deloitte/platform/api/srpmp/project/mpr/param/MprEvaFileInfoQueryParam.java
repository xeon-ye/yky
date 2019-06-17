package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : LIJUN
 * @Date : Create in 2019-04-10
 * @Description :  MprEvaFileInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MprEvaFileInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectNo;
    private String annexTwo;
    private String annexThree;
    private String annexFour;
    private String annexFive;
    private String annexSeven;
    private String annexEight;
    private String annexTen;
    private String annexEleven;
    private String annexTwelve;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String annexOne;
    private String annexSix;
    private String annexOther;

}
