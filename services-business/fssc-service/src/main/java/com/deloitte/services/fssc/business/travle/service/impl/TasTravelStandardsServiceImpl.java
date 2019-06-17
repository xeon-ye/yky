package com.deloitte.services.fssc.business.travle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.travle.param.TasTravelStandardsQueryForm;
import com.deloitte.services.fssc.business.travle.entity.TasTravelStandards;
import com.deloitte.services.fssc.business.travle.mapper.TasTravelStandardsMapper;
import com.deloitte.services.fssc.business.travle.service.ITasTravelStandardsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-04-17
 * @Description :  TasTravelStandards服务实现类
 * @Modified :
 */
@Service
@Transactional
public class TasTravelStandardsServiceImpl extends ServiceImpl<TasTravelStandardsMapper, TasTravelStandards> implements ITasTravelStandardsService {


    @Autowired
    private TasTravelStandardsMapper tasTravelStandardsMapper;

    @Override
    public IPage<TasTravelStandards> selectPage(TasTravelStandardsQueryForm queryForm ) {
        QueryWrapper<TasTravelStandards> queryWrapper = new QueryWrapper <TasTravelStandards>();
        queryWrapper.likeRight(StringUtil.isNotEmpty(queryForm.getNationAdminCode()),"NATION_ADMIN_CODE",queryForm.getNationAdminCode());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPlaceName()),"PLACE_NAME",queryForm.getPlaceName());
        queryWrapper.orderByAsc("NATION_ADMIN_CODE");
        //getQueryWrapper(queryWrapper,queryForm);
        return tasTravelStandardsMapper.selectPage(new Page<TasTravelStandards>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<TasTravelStandards> selectList(TasTravelStandardsQueryForm queryForm) {
        QueryWrapper<TasTravelStandards> queryWrapper = new QueryWrapper <TasTravelStandards>();
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getNationAdminCode()),"NATION_ADMIN_CODE",queryForm.getNationAdminCode());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPlaceName()),"PLACE_NAME",queryForm.getPlaceName());
        if(!"".equals(queryForm.getPeakMonth())){
            queryWrapper.eq(StringUtil.isNotEmpty(queryForm.getPlaceName()),"PEAK_MONTH",queryForm.getPeakMonth());
        }else{
            queryWrapper.isNull("PEAK_MONTH");
        }
        return tasTravelStandardsMapper.selectList(queryWrapper);
    }

    @Override
    public TasTravelStandards selectTravelStandar(String peakMonth, String nationAdminCode, String country, String placeName) {
        QueryWrapper<TasTravelStandards> tasWrapper = new QueryWrapper<>();
        if(StringUtil.isNotEmpty(peakMonth)){
            tasWrapper.eq("PEAK_MONTH",peakMonth).eq("NATION_ADMIN_CODE", nationAdminCode).
                    eq("COUNTRY", country).eq("PLACE_NAME", placeName);
        }else{
            tasWrapper.eq("NATION_ADMIN_CODE", nationAdminCode).
                    eq("COUNTRY", country).eq("PLACE_NAME", placeName);
        }
        return this.getOne(tasWrapper);
    }

    @Override
    public boolean selectCount(String travelBeginTime ,String placeName) {
        QueryWrapper<TasTravelStandards> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("PEAK_MONTH",travelBeginTime).eq("PLACE_NAME", placeName);
        return this.count(queryWrapper) > 0;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<TasTravelStandards> getQueryWrapper(QueryWrapper<TasTravelStandards> queryWrapper,TasTravelStandardsQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getNationAdminCode())){
            queryWrapper.eq(TasTravelStandards.NATION_ADMIN_CODE,queryForm.getNationAdminCode());
        }
        if(StringUtils.isNotBlank(queryForm.getCountry())){
            queryWrapper.eq(TasTravelStandards.COUNTRY,queryForm.getCountry());
        }
        if(StringUtils.isNotBlank(queryForm.getPlaceName())){
            queryWrapper.eq(TasTravelStandards.PLACE_NAME,queryForm.getPlaceName());
        }
        if(StringUtils.isNotBlank(queryForm.getMinisterialLevel())){
            queryWrapper.eq(TasTravelStandards.MINISTERIAL_LEVEL,queryForm.getMinisterialLevel());
        }
        if(StringUtils.isNotBlank(queryForm.getSecretaries())){
            queryWrapper.eq(TasTravelStandards.SECRETARIES,queryForm.getSecretaries());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherPersonnel())){
            queryWrapper.eq(TasTravelStandards.OTHER_PERSONNEL,queryForm.getOtherPersonnel());
        }
        if(StringUtils.isNotBlank(queryForm.getFoodAllowance())){
            queryWrapper.eq(TasTravelStandards.FOOD_ALLOWANCE,queryForm.getFoodAllowance());
        }
        if(StringUtils.isNotBlank(queryForm.getTrafficSubsidy())){
            queryWrapper.eq(TasTravelStandards.TRAFFIC_SUBSIDY,queryForm.getTrafficSubsidy());
        }
        if(StringUtils.isNotBlank(queryForm.getPeakMonth())){
            queryWrapper.eq(TasTravelStandards.PEAK_MONTH,queryForm.getPeakMonth());
        }
        return queryWrapper;
    }
     */
}

