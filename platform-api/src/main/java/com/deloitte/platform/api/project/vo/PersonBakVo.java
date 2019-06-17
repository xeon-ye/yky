package com.deloitte.platform.api.project.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description : PersonBak返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonBakVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "成员管理id")
    private String manId;

    @ApiModelProperty(value = "评审id")
    private String reviewId;

    @ApiModelProperty(value = "批复id")
    private String replyId;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    private String applicationId;

    @ApiModelProperty(value = "人员id")
    private String perId;

    @ApiModelProperty(value = "人员名称")
    private String perName;

    @ApiModelProperty(value = "职责code")
    private String perPositionCode;

    @ApiModelProperty(value = "职责")
    private String perPositionName;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "终止时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "拓展字段1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段5")
    private String ext5;

    @ApiModelProperty(value = "维度id")
    private Long orgId;

    @ApiModelProperty(value = "维度")
    private String orgPath;

    @ApiModelProperty(value = "联系地址")
    private String adress;

    @ApiModelProperty(value = "人员信息CODE")
    private String personCode;

    @ApiModelProperty(value = "人员信息名称")
    private String personName;

}
