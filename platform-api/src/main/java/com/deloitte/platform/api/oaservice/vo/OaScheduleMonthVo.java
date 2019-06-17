package com.deloitte.platform.api.oaservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-03-26
 * @Description : OaScheduleTable返回的VO对象
 * @Modified :
 */
@NoArgsConstructor
@AllArgsConstructor
public class OaScheduleMonthVo extends OaScheduleTableVo {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "日程月历title显示")
    private String title;
    @ApiModelProperty(value = "日程月历开始时间")
    private LocalDateTime start;
    @ApiModelProperty(value = "日程月历结束时间")
    private LocalDateTime end;
    @ApiModelProperty(value = "显示样式")
    private String className;

    public String getTitle() {
        if("个人日程".equals(super.getWorkType())){
            return super.getWorkName();
        }
        return super.getWorkDesc();
    }

    public LocalDateTime getStart() {
        return super.getStartTime();
    }

    public LocalDateTime getEnd() {
        return super.getEndTime();
    }

    public void setClassName(String className){
        this.className = className;
    }

    public String getClassName() {
        if("S".equals(super.getWorkStatus())){
            className = "draft";
        }else if("O".equals(super.getWorkStatus())){
            className = "process";
        }else if("A".equals(super.getWorkStatus())){
            className = "complete";
        }else if("C".equals(super.getWorkStatus())){
            className = "change";
        }else{
            className = "other";
        }
        return className + " " + super.getId();
    }
}
