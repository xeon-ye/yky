package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-08
 * @Description : GccGroupUser返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccGroupUserVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "人员信息id")
    private String userId;

    @ApiModelProperty(value = "专家小组编号")
    private String groupId;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private String createBy;

    @ApiModelProperty(value = "修改人id")
    private String updateBy;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "单位名称编码")
    private String company;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "部门名称")
    private String departName;

    @ApiModelProperty(value = "部门编码")
    private String depCode;

    @ApiModelProperty(value = "院校")
    private String academy;

    @ApiModelProperty(value = "专家职称")
    private String expertsTitles;

    @ApiModelProperty(value = "个人电话")
    private String mobilePhone;

    @ApiModelProperty(value = "个人邮箱")
    private String personalEmail;

    @ApiModelProperty(value = "个人银行账号")
    private String bankNumber;

    @ApiModelProperty(value = "开户行")
    private String openBank;

    @ApiModelProperty(value = "银联号")
    private String unionpay;

    @ApiModelProperty(value = "是否有效")
    private String status;

    @ApiModelProperty(value = "员工号")
    private String empCode;
}
