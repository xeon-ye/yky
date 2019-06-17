package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : BaseUser返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "身份证")
    private String identityCard;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "护照号")
    private String passportNo;

    @ApiModelProperty(value = "工作单位")
    private String company;

    @ApiModelProperty(value = "岗位职务")
    private String position;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    private String reserve;

    @ApiModelProperty(value = "备用字段2")
    private String version;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

}
