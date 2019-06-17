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
 * @Description : MprEvaTreaTable返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MprEvaTreaTableVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "论文题目")
    private String treaTopic;

    @ApiModelProperty(value = "期刊名称")
    private String journalName;

    @ApiModelProperty(value = "姓名")
    private String author;

    @ApiModelProperty(value = "作者类别")
    private String authorType;

    @ApiModelProperty(value = "年")
    private String year;

    @ApiModelProperty(value = "卷（期）")
    private String volume;

    @ApiModelProperty(value = "页")
    private String page;

    @ApiModelProperty(value = "收录来源")
    private String sourceInclusion;

    @ApiModelProperty(value = "SCI分区")
    private String sciPartition;

    @ApiModelProperty(value = "发表时间")
    private String issuTime;

    @ApiModelProperty(value = "期刊影响因子")
    private String impactFactor;

    @ApiModelProperty(value = "引用频次")
    private String refeFreq;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}
