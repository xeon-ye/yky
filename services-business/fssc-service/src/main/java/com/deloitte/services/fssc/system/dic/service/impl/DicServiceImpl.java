package com.deloitte.services.fssc.system.dic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.dic.param.DicQueryForm;
import com.deloitte.platform.api.fssc.dic.vo.DicVo;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.system.dic.entity.Dic;
import com.deloitte.services.fssc.system.dic.entity.DicValue;
import com.deloitte.services.fssc.system.dic.mapper.DicMapper;
import com.deloitte.services.fssc.system.dic.mapper.DicValueMapper;
import com.deloitte.services.fssc.system.dic.service.IDicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description :  Dic服务实现类
 * @Modified :
 */
@Service
@Transactional
public class DicServiceImpl extends ServiceImpl<DicMapper, Dic> implements IDicService {
    private Logger logger = LoggerFactory.getLogger(DicServiceImpl.class);

    @Autowired
    private DicMapper dicMapper;
    @Autowired
    private DicValueMapper dicValueMapper;

   // @Override
  /*  public IPage<Dic> selectPage(DicQueryForm queryForm ) {
        QueryWrapper<Dic> queryWrapper = new QueryWrapper <Dic>();
        //getQueryWrapper(queryWrapper,queryForm);
        return dicMapper.selectPage(new Page<Dic>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }*/

    @Override
    public IPage<Dic> selectPage(DicQueryForm queryForm) {
        Page<Dic> page = new Page(queryForm.getCurrentPage(), queryForm.getPageSize());
        List<Dic> unitInfoVos =
                dicMapper.selectByPageConditions(page, queryForm);
        page.setRecords(unitInfoVos);
        return page;
    }

    @Override
    public List<Dic> selectList(DicQueryForm queryForm) {
        QueryWrapper<Dic> queryWrapper = new QueryWrapper <Dic>();
        //getQueryWrapper(queryWrapper,queryForm);
        return dicMapper.selectList(queryWrapper);
    }

    /*public BaseResponse<Dic> findTypeByPage(PageRequestDto pageRequestDto, Map<String, Object> paramMap) {
        logger.info("进入findTypeByPage");
        BaseResponse<Dic> response = new BaseResponse<Dic>();

        int totalNum = dicMapper.count(paramMap);

        if(totalNum > 0) {
            response.setTotal(totalNum);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("pageIndex", pageRequestDto.getPageIndex() + 1);
            dataMap.put("pageSize", pageRequestDto.getPageIndex() + pageRequestDto.getPageSize());

            dataMap.putAll(paramMap);

            if(!StringUtils.isEmpty(pageRequestDto.getSortField()) && !StringUtils.isEmpty(pageRequestDto.getSortOrder())) {
                dataMap.put("sortField", pageRequestDto.getSortField());
                dataMap.put("sortOrder", pageRequestDto.getSortOrder());
            }

            List<Dic>  list = dicMapper.findByPage(dataMap);
            response.setRows(list);
        }
        return response;
    }*/

    @Transactional
    public int removeByEnumType(List<String> idList) {
        //删除 字典类型
        int  result_type= dicMapper.deleteByEnumType(idList);
        //删除 字典值
        int  result_value=dicValueMapper.deleteByEnumType(idList);
        logger.info("删除字典类型{},删除字典类型条数{}, 删除字典值条数{}",idList,result_type,result_value);
        return result_type;
    }

    @Override
    public int countBySysEnumType(Dic dic) {
        return dicMapper.countBySysEnumType(dic);
    }

    @Override
    public String saveType(Dic dic) {
        dicMapper.insertt(dic);
        return dic.getEumCode();
    }



    @Override
    public int removeByValue(List<Long> idList) {
        return dicValueMapper.deleteById(idList);
    }

    @Override
    public int countBySysEnumValue(DicValue dicValue) {
        return dicValueMapper.countBySysEnumValue(dicValue);
    }

    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<Dic> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(Dic.ID, idList);
        List<Dic> mainTypeList = this.list(queryWrapper);
        for (Dic mainType : mainTypeList) {
            mainType.setIsValid(validFlag);
            if (FsscEums.VALID.getValue().equals(validFlag)) {
                mainType.setUpdateTime(LocalDateTime.now());
            } else {
                mainType.setUpdateTime(LocalDateTime.now());
            }
        }
        return this.updateBatchById(mainTypeList);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Dic> getQueryWrapper(QueryWrapper<Dic> queryWrapper,BaseQueryForm<DicQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(dic.getEumCode())){
            queryWrapper.like(Dic.EUM_CODE,dic.getEumCode());
        }
        if(StringUtils.isNotBlank(dic.getEumName())){
            queryWrapper.like(Dic.EUM_NAME,dic.getEumName());
        }
        if(StringUtils.isNotBlank(dic.getEumDesciption())){
            queryWrapper.like(Dic.EUM_DESCIPTION,dic.getEumDesciption());
        }
        if(StringUtils.isNotBlank(dic.getEumOrder())){
            queryWrapper.like(Dic.EUM_ORDER,dic.getEumOrder());
        }
        if(StringUtils.isNotBlank(dic.getEumConcatTab())){
            queryWrapper.like(Dic.EUM_CONCAT_TAB,dic.getEumConcatTab());
        }
        if(StringUtils.isNotBlank(dic.getIsValid())){
            queryWrapper.like(Dic.IS_VALID,dic.getIsValid());
        }
        if(StringUtils.isNotBlank(dic.getExt1())){
            queryWrapper.like(Dic.EXT_1,dic.getExt1());
        }
        if(StringUtils.isNotBlank(dic.getExt2())){
            queryWrapper.like(Dic.EXT_2,dic.getExt2());
        }
        if(StringUtils.isNotBlank(dic.getExt3())){
            queryWrapper.like(Dic.EXT_3,dic.getExt3());
        }
        if(StringUtils.isNotBlank(dic.getExt4())){
            queryWrapper.like(Dic.EXT_4,dic.getExt4());
        }
        if(StringUtils.isNotBlank(dic.getExt5())){
            queryWrapper.like(Dic.EXT_5,dic.getExt5());
        }
        if(StringUtils.isNotBlank(dic.getCreateTime())){
            queryWrapper.like(Dic.CREATE_TIME,dic.getCreateTime());
        }
        if(StringUtils.isNotBlank(dic.getUpdateTime())){
            queryWrapper.like(Dic.UPDATE_TIME,dic.getUpdateTime());
        }
        if(StringUtils.isNotBlank(dic.getCreateBy())){
            queryWrapper.like(Dic.CREATE_BY,dic.getCreateBy());
        }
        if(StringUtils.isNotBlank(dic.getUpdateBy())){
            queryWrapper.like(Dic.UPDATE_BY,dic.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

