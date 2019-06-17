package com.deloitte.services.fssc.business.borrow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.borrow.param.BorrowMoneyInfoQueryForm;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankVo;
import com.deloitte.platform.api.fssc.borrow.vo.BorrowMoneyInfoVo;
import com.deloitte.platform.api.fssc.borrow.vo.BorrowMoneyLineVo;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayVo;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyInfo;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyLine;
import com.deloitte.services.fssc.business.borrow.mapper.BmBorrowBankMapper;
import com.deloitte.services.fssc.business.borrow.mapper.BorrowMoneyInfoMapper;
import com.deloitte.services.fssc.business.borrow.mapper.BorrowMoneyLineMapper;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyInfoService;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.general.entity.GeExpensePaymentList;
import com.deloitte.services.fssc.business.general.service.IGeExpenseBorrowPrepayService;
import com.deloitte.services.fssc.business.general.service.IGeExpensePaymentListService;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description :  BorrowMoneyInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class BorrowMoneyInfoServiceImpl extends ServiceImpl<BorrowMoneyInfoMapper, BorrowMoneyInfo> implements IBorrowMoneyInfoService {


    @Autowired
    private BorrowMoneyInfoMapper borrowMoneyInfoMapper;
    @Autowired
    private BorrowMoneyLineMapper borrowMoneyLineMapper;
    @Autowired
    private BmBorrowBankMapper bmBorrowBankMapper;
    @Autowired
    private IGeExpenseBorrowPrepayService geExpenseBorrowPrepayService;
    @Autowired
    private IGeExpensePaymentListService geExpensePaymentListService;
    @Autowired
    private BpmTaskBiz bpmTaskBiz;
    @Override
    public IPage<BorrowMoneyInfo> selectPage(BorrowMoneyInfoQueryForm queryForm ) {
        QueryWrapper<BorrowMoneyInfo> queryWrapper = new QueryWrapper <BorrowMoneyInfo>();
        queryWrapper.eq(FsscCommonUtil.getCurrentUserId()!=null,"CREATE_BY", FsscCommonUtil.getCurrentUserId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPayStatus()),"PAY_STATUS",queryForm.getPayStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),"UNIT_ID",queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),"DEPT_ID",queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()),"PROJECT_ID",queryForm.getProjectId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getMainTypeId()),"MAIN_TYPE_ID",queryForm.getMainTypeId());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"BORROW_AMOUNT",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"BORROW_AMOUNT",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());
        queryWrapper.orderByDesc("UPDATE_TIME");
        return borrowMoneyInfoMapper.selectPage(new Page<BorrowMoneyInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()),
                queryWrapper);

        }

    @Override
    public List<BorrowMoneyInfo> selectList(BorrowMoneyInfoQueryForm queryForm) {
        QueryWrapper<BorrowMoneyInfo> queryWrapper = new QueryWrapper <BorrowMoneyInfo>();
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPayStatus()),"PAY_STATUS",queryForm.getPayStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),"UNIT_ID",queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),"DEPT_ID",queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getApplyForId()),"APPLY_FOR_ID",queryForm.getApplyForId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()),"PROJECT_ID",queryForm.getProjectId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getMainTypeId()),"MAIN_TYPE_ID",queryForm.getMainTypeId());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"BORROW_AMOUNT",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"BORROW_AMOUNT",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());

        queryWrapper.orderByDesc("UPDATE_TIME");
        return borrowMoneyInfoMapper.selectList(queryWrapper);
    }

    @Override
    public BorrowMoneyInfoVo findInfoById(Long borrowId) {

        //主表信息
        BorrowMoneyInfo byId = borrowMoneyInfoMapper.selectById(borrowId);
        BorrowMoneyInfoVo borrowMoneyInfoVo =
                new BeanUtils<BorrowMoneyInfoVo>().copyObj(byId, BorrowMoneyInfoVo.class);

        //行信息
        QueryWrapper<BorrowMoneyLine> wrapper=new QueryWrapper<>();
        wrapper.eq(BorrowMoneyLine.DOCUMENT_ID,borrowId);
        List<BorrowMoneyLine> lines = borrowMoneyLineMapper.selectList(wrapper);
        List<BorrowMoneyLineVo> borrowMoneyLineVos =
                new BeanUtils<BorrowMoneyLineVo>().copyObjs(lines, BorrowMoneyLineVo.class);
        borrowMoneyInfoVo.setBorrowMoneyLineList(borrowMoneyLineVos);
        //工资卡 和
        QueryWrapper<BmBorrowBank> bankQueryWrapper=new QueryWrapper<>();
        bankQueryWrapper.eq(BorrowMoneyLine.DOCUMENT_ID,borrowId)
                .eq("DOCUMENT_TYPE", FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue())
                .eq("GET_OR_RETURN","SALARY");
        List<BmBorrowBank> bmBorrowBanks = bmBorrowBankMapper.selectList(bankQueryWrapper);
        borrowMoneyInfoVo.setSalaryCards(new BeanUtils<BmBorrowBankVo>().copyObjs(bmBorrowBanks,BmBorrowBankVo.class));

        //公务卡
        QueryWrapper<BmBorrowBank> businessQueryWrapper=new QueryWrapper<>();
        businessQueryWrapper.eq(BorrowMoneyLine.DOCUMENT_ID,borrowId)
                .eq("DOCUMENT_TYPE", FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue())
                .eq("GET_OR_RETURN","BUSINESS");
        List<BmBorrowBank> business = bmBorrowBankMapper.selectList(businessQueryWrapper);
        borrowMoneyInfoVo.setBusinessCards(new BeanUtils<BmBorrowBankVo>().copyObjs(business,BmBorrowBankVo.class));

        //核销明细
        QueryWrapper<GeExpenseBorrowPrepay> borrowPrepayQueryWrapper=new QueryWrapper<>();
        borrowPrepayQueryWrapper.eq("BORROW_OR_PREPAY_ID",borrowId)
                .eq("CONNECT_DOCUMENT_TYPE", FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
        List<GeExpenseBorrowPrepay> geExpenseBorrowPrepays = geExpenseBorrowPrepayService.list(borrowPrepayQueryWrapper);
        geExpenseBorrowPrepayService.paddingHexiaomingxi(geExpenseBorrowPrepays);
        borrowMoneyInfoVo.setExpenseBorrowPrepayVos(new BeanUtils<GeExpenseBorrowPrepayVo>().copyObjs(geExpenseBorrowPrepays,GeExpenseBorrowPrepayVo.class));
        try {
            borrowMoneyInfoVo.setBpmProcessTaskVos(bpmTaskBiz.findHistory(borrowId,FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue()));
        }catch (Exception e){
            log.error("error:{}"+e.getMessage());
        }

        //对公付款清单
        QueryWrapper<GeExpensePaymentList> paymentListQueryWrapper=new QueryWrapper<>();
        paymentListQueryWrapper.eq(GeExpensePaymentList.DOCUMENT_ID,borrowId)
                .eq(GeExpensePaymentList.DOCUMENT_TYPE,FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
        List<GeExpensePaymentList> list = geExpensePaymentListService.list(paymentListQueryWrapper);
        borrowMoneyInfoVo.setPaymentListVos(new BeanUtils<GeExpensePaymentListVo>()
        .copyObjs(list,GeExpensePaymentListVo.class));

        return borrowMoneyInfoVo;
    }


    @Override
    public boolean existsByExpenseMainTypeIds(List<Long> expenseMainTypeIdList) {
       return borrowMoneyInfoMapper.countByExpenseMainTypeIds(expenseMainTypeIdList) > 0 ? true : false;
    }

}

