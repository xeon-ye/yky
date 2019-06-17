package com.deloitte.services.fssc.engine.automatic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountLogicHead;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountLogicLine;
import com.deloitte.services.fssc.engine.automatic.mapper.AvAccountLogicHeadMapper;
import com.deloitte.services.fssc.engine.automatic.mapper.AvAccountLogicLineMapper;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountLogicHeadService;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountLogicLineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-27
 * @Description :  AvAccountElementRele服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AvAccountLogicLineServiceImpl  extends ServiceImpl<AvAccountLogicLineMapper, AvAccountLogicLine> implements IAvAccountLogicLineService {
}
