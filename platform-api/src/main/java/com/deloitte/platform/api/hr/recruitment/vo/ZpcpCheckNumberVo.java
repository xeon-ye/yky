package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-19
 * @Description : ZpcpCheckNumber返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZpcpCheckNumberVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "通知的id")
    private Long noticeId;

    @ApiModelProperty(value = "审核小组名称")
    private Long checkGroupId;

    @ApiModelProperty(value = "应到人数")
    private Long shouldNumber;

    @ApiModelProperty(value = "实际到人数")
    private String trueNumber;

    @ApiModelProperty(value = "${field.comment}")
    private String organizationCode;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String createBy;

    @ApiModelProperty(value = "审核类型(1.聘任工作组,2,学术委员会,3.教授聘任委员会,4教职聘任委员会)")
    private String checkType;

}
