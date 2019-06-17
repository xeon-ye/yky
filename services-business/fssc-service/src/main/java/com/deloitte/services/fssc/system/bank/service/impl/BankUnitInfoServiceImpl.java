package com.deloitte.services.fssc.system.bank.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.bank.param.BankUnitInfoQueryForm;
import com.deloitte.platform.api.fssc.bank.vo.BankUnitInfoVo;
import com.deloitte.services.fssc.eums.FsscRedisKeyEnums;
import com.deloitte.services.fssc.system.bank.entity.BankInfo;
import com.deloitte.services.fssc.system.bank.entity.BankUnitInfo;
import com.deloitte.services.fssc.system.bank.mapper.BankUnitInfoMapper;
import com.deloitte.services.fssc.system.bank.service.IBankInfoService;
import com.deloitte.services.fssc.system.bank.service.IBankUnitInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-25
 * @Description :  BankInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BankUnitInfoServiceImpl extends ServiceImpl<BankUnitInfoMapper, BankUnitInfo> implements IBankUnitInfoService {


    @Autowired
    private BankUnitInfoMapper bankUnitInfoMapper;

    @Autowired
    private IBankInfoService bankInfoService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public IPage<BankUnitInfoVo> selectPage(BankUnitInfoQueryForm queryForm) {
        Page<BankUnitInfoVo> page = new Page(queryForm.getCurrentPage(), queryForm.getPageSize());
        List<BankUnitInfoVo> unitInfoVos =
                bankUnitInfoMapper.selectByPageConditions(page, queryForm);
        page.setRecords(unitInfoVos);
        return page;
    }

    @Override
    public List<BankUnitInfo> selectList(BankUnitInfoQueryForm queryForm) {
        QueryWrapper<BankUnitInfo> queryWrapper = new QueryWrapper<>();
        //getQueryWrapper(queryWrapper,queryForm);
        return bankUnitInfoMapper.selectList(queryWrapper);
    }


    @Override
    public Map<String,List<BankUnitInfoVo>> getBankAccountVoMap() {
        Object object = redisTemplate.opsForValue().get(FsscRedisKeyEnums.FSSC_BANK_ACCOUNT_VO_MAP.getValue());
        if (object != null){
            return (Map<String,List<BankUnitInfoVo>>)object;
        }
        List<BankUnitInfoVo> bankUnitInfoVoList = bankUnitInfoMapper.selectByConditions(new BankUnitInfoQueryForm());
        if (CollectionUtils.isEmpty(bankUnitInfoVoList)){
            return Collections.EMPTY_MAP;
        }
        Map<String,List<BankUnitInfoVo>> bankUnitInfoVoMap = new HashMap<>();
        for (BankUnitInfoVo bankUnitInfoVo : bankUnitInfoVoList){
            List<BankUnitInfoVo> bankUnitInfoVos = bankUnitInfoVoMap.get(bankUnitInfoVo.getBankAccount());
            if (CollectionUtils.isEmpty(bankUnitInfoVos)){
                bankUnitInfoVos = new ArrayList<>();
                bankUnitInfoVos.add(bankUnitInfoVo);
                bankUnitInfoVoMap.put(bankUnitInfoVo.getBankAccount(),bankUnitInfoVos);
            }else{
                bankUnitInfoVos.add(bankUnitInfoVo);
            }
        }
        redisTemplate.opsForValue().set(FsscRedisKeyEnums.FSSC_BANK_ACCOUNT_VO_MAP.getValue(),bankUnitInfoVoMap,1,TimeUnit.HOURS);
        return bankUnitInfoVoMap;
    }

    @Override
    public List<BankUnitInfoVo> selectListByBankAccount(String bankAccount) {
        BankUnitInfoQueryForm queryForm = new BankUnitInfoQueryForm();
        queryForm.setBankAccount(bankAccount);
        return bankUnitInfoMapper.selectByConditions(queryForm);
    }

    @Override
    public List<BankUnitInfo> listByInterLineNum(String interLineNum, String unitId) {
        QueryWrapper<BankUnitInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(unitId) || StringUtils.isNotBlank(interLineNum)) {
            if (StringUtils.isNotBlank(unitId)) {
                queryWrapper.in(BankUnitInfo.UNIT_ID, unitId);
            }
            if (StringUtils.isNotBlank(interLineNum)) {
                List<BankInfo> bankInfoList = bankInfoService.listByInterLineNum(interLineNum);
                if (CollectionUtils.isEmpty(bankInfoList)){
                    return Collections.EMPTY_LIST;
                }
                List<Long> bankIdList = bankInfoList.stream().map(BankInfo::getId).collect(Collectors.toList());
                queryWrapper.in(BankUnitInfo.BANK_ID, bankIdList);
            }
        }else {
            return Collections.EMPTY_LIST;
        }
        return this.list(queryWrapper);
    }

    @Override
    public Integer countByBankNum(String bankNum) {
        QueryWrapper<BankUnitInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BankUnitInfo.BANK_ACCOUNT,bankNum);
        return this.count(queryWrapper);
    }

    @Override
    public BankUnitInfo getBankUnitInfo(String bankNum) {
        QueryWrapper<BankUnitInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BankUnitInfo.BANK_ACCOUNT,bankNum);
        return this.getOne(queryWrapper);
    }

    @Override
    public Map<String, BankUnitInfo> getBankNumBeanMap() {
        Object object = redisTemplate.opsForValue().get(FsscRedisKeyEnums.FSSC_BANK_NUM_BEAN_MAP.getValue());
        if (object != null){
            Map<String, BankUnitInfo> bankUnitInfoMap = (Map<String, BankUnitInfo>)object;
            return bankUnitInfoMap;
        }
        QueryWrapper<BankUnitInfo> queryWrapper = new QueryWrapper<>();
        List<BankUnitInfo> bankUnitInfoList = bankUnitInfoMapper.selectList(queryWrapper);
        Map<String, BankUnitInfo> bankUnitInfoMap = new HashMap<>(bankUnitInfoList.size());
        if (CollectionUtils.isNotEmpty(bankUnitInfoList)){
            for(BankUnitInfo bankUnitInfo : bankUnitInfoList){
                bankUnitInfoMap.put(bankUnitInfo.getBankAccount(),bankUnitInfo);
            }
        }
        redisTemplate.opsForValue().set(FsscRedisKeyEnums.FSSC_BANK_NUM_BEAN_MAP.getValue(),bankUnitInfoMap,24, TimeUnit.HOURS);
        return bankUnitInfoMap;
    }
}

