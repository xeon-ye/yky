package com.deloitte.services.fssc.engine.automatic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountLogicLine;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.automatic.mapper.AvAccountLogicLineMapper;
import com.deloitte.services.fssc.engine.automatic.mapper.AvBaseElementMapper;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountLogicLineService;
import com.deloitte.services.fssc.engine.automatic.service.IAvBaseElementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AvBaseElementServiceImpl  extends ServiceImpl<AvBaseElementMapper, AvBaseElement> implements IAvBaseElementService {
}
