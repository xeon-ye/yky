package com.deloitte.services.fssc.engine.dockingEbs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvAccountElementQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvBaseElementVo;
import com.deloitte.platform.api.fssc.engine.manual.vo.AvManualVoucherHeadForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountBaseRele;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.automatic.mapper.AvAccountBaseReleMapper;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountElementService;
import com.deloitte.services.fssc.engine.automatic.service.IAvBaseElementService;
import com.deloitte.services.fssc.engine.dockingEbs.entity.AvBaseFromEbs;
import com.deloitte.services.fssc.engine.dockingEbs.returnEntity.BaseResultReturnEbs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description :   EBS推送数据到财务系统
 * @Modified :
 */
@Api(tags = "EBS端提供COA值，币种，业务类别到财务系统中")
@Slf4j
@RestController
@RequestMapping("/fssc/dockingEbs/currency")
public class CurrencyResourceFromEbsController {
    @Autowired
    private IAvBaseElementService avBaseElementService;
    @Autowired
    private AvAccountBaseReleMapper avAccountBaseReleMapper;
    @Autowired
    private IAvAccountElementService avAccountElementService;
    @ApiOperation(value = "获取得到EBS基础的数据包含COA值，币种，业务类别", notes = "EBS获取得到")
    @PostMapping(value = "/getListFromEbs")
    public Result searchBaseElement(@Valid @RequestBody @ApiParam(name="jsonData",value="获取得到EBS数据",required=true) JSONArray jsonData) {
          //  List<AvBaseFromEbs>  list= JSON.parseArray(jsonData, AvBaseFromEbs.class);
            List<AvBaseFromEbs> list = (ArrayList<AvBaseFromEbs>) JSONArray.parseArray(jsonData.toString(),AvBaseFromEbs.class);
            List<AvBaseElement> aBEList = new ArrayList<AvBaseElement>();
            for(AvBaseFromEbs p:list){
                AvBaseElement entity = new AvBaseElement();
                entity.setDataCode(p.getDataCode());
                entity.setDataDesc(p.getDataDesc());
                entity.setDataStatus(p.getDataStatus());
                entity.setDataType(p.getDataType());
                entity.setEtx1(p.getAttribute1());
                entity.setEtx2(p.getAttribute2());
                entity.setEtx3(p.getAttribute3());
                entity.setSummaryFlag(p.getSummaryFlag());
                aBEList.add(entity);
            }
            List<BaseResultReturnEbs> resultList = new ArrayList<BaseResultReturnEbs>();
            for(AvBaseElement p : aBEList){
                try{
                    QueryWrapper<AvBaseElement> queryWrapper = new QueryWrapper <AvBaseElement>();
                    queryWrapper.eq(AvBaseElement.DATA_CODE,p.getDataCode());
                    queryWrapper.eq(AvBaseElement.DATA_TYPE,p.getDataType());
                    List<AvBaseElement>  oldlist  = avBaseElementService.list(queryWrapper);
                    if(oldlist.size()>0){
                        p.setId(oldlist.get(0).getId());
                        avBaseElementService.updateById(p);
                    }else {

                        p.setCreateDate(LocalDateTime.now());
                        avBaseElementService.save(p);

                        QueryWrapper<AvAccountElement> queryWrapperEle = new QueryWrapper <AvAccountElement>();
                        queryWrapperEle.eq(AvAccountElement.SEGMENT_TYPE,p.getDataType());
                        AvAccountElement eleEntity = avAccountElementService.getOne(queryWrapperEle);
                        if(eleEntity!=null){//{"table":"AV_BASE_ELEMENT","field":"","conditions":"DATA_TYPE='JE_CATEGORY'"}
                            AvAccountBaseRele releEntity = new AvAccountBaseRele();
                            releEntity.setElementId(eleEntity.getId());
                            releEntity.setBaseId(p.getId());
                            avAccountBaseReleMapper.insert(releEntity);
                            JSONObject obj = new JSONObject();
                            obj.put("table","AV_BASE_ELEMENT");
                            obj.put("field","");
                            obj.put("conditions","DATA_TYPE='"+p.getDataType()+"'");
                            eleEntity.setDataFrom(obj.toJSONString());
                            avAccountElementService.saveOrUpdate(eleEntity);
                        }
                    }


                }catch (Exception e){
                    log.error("EBS传输的数据错误"+e);
                    BaseResultReturnEbs entity = new BaseResultReturnEbs();
                    entity.setDataCode(p.getDataCode());
                    entity.setDataType(p.getDataType());
                    entity.setErrorMessage("财务系统保存失败");
                    entity.setImportFlag("E");
                    resultList.add(entity);
                    continue;
                }
            }
            if(resultList.size()>0){
               return  Result.fail(resultList);
            }
        return  Result.success(resultList);
    }
}
