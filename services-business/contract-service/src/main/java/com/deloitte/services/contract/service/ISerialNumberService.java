package com.deloitte.services.contract.service;

import com.deloitte.services.contract.entity.SerialNumber;

import java.util.Map;

public interface ISerialNumberService{

    /**
     * 获取流水号
     * @return
     */
    Map<String, Object> getSerialNumber();

    /**
     * 获取标准文本流水号
     * @param type 传入2为获取标准文本流水号
     * @return
     */
    String getStandardNumber(String type);
}
