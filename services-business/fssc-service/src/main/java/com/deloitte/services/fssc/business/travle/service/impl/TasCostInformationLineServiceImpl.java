package com.deloitte.services.fssc.business.travle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.travle.param.TasCostInformationLineQueryForm;
import com.deloitte.services.fssc.business.travle.entity.TasCostInformationLine;
import com.deloitte.services.fssc.business.travle.mapper.TasCostInformationLineMapper;
import com.deloitte.services.fssc.business.travle.service.ITasCostInformationLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description :  TasCostInformationLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class TasCostInformationLineServiceImpl extends ServiceImpl<TasCostInformationLineMapper, TasCostInformationLine> implements ITasCostInformationLineService {


    @Autowired
    private TasCostInformationLineMapper tasCostInformationLineMapper;

    @Override
    public IPage<TasCostInformationLine> selectPage(TasCostInformationLineQueryForm queryForm ) {
        QueryWrapper<TasCostInformationLine> queryWrapper = new QueryWrapper <TasCostInformationLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return tasCostInformationLineMapper.selectPage(new Page<TasCostInformationLine>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<TasCostInformationLine> selectList(TasCostInformationLineQueryForm queryForm) {
        QueryWrapper<TasCostInformationLine> queryWrapper = new QueryWrapper <TasCostInformationLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return tasCostInformationLineMapper.selectList(queryWrapper);
    }

    public boolean existsByExpenseSubTypeClIds(List<Long> ExpenseSubTypeList) {
        return tasCostInformationLineMapper.existsByExpenseSubTypeIds(ExpenseSubTypeList) > 0 ? true : false;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<TasCostInformationLine> getQueryWrapper(QueryWrapper<TasCostInformationLine> queryWrapper,TasCostInformationLineQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(TasCostInformationLine.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(TasCostInformationLine.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(TasCostInformationLine.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(TasCostInformationLine.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(TasCostInformationLine.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(TasCostInformationLine.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(TasCostInformationLine.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(TasCostInformationLine.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(TasCostInformationLine.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(TasCostInformationLine.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getExt6())){
            queryWrapper.eq(TasCostInformationLine.EXT6,queryForm.getExt6());
        }
        if(StringUtils.isNotBlank(queryForm.getExt7())){
            queryWrapper.eq(TasCostInformationLine.EXT7,queryForm.getExt7());
        }
        if(StringUtils.isNotBlank(queryForm.getExt8())){
            queryWrapper.eq(TasCostInformationLine.EXT8,queryForm.getExt8());
        }
        if(StringUtils.isNotBlank(queryForm.getExt9())){
            queryWrapper.eq(TasCostInformationLine.EXT9,queryForm.getExt9());
        }
        if(StringUtils.isNotBlank(queryForm.getExt10())){
            queryWrapper.eq(TasCostInformationLine.EXT10,queryForm.getExt10());
        }
        if(StringUtils.isNotBlank(queryForm.getExt11())){
            queryWrapper.eq(TasCostInformationLine.EXT11,queryForm.getExt11());
        }
        if(StringUtils.isNotBlank(queryForm.getExt12())){
            queryWrapper.eq(TasCostInformationLine.EXT12,queryForm.getExt12());
        }
        if(StringUtils.isNotBlank(queryForm.getExt13())){
            queryWrapper.eq(TasCostInformationLine.EXT13,queryForm.getExt13());
        }
        if(StringUtils.isNotBlank(queryForm.getExt14())){
            queryWrapper.eq(TasCostInformationLine.EXT14,queryForm.getExt14());
        }
        if(StringUtils.isNotBlank(queryForm.getExt15())){
            queryWrapper.eq(TasCostInformationLine.EXT15,queryForm.getExt15());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(TasCostInformationLine.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getName())){
            queryWrapper.eq(TasCostInformationLine.NAME,queryForm.getName());
        }
        if(StringUtils.isNotBlank(queryForm.getJobNumber())){
            queryWrapper.eq(TasCostInformationLine.JOB_NUMBER,queryForm.getJobNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getLevelName())){
            queryWrapper.eq(TasCostInformationLine.LEVEL_NAME,queryForm.getLevelName());
        }
        if(StringUtils.isNotBlank(queryForm.getTravelBeginTime())){
            queryWrapper.eq(TasCostInformationLine.TRAVEL_BEGIN_TIME,queryForm.getTravelBeginTime());
        }
        if(StringUtils.isNotBlank(queryForm.getTravelEndTime())){
            queryWrapper.eq(TasCostInformationLine.TRAVEL_END_TIME,queryForm.getTravelEndTime());
        }
        if(StringUtils.isNotBlank(queryForm.getLocationBegTravel())){
            queryWrapper.eq(TasCostInformationLine.LOCATION_BEG_TRAVEL,queryForm.getLocationBegTravel());
        }
        if(StringUtils.isNotBlank(queryForm.getLocationEndTravel())){
            queryWrapper.eq(TasCostInformationLine.LOCATION_END_TRAVEL,queryForm.getLocationEndTravel());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalForehead())){
            queryWrapper.eq(TasCostInformationLine.TOTAL_FOREHEAD,queryForm.getTotalForehead());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(TasCostInformationLine.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getDayNum())){
            queryWrapper.eq(TasCostInformationLine.DAY_NUM,queryForm.getDayNum());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitPrice())){
            queryWrapper.eq(TasCostInformationLine.UNIT_PRICE,queryForm.getUnitPrice());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalSum())){
            queryWrapper.eq(TasCostInformationLine.TOTAL_SUM,queryForm.getTotalSum());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(TasCostInformationLine.LINE_NUMBER,queryForm.getLineNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(TasCostInformationLine.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(TasCostInformationLine.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getConnectCostType())){
            queryWrapper.eq(TasCostInformationLine.CONNECT_COST_TYPE,queryForm.getConnectCostType());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(TasCostInformationLine.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(TasCostInformationLine.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

