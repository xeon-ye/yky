package com.deloitte.services.dss.scientific.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.scientific.vo.PersonnelColumnarVo;
import com.deloitte.platform.api.dss.scientific.vo.PersonnelVo;
import com.deloitte.services.dss.scientific.mapper.PersonnelMapper;
import com.deloitte.services.dss.scientific.service.IPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description :  BaseIncomeSubType服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PersonnelServiceImpl extends ServiceImpl implements IPersonnelService {

    @Autowired
    public PersonnelMapper personnelMapper;

    /**
     * 年龄占比
     * @param map
     * @return
     */
    @Override
    public List<PersonnelVo> queryAgeProp(Map map) {
        return personnelMapper.queryAgeProp(map);
    }

    /**
     * 柱状图数据
     * @param map
     * @return
     */
    @Override
    public    List<PersonnelColumnarVo> queryColumnarData(Map map){
        return personnelMapper.queryColumnarData(map);
    }
}

