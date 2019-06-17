package com.deloitte.services.dss.scientific.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.scientific.vo.*;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatFormException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.dss.scientific.mapper.DeptFundMapper;
import com.deloitte.services.dss.scientific.mapper.HarvestMapper;
import com.deloitte.services.dss.scientific.mapper.ResearchResultMapper;
import com.deloitte.services.dss.scientific.service.IDeptFundService;
import com.deloitte.services.dss.scientific.service.IHarvestService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 科研成果主菜单相关接口
 */
@Service
@Transactional
public class HarvestServiceImpl extends ServiceImpl implements IHarvestService {

    @Autowired
    private HarvestMapper  harvestMapper;

    @Override
    public List<HarvestResultPatentVo> queryDeptPatentResult(Map map) {
        return harvestMapper.queryDeptPatentResult(map);
    }

    @Override
    public List<HarvestPaperVo> queryDeptPaper(Map map) {
        return harvestMapper.queryDeptPaper(map);
    }

    @Override
    public List<HarvestLineVo> queryBrokenLineData(Map map) {
        return harvestMapper.queryBrokenLineData(map);
    }
}
