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
 * @Date : Create in 2019-05-26
 * @Description : ProUser返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProUserVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "人员id")
    private String userId;

    @ApiModelProperty(value = "名称")
    private String userName;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "员工工号")
    private String userNo;

    @ApiModelProperty(value = "所在部门")
    private String userDep;

    @ApiModelProperty(value = "所处科室")
    private String userKs;

    @ApiModelProperty(value = "职位code")
    private String userPostCode;

    @ApiModelProperty(value = "职位名称")
    private String userPostName;

    @ApiModelProperty(value = "人员标志code")
    private String userMarkCode;

    @ApiModelProperty(value = "人员标志name")
    private String userMarkName;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "固定电话")
    private String tel;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "专业code")
    private String majorCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    private String ext3;

    @ApiModelProperty(value = "拓展4")
    private String ext4;

    @ApiModelProperty(value = "拓展5")
    private String ext5;

}
