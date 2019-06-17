package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.services.contract.entity.SerialNumber;
import com.deloitte.services.contract.mapper.SerialNumberMapper;
import com.deloitte.services.contract.service.ISerialNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SerialNumberServiceImpl implements ISerialNumberService {

    @Autowired
    private SerialNumberMapper serialNumberMapper;

    public Map<String, Object> getSerialNumber(){
        Map<String, Object> map = new HashMap<>();
        Calendar date = Calendar.getInstance();
        String dateStr = String.valueOf(date.get(Calendar.YEAR));
        int number = 0;
        List<SerialNumber> list = serialNumberMapper.getSerialNumber(dateStr);
        if (list != null && list.size() > 0){
            SerialNumber serialNumber = list.get(0);
            number = Integer.parseInt(serialNumber.getSerialNumber());
            serialNumber.setSerialNumber(String.valueOf(number + 1));
            serialNumberMapper.updateById(serialNumber);
        }else {
            serialNumberMapper.setSerialNumber(dateStr);
            number = 1;
        }
        String serialNumber = dateStr + String.format("%06d", number);
        map.put("serialNumber", serialNumber);
        return map;
    }

    /**
     * 获取标准文本流水号
     * @param type 传入2为获取标准文本流水号
     * @return
     */
    public String getStandardNumber(String type){
        Calendar date = Calendar.getInstance();
        String dateStr = String.valueOf(date.get(Calendar.YEAR));
        int number = 0;
        SerialNumber serialNumber = new SerialNumber();
        serialNumber.setYear(dateStr);
        serialNumber.setType(type);
        QueryWrapper<SerialNumber> queryWrapper = new QueryWrapper <>();
        queryWrapper.eq(SerialNumber.YEAR,serialNumber.getYear());
        queryWrapper.eq(SerialNumber.TYPE,serialNumber.getType());
        List<SerialNumber> list = serialNumberMapper.selectList(queryWrapper);
        if (list != null && list.size() > 0){
            SerialNumber serialNumber1 = list.get(0);
            number = Integer.parseInt(serialNumber1.getSerialNumber());
            serialNumber1.setSerialNumber(String.valueOf(number + 1));
            serialNumberMapper.updateById(serialNumber1);
        }else {
            serialNumber.setSerialNumber("2");
            serialNumberMapper.insert(serialNumber);
            number = 1;
        }
        return "BZWB" + dateStr + String.format("%03d", number);
    }
}
