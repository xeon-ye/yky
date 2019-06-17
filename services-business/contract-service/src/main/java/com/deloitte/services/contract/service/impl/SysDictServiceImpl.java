package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.SysDictQueryForm;
import com.deloitte.platform.api.contract.vo.SysDictVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.entity.SysDict;
import com.deloitte.services.contract.mapper.SysDictMapper;
import com.deloitte.services.contract.service.ISysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SysDict服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {


    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public IPage<SysDict> selectPage(SysDictQueryForm queryForm ) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper <SysDict>();
        //getQueryWrapper(queryWrapper,queryForm);
        return sysDictMapper.selectPage(new Page<SysDict>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SysDict> selectList(SysDictQueryForm queryForm) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper <SysDict>();
        //getQueryWrapper(queryWrapper,queryForm);
        return sysDictMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SysDict> getQueryWrapper(QueryWrapper<SysDict> queryWrapper,SysDictQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getType())){
            queryWrapper.eq(SysDict.TYPE,queryForm.getType());
        }
        if(StringUtils.isNotBlank(queryForm.getCode())){
            queryWrapper.eq(SysDict.CODE,queryForm.getCode());
        }
        if(StringUtils.isNotBlank(queryForm.getValue())){
            queryWrapper.eq(SysDict.VALUE,queryForm.getValue());
        }
        if(StringUtils.isNotBlank(queryForm.getParentCode())){
            queryWrapper.eq(SysDict.PARENT_CODE,queryForm.getParentCode());
        }
        if(StringUtils.isNotBlank(queryForm.getActiveDate())){
            queryWrapper.eq(SysDict.ACTIVE_DATE,queryForm.getActiveDate());
        }
        if(StringUtils.isNotBlank(queryForm.getExpiredDate())){
            queryWrapper.eq(SysDict.EXPIRED_DATE,queryForm.getExpiredDate());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUesd())){
            queryWrapper.eq(SysDict.IS_UESD,queryForm.getIsUesd());
        }
        if(StringUtils.isNotBlank(queryForm.getDescribe())){
            queryWrapper.eq(SysDict.DESCRIBE,queryForm.getDescribe());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(SysDict.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(SysDict.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(SysDict.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(SysDict.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(SysDict.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(SysDict.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(SysDict.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(SysDict.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(SysDict.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */

    public Map<String, SysDictVo> getSysDictVoByTypes(String[] types){
        Map<String, SysDictVo> map = new HashMap<>();
        Map<String, List<SysDictVo>> mapList = new HashMap<>();
        for (String type: types) {
            List<SysDictVo> sysDictList = sysDictMapper.getSysDictVoByType(type);
            mapList.put(type, sysDictList);
        }
        for (String type: types) {
            List<SysDictVo> sysDictList = mapList.get(type);
            SysDictVo sysDict = setSysDictList(null, sysDictList);
            map.put(type, sysDict);
        }
        return map;
    }

    public SysDictVo setSysDictList(SysDictVo sysDict,List<SysDictVo> sysDictList){
        if (sysDict != null) {
            List<SysDictVo> entitys = new ArrayList<>();
            SysDictVo entityPar = null;
            for (int i = 0; i < sysDictList.size(); i++) {
                entityPar = sysDictList.get(i);
                if (sysDict.getId().equals(entityPar.getParentCode())) {
                    sysDictList.remove(entityPar);
                    entityPar = setSysDictList(entityPar, sysDictList);
                    entitys.add(entityPar);
                    i--;
                }
            }
            sysDict.setSysDicts(entitys);
        }else {
            SysDictVo entityPar = null;
            for (int i = 0; i < sysDictList.size(); i++) {
                entityPar = sysDictList.get(i);
                if (entityPar.getParentCode().equals("0")) {
                    sysDictList.remove(entityPar);
                    sysDict = setSysDictList(entityPar, sysDictList);
                    i--;
                }
            }

        }
        return sysDict;
    }

    public Result saveContractType(SysDict sysDict){
        String type = "INCOME_EXPENDITURE_TYPE";
        BigDecimal count = sysDictMapper.getContractTypeCount(type);
        sysDict.setType(type);
        sysDict.setCode("INC-" + count.toString());
        sysDict.setId(null);
        int i = sysDictMapper.insert(sysDict);
        if (i > 0){
            String[] types = type.split(",");
            Map<String, SysDictVo> sysDictPages = getSysDictVoByTypes(types);
            return new Result<Map<String, List<SysDict>>>().sucess(sysDictPages);
        }else{
            return Result.fail();
        }
    }

    public Result updateContractType(SysDict sysDict){
        String type = "INCOME_EXPENDITURE_TYPE";
        SysDict sysDictNew = sysDictMapper.selectById(sysDict.getId());
        sysDictNew.setValue(sysDict.getValue());
//        sysDictNew.setActiveDate(sysDict.getActiveDate());
//        sysDictNew.setExpiredDate(sysDict.getExpiredDate());
//        sysDictNew.setDescribe(sysDict.getDescribe());
        int i = sysDictMapper.updateById(sysDictNew);
        if (i > 0){
            String[] types = type.split(",");
            Map<String, SysDictVo> sysDictPages = getSysDictVoByTypes(types);
            return new Result<Map<String, List<SysDict>>>().sucess(sysDictPages);
        }else{
            return Result.fail();
        }
    }

    /**
     * 删除节点及其子节点全部信息
     * @param sysDict
     * @return
     */
    public Result deleteContractType(SysDict sysDict){
        String type = "INCOME_EXPENDITURE_TYPE";
        int i = sysDictMapper.deleteContractType(sysDict.getId().toString());
        if (i > 0){
            String[] types = type.split(",");
            Map<String, SysDictVo> sysDictPages = getSysDictVoByTypes(types);
            return new Result<Map<String, List<SysDict>>>().sucess(sysDictPages);
        }else{
            return Result.fail();
        }
    }
}

