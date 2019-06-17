package com.deloitte.services.fssc.engine.automatic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvVoucherLogicInfoQueryForm;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountLogicHead;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountLogicLine;
import com.deloitte.services.fssc.engine.automatic.entity.AvVoucherLogicInfo;
import com.deloitte.services.fssc.engine.automatic.mapper.AvAccountLogicHeadMapper;
import com.deloitte.services.fssc.engine.automatic.mapper.AvAccountLogicLineMapper;
import com.deloitte.services.fssc.engine.automatic.mapper.AvLedgerUnitRelationMapper;
import com.deloitte.services.fssc.engine.automatic.mapper.AvVoucherLogicInfoMapper;
import com.deloitte.services.fssc.engine.automatic.service.IAvVoucherLogicInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description :  AvVoucherLogicInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AvVoucherLogicInfoServiceImpl extends ServiceImpl<AvVoucherLogicInfoMapper, AvVoucherLogicInfo> implements IAvVoucherLogicInfoService {


    @Autowired
    private AvVoucherLogicInfoMapper avVoucherLogicInfoMapper;
    @Autowired
    private AvLedgerUnitRelationMapper avLedgerUnitRelationMapper;
    @Autowired
    private AvAccountLogicHeadMapper  avAccountLogicHeadMapper;
    @Autowired
    private AvAccountLogicLineMapper  avAccountLogicLineMapper;

    @Override
    public IPage<AvVoucherLogicInfo> selectPage(AvVoucherLogicInfoQueryForm queryForm ) {
        QueryWrapper<AvVoucherLogicInfo> queryWrapper = new QueryWrapper <AvVoucherLogicInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avVoucherLogicInfoMapper.selectPage(new Page<AvVoucherLogicInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AvVoucherLogicInfo> selectList(AvVoucherLogicInfoQueryForm queryForm) {
        QueryWrapper<AvVoucherLogicInfo> queryWrapper = new QueryWrapper <AvVoucherLogicInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avVoucherLogicInfoMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<AvVoucherLogicInfo> getQueryWrapper(QueryWrapper<AvVoucherLogicInfo> queryWrapper,AvVoucherLogicInfoQueryForm queryForm){
      /*  //条件拼接
        if(StringUtils.isNotBlank(queryForm.getLedgerId())){
            queryWrapper.eq(AvVoucherLogicInfo.LEDGER_ID,queryForm.getLedgerId());
        }
        if(StringUtils.isNotBlank(queryForm.getType())){
            queryWrapper.eq(AvVoucherLogicInfo.TYPE,queryForm.getType());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateDate())){
            queryWrapper.eq(AvVoucherLogicInfo.CREATE_DATE,queryForm.getCreateDate());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(AvVoucherLogicInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateDate())){
            queryWrapper.eq(AvVoucherLogicInfo.UPDATE_DATE,queryForm.getUpdateDate());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(AvVoucherLogicInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx1())){
            queryWrapper.eq(AvVoucherLogicInfo.ETX_1,queryForm.getEtx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx2())){
            queryWrapper.eq(AvVoucherLogicInfo.ETX_2,queryForm.getEtx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx3())){
            queryWrapper.eq(AvVoucherLogicInfo.ETX_3,queryForm.getEtx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx4())){
            queryWrapper.eq(AvVoucherLogicInfo.ETX_4,queryForm.getEtx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx5())){
            queryWrapper.eq(AvVoucherLogicInfo.ETX_5,queryForm.getEtx5());
        }*/
        return queryWrapper;
    }

   public List<BaseDocumentType> getDocumentTypeList(Long ledgerId){
      return  avLedgerUnitRelationMapper.getDocumentTypeList(ledgerId);
   }

    /**
     * 根据LogidId获取得到单据下的凭证头信息
     * @param logicId
     * @return
     */
   public List<AvAccountLogicHead> getLogicHeadListByLogicId(Long logicId){
        QueryWrapper<AvAccountLogicHead> queryWrapper = new QueryWrapper <AvAccountLogicHead>();
        queryWrapper.eq(AvAccountLogicHead.LOGIC_ID,logicId);
        return avAccountLogicHeadMapper.selectList(queryWrapper);

    }

    /**
     * 根据LogidId获取得到单据下的凭证行信息
     * @param logicId
     * @return
     */
   public   List<AvAccountLogicLine>getLogicLineListByLogicId(Long logicId){
       QueryWrapper<AvAccountLogicLine> queryWrapper = new QueryWrapper <AvAccountLogicLine>();
       queryWrapper.eq(AvAccountLogicLine.LOGIC_ID,logicId);
       return avAccountLogicLineMapper.selectList(queryWrapper);
    }

    public  List<AvVoucherLogicInfo> getLedgerInfo(String type,String unitCode){
       return avVoucherLogicInfoMapper.getLedgerInfo(type,unitCode);
    }

}

