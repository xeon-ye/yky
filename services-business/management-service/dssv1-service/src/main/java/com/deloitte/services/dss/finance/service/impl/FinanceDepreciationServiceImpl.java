package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.finance.vo.FinanceDepreciationVo;
import com.deloitte.services.dss.finance.mapper.FinanceDepreciationMapper;
import com.deloitte.services.dss.finance.service.IFinanceDepreciationService;
import com.deloitte.services.dss.util.MathUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-12
 * @Description :  FinanceDepreciation服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinanceDepreciationServiceImpl extends ServiceImpl implements IFinanceDepreciationService {


    @Autowired
    private FinanceDepreciationMapper financeDepreciationMapper;

    @Override
    public List<FinanceDepreciationVo> queryDepreciation(FinanceDepreciationVo financeDepreciationVo) {
        List<FinanceDepreciationVo> financeDepreciationVos = financeDepreciationMapper.queryDepreciation(financeDepreciationVo);
        return financeDepreciationVos;
    }

    //查询整体折旧率
    @Override
    public Double queryAvgDepreciation(FinanceDepreciationVo financeDepreciationVo) {
        //查询当年折旧率
        List<FinanceDepreciationVo> financeDepreciationVosNow = financeDepreciationMapper.queryDepreciation(financeDepreciationVo);
        //计算整体折旧率
        Integer size = financeDepreciationVosNow.size();
        Double allDepreciation = null;
        for(int i = 0 ; i < size ; i++){
            allDepreciation += financeDepreciationVosNow.get(i).getDepreciation();
        }
        double temp = size;
        Double result = MathUtils.div(allDepreciation, temp);
        return result;
    }

    @Override
    public Double queryRate(FinanceDepreciationVo financeDepreciationVo) {
        Double depreciationNow = queryAvgDepreciation(financeDepreciationVo);
        financeDepreciationVo.setYear(financeDepreciationVo.getYear() - 1);
        Double depreciationBefore = queryAvgDepreciation(financeDepreciationVo);
        Double rate = MathUtils.div(depreciationNow - depreciationBefore, depreciationBefore);
        return rate;
    }
}

