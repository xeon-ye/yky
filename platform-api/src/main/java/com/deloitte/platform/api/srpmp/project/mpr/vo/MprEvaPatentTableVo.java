package com.deloitte.platform.api.srpmp.project.mpr.vo;
import com.alibaba.fastjson.JSONArray;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaPatentTable返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MprEvaPatentTableVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "专利名称")
    private String patentName;

    @ApiModelProperty(value = "申请号/授权号")
    private String applyAuthNum;

    @ApiModelProperty(value = "申请/批准国别")
    private String applyApprovalCountry;

    @ApiModelProperty(value = "专利类别（发明专利、实用新型专利、外观专利）")
    private String patentType;

    @ApiModelProperty(value = "完成人")
    private JSONArray completePerson;

    @ApiModelProperty(value = "完成时间")
    private String completeDate;

    @ApiModelProperty(value = "状态（申请/获批）")
    private String patentStatus;

    @ApiModelProperty(value = "是否应用")
    private String isUse;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}
