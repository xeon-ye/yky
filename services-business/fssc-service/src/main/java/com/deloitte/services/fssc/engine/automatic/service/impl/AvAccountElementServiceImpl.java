package com.deloitte.services.fssc.engine.automatic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvAccountElementQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.*;
import com.deloitte.services.fssc.engine.automatic.mapper.AvAccountBaseReleMapper;
import com.deloitte.services.fssc.engine.automatic.mapper.AvAccountElementMapper;
import com.deloitte.services.fssc.engine.automatic.mapper.AvAccountElementReleMapper;
import com.deloitte.services.fssc.engine.automatic.mapper.AvBaseElementMapper;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountElementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description :  AvAccountElement服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class AvAccountElementServiceImpl extends ServiceImpl<AvAccountElementMapper, AvAccountElement> implements IAvAccountElementService {

    @Autowired
    private AvAccountElementMapper avAccountElementMapper;
    @Autowired
    private AvBaseElementMapper avBaseElemntMapper;
    @Autowired
    private AvAccountElementReleMapper avAccountElementReleMapper;
    @Autowired
    private AvAccountBaseReleMapper avAccountBaseReleMapper;

    @Override
    public IPage<AvAccountElement> selectPage(AvAccountElementQueryForm queryForm ) {
        QueryWrapper<AvAccountElement> queryWrapper = new QueryWrapper <AvAccountElement>();
        getQueryWrapper(queryWrapper,queryForm);
        return avAccountElementMapper.selectPage(new Page<AvAccountElement>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<AvAccountElement> selectList(AvAccountElementQueryForm queryForm) {
        QueryWrapper<AvAccountElement> queryWrapper = new QueryWrapper <AvAccountElement>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avAccountElementMapper.selectList(queryWrapper);
    }

    /**
     *
     * @param queryWrapper
     * @param queryForm
     * @return
     */
    public QueryWrapper<AvAccountElement> getQueryWrapper(QueryWrapper<AvAccountElement> queryWrapper,AvAccountElementQueryForm queryForm){
        //条件拼接
        if(queryForm.getChartOfAccountsId()!=null){
            queryWrapper.eq(AvAccountElement.CHART_OF_ACCOUNTS_ID,queryForm.getChartOfAccountsId());
        }
        if(StringUtils.isNotBlank(queryForm.getSegmentCode())){
            queryWrapper.eq(AvAccountElement.SEGMENT_CODE,queryForm.getSegmentCode());
        }
        if(StringUtils.isNotBlank(queryForm.getSegmentDesc())){
            queryWrapper.eq(AvAccountElement.SEGMENT_DESC,queryForm.getSegmentDesc());
        }
        if(queryForm.getSegmentNum()!=null){
            queryWrapper.eq(AvAccountElement.SEGMENT_NUM,queryForm.getSegmentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getHasValue())){
            queryWrapper.eq(AvAccountElement.HAS_VALUE,queryForm.getHasValue());
        }
        if(StringUtils.isNotBlank(queryForm.getStatus())){
            queryWrapper.eq(AvAccountElement.STATUS,queryForm.getStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getType())){
            queryWrapper.eq(AvAccountElement.TYPE,queryForm.getType());
        }
        if(StringUtils.isNotBlank(queryForm.getDataFrom())){
            queryWrapper.eq(AvAccountElement.DATA_FROM,queryForm.getDataFrom());
        }
        return queryWrapper;
    }

    /**
     * 核算要素列表和生成会计凭证的头表关联查询是否该要素被使用了
     * @param element_id
     * @return
     */
    public List<AvAccountElement> whetherEnabledById(Long element_id){
        List<AvAccountElement> list = new ArrayList<AvAccountElement>();
        try{
             list = avAccountElementMapper.whetherEnabledById(element_id);
        }catch (Exception e){
           log.info("查询是否被使用报错！");
        }
        return  list;
    }

    public  List<AvBaseElement> baseValueListByElementId(AvAccountElementQueryForm queryForm){
        List<AvBaseElement> avBaseElementlist = new ArrayList<AvBaseElement>();
        try {
            avBaseElementlist = avBaseElemntMapper.baseValueListByElementId(queryForm.getPageSize()*queryForm.getCurrentPage(),(queryForm.getCurrentPage()-1)*queryForm.getPageSize(),queryForm.getId());
        }catch (Exception e){
            log.error("查询核算要素值报错："+e);
        }
        return avBaseElementlist;
    }
    public  Integer baseValueCountByElementId(AvAccountElementQueryForm queryForm){
        return  avBaseElemntMapper.baseValueCountByElementId(queryForm.getId());
    }

    public  boolean saveSourceFromObtain(AvAccountElement entity){
        try{
            String sourseFroms = entity.getDataFrom();
            log.info(sourseFroms);
            //解析数据来源的json串{"table":"AV_BASE_ELEMENT","field":,DATA_CODE"conditions":"DATA_TYPE='H'"}
            JSONObject jsonObject = new JSONObject(sourseFroms);
            String table = jsonObject.getString("table");
            String conditions = jsonObject.getString("conditions");
            List<AvBaseElement> baseList =  avAccountElementMapper.sourceFromObtain(table,conditions);
            for(int i=0;i<baseList.size();i++){
                AvAccountBaseRele releEntity = new AvAccountBaseRele();
                releEntity.setElementId(entity.getId());
                releEntity.setBaseId(baseList.get(i).getId());
                releEntity.setCreateBy(entity.getUpdateBy());
                releEntity.setCreateDate(entity.getUpdateDate());
                QueryWrapper<AvAccountBaseRele> queryWrapper = new QueryWrapper <AvAccountBaseRele>();
                queryWrapper.eq(AvAccountBaseRele.ELEMENT_ID,entity.getId());
                queryWrapper.eq(AvAccountBaseRele.BASE_ID,baseList.get(i).getId());
                //判断关系库里面是否存在该关系
                List<AvAccountBaseRele> baseOldlist =  avAccountBaseReleMapper.selectList(queryWrapper);
                if(baseOldlist.size()>0){
                    continue;
                }else{
                    avAccountBaseReleMapper.insert(releEntity);
                }
            }
        }catch (Exception e){
            log.error("解析出错"+e);
        }

        return false;
    }


    public  List<AvChartOfAccount> elementReleationLedgerList (AvAccountElementQueryForm form){
        form.setStart((form.getCurrentPage()-1)*form.getPageSize()+1);
        form.setEnd(form.getCurrentPage()*form.getPageSize());
        List<AvChartOfAccount> list = avAccountElementMapper.elementReleationLedgerList(form);
        return list;
    }
    public Integer elementReleationLedgerListCount(AvAccountElementQueryForm form){
        return  avAccountElementMapper.elementReleationLedgerListCount(form);
    }


    public  List<AvAccountElement> queryPageListByParam(AvAccountElementQueryForm form){
        form.setStart((form.getCurrentPage()-1)*form.getPageSize()+1);
        form.setEnd(form.getCurrentPage()*form.getPageSize());
        return  avAccountElementMapper.queryPageListByParam(form);
    }
    public int queryPageListByParamCount(AvAccountElementQueryForm form){
        return  avAccountElementMapper.queryPageListByParamCount(form);
    }
}

