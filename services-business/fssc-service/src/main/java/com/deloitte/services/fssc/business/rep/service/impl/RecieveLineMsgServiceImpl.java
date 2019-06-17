package com.deloitte.services.fssc.business.rep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.ppc.vo.IncomeClaimedVo;
import com.deloitte.platform.api.fssc.rep.param.RecieveLineMsgQueryForm;
import com.deloitte.services.fssc.business.rep.entity.RecieveLineMsg;
import com.deloitte.services.fssc.business.rep.mapper.RecieveLineMsgMapper;
import com.deloitte.services.fssc.business.rep.service.IRecieveLineMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description :  RecieveLineMsg服务实现类
 * @Modified :
 */
@Service
@Transactional
public class RecieveLineMsgServiceImpl extends ServiceImpl<RecieveLineMsgMapper, RecieveLineMsg> implements IRecieveLineMsgService {


    @Autowired
    private RecieveLineMsgMapper recieveLineMsgMapper;

    @Override
    public IPage<RecieveLineMsg> selectPage(RecieveLineMsgQueryForm queryForm ) {
        QueryWrapper<RecieveLineMsg> queryWrapper = new QueryWrapper <RecieveLineMsg>();
        //getQueryWrapper(queryWrapper,queryForm);
        return recieveLineMsgMapper.selectPage(new Page<RecieveLineMsg>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<RecieveLineMsg> selectList(RecieveLineMsgQueryForm queryForm) {
        QueryWrapper<RecieveLineMsg> queryWrapper = new QueryWrapper <RecieveLineMsg>();
        //getQueryWrapper(queryWrapper,queryForm);
        return recieveLineMsgMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<IncomeClaimedVo> conditionsRecie(RecieveLineMsgQueryForm recievePaymentQueryForm) {
        IPage<IncomeClaimedVo> page=new Page<>(recievePaymentQueryForm.getCurrentPage(),recievePaymentQueryForm.getPageSize());
        List<IncomeClaimedVo> list=recieveLineMsgMapper.conditionsRecie(page,recievePaymentQueryForm);
        page.setRecords(list);
        return page;
    }

    @Override
    public boolean existsByReceivePayment(List<Long> incomeSubTypeIds) {
        QueryWrapper<RecieveLineMsg> countWrapper = new QueryWrapper();
        countWrapper.in(RecieveLineMsg.IN_COME_SUB_TYPE_ID,incomeSubTypeIds);
        return this.count(countWrapper) > 0;
    }
}

