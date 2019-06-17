package com.deloitte.platform.api.srpmp.relust.vo;
import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeVO;
import com.deloitte.platform.common.core.entity.vo.BaseVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description : SrpmsResult返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsResultVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "成果id")
    private String id;

    @ApiModelProperty(value = "成果入库编号")
    private String resultNum;

    @ApiModelProperty(value = "成果名称")
    private String resultName;
	
    @ApiModelProperty(value = "成果类型")
    private String resultType;

    @ApiModelProperty(value = "成果类型名称")
    private String resultTypeName;

    @ApiModelProperty(value = "是否转化")
    private String transFlag;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "所属单位")
    private String deptName;

    @ApiModelProperty(value = "完成人")
    private String personName;

    @ApiModelProperty(value = "明细")
    private JSONObject detail;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "审批历史")
    private List<TaskNodeVO> approveHistory;

    @ApiModelProperty(value = "附件")
    private List<SrpmsProjectAttachmentVo> attachmentFile;

}
