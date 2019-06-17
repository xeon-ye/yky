package com.deloitte.platform.api.hr.ebs.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description : EmployeeMesPunish返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMesPunishVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "头像地址")
    private String headPhoto;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "员工自助ID")
    private String empMesId;

    @ApiModelProperty(value = "流程申请ID")
    private String mesTid;

    @ApiModelProperty(value = "处分类别")
    private String punishCategory;

    @ApiModelProperty(value = "处分名称")
    private String punishName;

    @ApiModelProperty(value = "受处分时间")
    private LocalDateTime punishDate;

    @ApiModelProperty(value = "受处分给予单位")
    private String punishUnit;

    @ApiModelProperty(value = "撤销处分时间")
    private LocalDateTime unpunishDate;

    @ApiModelProperty(value = "是否监察机关直接给予")
    private String supervisory;

    @ApiModelProperty(value = "申诉时间")
    private LocalDateTime appealDate;

    @ApiModelProperty(value = "申诉内容")
    private String appealContent;

    @ApiModelProperty(value = "申诉处理结果")
    private String appealResult;

    @ApiModelProperty(value = "申诉受理单位")
    private String appealUnit;

}
