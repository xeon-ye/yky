package com.deloitte.services.oaservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.oaservice.param.OaAssistantMappingQueryForm;
import com.deloitte.services.oaservice.entity.OaAssistantMapping;
import com.deloitte.services.oaservice.entity.OaMssInfo;
import com.deloitte.services.oaservice.mapper.OaAssistantMappingMapper;
import com.deloitte.services.oaservice.mapper.OaMssInfoMapper;
import com.deloitte.services.oaservice.service.IOaMssInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-03
 * @Description :  OaAssistantMapping服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaMssInfoServiceImpl extends ServiceImpl<OaMssInfoMapper, OaMssInfo> implements IOaMssInfoService {

}

