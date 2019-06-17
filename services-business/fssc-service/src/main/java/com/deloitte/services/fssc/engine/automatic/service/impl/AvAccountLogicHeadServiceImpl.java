package com.deloitte.services.fssc.engine.automatic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvAccountElementReleQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElementRele;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountLogicHead;
import com.deloitte.services.fssc.engine.automatic.mapper.AvAccountElementReleMapper;
import com.deloitte.services.fssc.engine.automatic.mapper.AvAccountLogicHeadMapper;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountElementReleService;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountLogicHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : chenx
 * @Date : Create in 2019-03-27
 * @Description :  AvAccountElementRele服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AvAccountLogicHeadServiceImpl extends ServiceImpl<AvAccountLogicHeadMapper, AvAccountLogicHead> implements IAvAccountLogicHeadService {

}
