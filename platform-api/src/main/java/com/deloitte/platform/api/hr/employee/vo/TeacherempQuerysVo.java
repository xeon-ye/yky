package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-05-20
 * @Description : TeacherempQuerys返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherempQuerysVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "售房单位")
    private String houseSelling;

    @ApiModelProperty(value = "售房地址")
    private String houseSellingAddres;

    @ApiModelProperty(value = "优惠号")
    private String discountNumber;

    @ApiModelProperty(value = "开证时间")
    private String openingTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "${field.comment}")
    private String createTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateTime;

    @ApiModelProperty(value = "单位")
    private String company;

    private String idcard;
}
