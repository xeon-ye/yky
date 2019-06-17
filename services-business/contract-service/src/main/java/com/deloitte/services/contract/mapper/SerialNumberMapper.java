package com.deloitte.services.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.contract.entity.AttamentInfo;
import com.deloitte.services.contract.entity.SerialNumber;

import java.util.List;

public interface SerialNumberMapper extends BaseMapper<SerialNumber>{

    List<SerialNumber> getSerialNumber(String dateStr);

    boolean setSerialNumber(String dateStr);
}
