package com.deloitte.services.srpmp.project.budget.dto;

import lombok.Data;

/**
 * Created by lixin on 29/03/2019.
 * 设备预算统计DTO
 */
@Data
public class DeviceBudgetStatisDTO {

    private Integer countReagentMore = 0;
    private Integer countDeviceMore = 0;
    private Integer countReagentLess = 0;
    private Integer countDeviceLess = 0;

    private Double priceReagentMore = 0D;
    private Double priceDeviceMore = 0D;
    private Double priceReagentLess = 0D;
    private Double priceDeviceLess = 0D;

    private Integer countAmount = 0;
    private Double priceAmount = 0D;
}
