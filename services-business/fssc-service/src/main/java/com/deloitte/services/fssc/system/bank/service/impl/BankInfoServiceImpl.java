package com.deloitte.services.fssc.system.bank.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.bank.param.BankInfoQueryForm;
import com.deloitte.services.fssc.eums.FsscRedisKeyEnums;
import com.deloitte.services.fssc.system.bank.entity.BankInfo;
import com.deloitte.services.fssc.system.bank.mapper.BankInfoMapper;
import com.deloitte.services.fssc.system.bank.service.IBankInfoService;
import com.deloitte.services.fssc.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-26
 * @Description :  BankInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BankInfoServiceImpl extends ServiceImpl<BankInfoMapper, BankInfo> implements IBankInfoService {


    @Autowired
    private BankInfoMapper bankInfoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public IPage<BankInfo> selectPage(BankInfoQueryForm queryForm ) {
        QueryWrapper<BankInfo> queryWrapper = new QueryWrapper <BankInfo>();
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getBankCode()),BankInfo.BANK_CODE,queryForm.getBankCode());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getBankName()),BankInfo.BANK_NAME,queryForm.getBankName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getBankSimpleName()),BankInfo.BANK_SIMPLE_NAME,queryForm.getBankSimpleName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getBranchBankName()),BankInfo.BRANCH_BANK_NAME,queryForm.getBranchBankName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getInterBranchNumber()),BankInfo.INTER_BRANCH_NUMBER,queryForm.getInterBranchNumber());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getIsValid()),BankInfo.IS_VALID,queryForm.getIsValid());
        queryWrapper.orderByDesc(BankInfo.UPDATE_TIME);
        return bankInfoMapper.selectPage(new Page<BankInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BankInfo> selectList(BankInfoQueryForm queryForm) {
        QueryWrapper<BankInfo> queryWrapper = new QueryWrapper <>();
        //getQueryWrapper(queryWrapper,queryForm);
        return bankInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List<BankInfo> listByInterLineNum(String interLineNum) {
        QueryWrapper<BankInfo> queryWrapper = new QueryWrapper <>();
        queryWrapper.in(BankInfo.INTER_BRANCH_NUMBER, interLineNum);
        return this.list(queryWrapper);
    }

    @Override
    public Map<Long, BankInfo> getBankIdBeanMap() {
        Object object = redisTemplate.opsForValue().get(FsscRedisKeyEnums.FSSC_BANK_ID_BEAN_MAP.getValue());
        if (object != null){
            return (Map<Long, BankInfo>)object;
        }
        QueryWrapper<BankInfo> queryWrapper = new QueryWrapper <>();
        List<BankInfo> bankInfoList = bankInfoMapper.selectList(queryWrapper);
        Map<Long, BankInfo> bankInfoMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(bankInfoList)){
            for(BankInfo bankInfo : bankInfoList){
                bankInfoMap.put(bankInfo.getId(),bankInfo);
            }
        }
        redisTemplate.opsForValue().set(FsscRedisKeyEnums.FSSC_BANK_ID_BEAN_MAP.getValue(),bankInfoMap,1, TimeUnit.HOURS);
        return bankInfoMap;
    }
}

