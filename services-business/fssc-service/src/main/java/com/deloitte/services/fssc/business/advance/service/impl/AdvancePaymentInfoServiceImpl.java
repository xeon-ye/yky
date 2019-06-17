package com.deloitte.services.fssc.business.advance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.advance.param.AdvancePaymentInfoQueryForm;
import com.deloitte.platform.api.fssc.advance.vo.AdvancePaymentInfoVo;
import com.deloitte.platform.api.fssc.advance.vo.AdvancePaymentLineVo;
import com.deloitte.platform.api.fssc.advance.vo.ContactDetailVo;
import com.deloitte.platform.api.fssc.advance.vo.VerificationDetailVo;
import com.deloitte.platform.api.fssc.bank.param.BankUnitInfoQueryForm;
import com.deloitte.platform.api.fssc.bank.vo.BankUnitInfoVo;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankVo;
import com.deloitte.platform.api.fssc.file.vo.FileVo;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentInfo;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentLine;
import com.deloitte.services.fssc.business.advance.entity.ContactDetail;
import com.deloitte.services.fssc.business.advance.entity.VerificationDetail;
import com.deloitte.services.fssc.business.advance.mapper.AdvancePaymentInfoMapper;
import com.deloitte.services.fssc.business.advance.mapper.AdvancePaymentLineMapper;
import com.deloitte.services.fssc.business.advance.mapper.ContactDetailMapper;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentInfoService;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.borrow.mapper.BmBorrowBankMapper;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.contract.mapper.CrbAssocAdvancePaymentMapper;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.general.entity.GeExpensePaymentList;
import com.deloitte.services.fssc.business.general.mapper.GeExpenseBorrowPrepayMapper;
import com.deloitte.services.fssc.business.general.mapper.GeExpensePaymentListMapper;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.bank.service.IBankUnitInfoService;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.mapper.FileMapper;
import com.deloitte.services.fssc.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description :  BmAdvancePaymentInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AdvancePaymentInfoServiceImpl extends ServiceImpl<AdvancePaymentInfoMapper, AdvancePaymentInfo> implements IAdvancePaymentInfoService {


    @Autowired
    private AdvancePaymentInfoMapper advancePaymentInfoMapper;

    @Autowired
    private AdvancePaymentLineMapper advancePaymentLineMapper;

    @Autowired
    private ContactDetailMapper contactDetailMapper;



    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private GeExpensePaymentListMapper gGeExpensePaymentListMapper;

    @Autowired
    private BpmTaskBiz bpmTaskBiz;

    @Autowired
    public GeExpenseBorrowPrepayMapper geExpenseBorrowPrepayMapper;

    @Autowired
    public CrbAssocAdvancePaymentMapper crbAssocAdvancePaymentMapper;

    @Autowired
    private BmBorrowBankMapper bmBorrowBankMapper;
    @Autowired
    private IBankUnitInfoService bankUnitInfoService;

    @Override
    public IPage<AdvancePaymentInfo> selectPage(AdvancePaymentInfoQueryForm queryForm ) {
        QueryWrapper<AdvancePaymentInfo> queryWrapper = new QueryWrapper <AdvancePaymentInfo>();
        queryWrapper.eq(FsscCommonUtil.getCurrentUserId()!=null,"CREATE_BY", FsscCommonUtil.getCurrentUserId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPayStatus()),"PAY_STATUS",queryForm.getPayStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),"UNIT_ID",queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),"DEPT_ID",queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getMainTypeId()),"MAIN_TYPE_ID",queryForm.getMainTypeId());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"TOTAL_SUM",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"TOTAL_SUM",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getSupplierId()),"SUPPLIER_ID",queryForm.getSupplierId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()),"PROJECT_ID",queryForm.getProjectId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getContactNumber()),"CONTACT_NUMBER",queryForm.getContactNumber());
        queryWrapper.orderByDesc("CREATE_TIME");
        return advancePaymentInfoMapper.selectPage(new Page<AdvancePaymentInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AdvancePaymentInfo> selectList(AdvancePaymentInfoQueryForm queryForm) {
        QueryWrapper<AdvancePaymentInfo> queryWrapper = new QueryWrapper <AdvancePaymentInfo>();
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPayStatus()),"PAY_STATUS",queryForm.getPayStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),"UNIT_ID",queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),"DEPT_ID",queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getMainTypeId()),"MAIN_TYPE_ID",queryForm.getMainTypeId());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"TOTAL_SUM",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"TOTAL_SUM",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getSupplierId()),"SUPPLIER_ID",queryForm.getSupplierId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()),"PROJECT_ID",queryForm.getProjectId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getContactNumber()),"CONTACT_NUMBER",queryForm.getContactNumber());
        queryWrapper.orderByDesc("CREATE_TIME");
        return advancePaymentInfoMapper.selectList(queryWrapper);
    }

    @Override
    public AdvancePaymentInfoVo findInfoById(Long advanceId) {

        //主表信息
        AdvancePaymentInfo byId = advancePaymentInfoMapper.selectById(advanceId);
        AdvancePaymentInfoVo advancePaymentInfoVo =
                new BeanUtils<AdvancePaymentInfoVo>().copyObj(byId, AdvancePaymentInfoVo.class);

        //行信息
        QueryWrapper<AdvancePaymentLine> wrapper=new QueryWrapper<>();
        wrapper.eq("DOCUMENT_ID",advanceId).eq("DOCUMENT_TYPE", FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());;
        List<AdvancePaymentLine> lines = advancePaymentLineMapper.selectList(wrapper);
        List<AdvancePaymentLineVo> borrowMoneyLineVos =
                new BeanUtils<AdvancePaymentLineVo>().copyObjs(lines, AdvancePaymentLineVo.class);
        advancePaymentInfoVo.setAdvanceMoneyLineList(borrowMoneyLineVos);
        //合同信息
        QueryWrapper<ContactDetail> bankQueryWrapper=new QueryWrapper<>();
        bankQueryWrapper.eq("DOCUMENT_ID",advanceId).eq("DOCUMENT_TYPE", FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
        List<ContactDetail> bmBorrowBanks = contactDetailMapper.selectList(bankQueryWrapper);
        advancePaymentInfoVo.setBmContactDetaiVos(new BeanUtils<ContactDetailVo>().copyObjs(bmBorrowBanks, ContactDetailVo.class));
        //公务卡
        QueryWrapper<BmBorrowBank> businessQueryWrapper=new QueryWrapper<>();
        businessQueryWrapper.eq("DOCUMENT_ID",advanceId)
                .eq("DOCUMENT_TYPE", FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue())
                .eq("GET_OR_RETURN","BUSINESS");
        List<BmBorrowBank> business = bmBorrowBankMapper.selectList(businessQueryWrapper);
        advancePaymentInfoVo.setBusinessCards(new BeanUtils<BmBorrowBankVo>().copyObjs(business,BmBorrowBankVo.class));
        //核销详情
        QueryWrapper<GeExpenseBorrowPrepay>  prepayQueryWrapper=new QueryWrapper<>();
        prepayQueryWrapper.eq("BORROW_OR_PREPAY_ID",advanceId).eq("CONNECT_DOCUMENT_TYPE", FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
        List<GeExpenseBorrowPrepay> bmVerificationDetailList = geExpenseBorrowPrepayMapper.selectList(prepayQueryWrapper);
        List<VerificationDetail> verificationDetails = new ArrayList<VerificationDetail>();
        if(CollectionUtils.isNotEmpty(bmVerificationDetailList)){
            for (GeExpenseBorrowPrepay geExpenseBorrowPrepay:bmVerificationDetailList) {
                VerificationDetail verificationDetail =new VerificationDetail();
                verificationDetail.setDocumentId(advanceId);
                verificationDetail.setDocumentType(geExpenseBorrowPrepay.getDocumentType());
                verificationDetail.setDocumentNum(geExpenseBorrowPrepay.getDocumentNum());
                verificationDetail.setSubTypeName(geExpenseBorrowPrepay.getSubTypeName());
                verificationDetail.setSubTypeCode(geExpenseBorrowPrepay.getSubTypeCode());
                verificationDetail.setSubTypeId(geExpenseBorrowPrepay.getSubTypeId());
                verificationDetail.setMainTypeCode(geExpenseBorrowPrepay.getMainTypeCode());
                verificationDetail.setMainTypeId(geExpenseBorrowPrepay.getMainTypeId());
                verificationDetail.setMainTypeName(geExpenseBorrowPrepay.getMainTypeName());
                verificationDetail.setVerificationAmount(geExpenseBorrowPrepay.getCurrentVerAmount());
                verificationDetail.setVerificationTime(geExpenseBorrowPrepay.getVerficationDate());
                verificationDetail.setVerificationExplain(geExpenseBorrowPrepay.getVerficationRemark());
                verificationDetail.setAccountCode(geExpenseBorrowPrepay.getAccountCode());
                verificationDetail.setBudgetAccountCode(geExpenseBorrowPrepay.getBudgetAccountCode());
                verificationDetail.setTravelPlanId(geExpenseBorrowPrepay.getTravelPlanId());
                verificationDetail.setEx1(geExpenseBorrowPrepay.getEx1());
                verificationDetails.add(verificationDetail);
            }
        }

        /*//合同报账核销明细
        QueryWrapper<CrbAssocAdvancePayment> crbAssocQueryWrapper=new QueryWrapper<>();
        crbAssocQueryWrapper.eq("DOCUMENT_ID",advanceId).eq("DOCUMENT_TYPE", FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
        List<CrbAssocAdvancePayment> bmVerificationDetailLists = crbAssocAdvancePaymentMapper.selectList(crbAssocQueryWrapper);
        if(CollectionUtils.isNotEmpty(bmVerificationDetailLists)){
            for (CrbAssocAdvancePayment crbAssocAdvancePayment:bmVerificationDetailLists) {
                VerificationDetail verificationDetailLi =new VerificationDetail();
                verificationDetailLi.setAdvancePaymentId(advanceId);
                verificationDetailLi.setDocumentType(crbAssocAdvancePayment.getDocumentType());
                verificationDetailLi.setDocumentNum(crbAssocAdvancePayment.getDocumentNum());
                verificationDetailLi.setSubTypeName(crbAssocAdvancePayment.getSubTypeName());
                verificationDetailLi.setVerificationAmount(crbAssocAdvancePayment.getHasVerAmount());
                verificationDetailLi.setVerificationTime(crbAssocAdvancePayment.getCancellationTime());
                verificationDetailLi.setVerificationExplain(crbAssocAdvancePayment.getVerInstructions());
                verificationDetails.add(verificationDetailLi);
            }
        }

*/
        advancePaymentInfoVo.setVerificationDetailVos(new BeanUtils<VerificationDetailVo>().copyObjs(verificationDetails, VerificationDetailVo.class));
        QueryWrapper<File> fileQueryWrapper=new QueryWrapper<>();
        fileQueryWrapper.eq("DOCUMENT_ID",advanceId).eq("DOCUMENT_TYPE", FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
        advancePaymentInfoVo.setFileVos(new BeanUtils<FileVo>().copyObjs(fileMapper.selectList(fileQueryWrapper), FileVo.class));

        //对公付款单信息
        QueryWrapper<GeExpensePaymentList> geExpensePaymentQueryWrapper=new QueryWrapper<>();
        geExpensePaymentQueryWrapper.eq("DOCUMENT_ID",advanceId).eq("DOCUMENT_TYPE", FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
        List<GeExpensePaymentList> geExpensePayment = gGeExpensePaymentListMapper.selectList(geExpensePaymentQueryWrapper);
        List<GeExpensePaymentListVo> geExpensePaymentListVos = new BeanUtils<GeExpensePaymentListVo>().copyObjs(geExpensePayment, GeExpensePaymentListVo.class);
        if(CollectionUtils.isNotEmpty(geExpensePaymentListVos)){
            for (GeExpensePaymentListVo vo:geExpensePaymentListVos){
                String vendorCode = vo.getVendorCode();
                if(StringUtil.isNotEmpty(vendorCode)){
                    BankUnitInfoQueryForm form=new BankUnitInfoQueryForm();
                    form.setUnitCode(vendorCode);
                    List<BankUnitInfoVo> records = bankUnitInfoService.selectPage(form).getRecords();
                    vo.setBankUnitInfoVos(records);
                }
            }
        }
        advancePaymentInfoVo.setGeExpensePaymentFormList(geExpensePaymentListVos);
        //对公审批历史信息
        String documentType=FsscEums.ADP_ADVANCE_PAYMENT_INFO.getValue();
        try {
            List<BpmProcessTaskVo> history = bpmTaskBiz.findHistory(advanceId, documentType);
            advancePaymentInfoVo.setBpmProcessTaskVos(new BeanUtils<BpmProcessTaskVo>().copyObjs(history,BpmProcessTaskVo.class));
        }catch (FSSCException e){
            e.getMessage();
        }
        return advancePaymentInfoVo;
    }

    @Override
    public boolean existsByExpenseMainTypeAdvIds(List<Long> expenseMainTypeIdList) {
        return advancePaymentInfoMapper.countByExpenseMainTypeIds(expenseMainTypeIdList) > 0 ? true : false;
    }


    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<AdvancePaymentInfo> getQueryWrapper(QueryWrapper<AdvancePaymentInfo> queryWrapper,BaseQueryForm<AdvancePaymentInfoQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getCreateBy())){
            queryWrapper.like(AdvancePaymentInfo.CREATE_BY,bmAdvancePaymentInfo.getCreateBy());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getCreateUserName())){
            queryWrapper.like(AdvancePaymentInfo.CREATE_USER_NAME,bmAdvancePaymentInfo.getCreateUserName());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getUpdateBy())){
            queryWrapper.like(AdvancePaymentInfo.UPDATE_BY,bmAdvancePaymentInfo.getUpdateBy());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getUpdateTime())){
            queryWrapper.like(AdvancePaymentInfo.UPDATE_TIME,bmAdvancePaymentInfo.getUpdateTime());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getCreateTime())){
            queryWrapper.like(AdvancePaymentInfo.CREATE_TIME,bmAdvancePaymentInfo.getCreateTime());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getVersion())){
            queryWrapper.like(AdvancePaymentInfo.VERSION,bmAdvancePaymentInfo.getVersion());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getDocumentNum())){
            queryWrapper.like(AdvancePaymentInfo.DOCUMENT_NUM,bmAdvancePaymentInfo.getDocumentNum());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getDocumentStatus())){
            queryWrapper.like(AdvancePaymentInfo.DOCUMENT_STATUS,bmAdvancePaymentInfo.getDocumentStatus());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getPayStatus())){
            queryWrapper.like(AdvancePaymentInfo.PAY_STATUS,bmAdvancePaymentInfo.getPayStatus());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getUnitId())){
            queryWrapper.like(AdvancePaymentInfo.UNIT_ID,bmAdvancePaymentInfo.getUnitId());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getDeptId())){
            queryWrapper.like(AdvancePaymentInfo.DEPT_ID,bmAdvancePaymentInfo.getDeptId());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getProjectId())){
            queryWrapper.like(AdvancePaymentInfo.PROJECT_ID,bmAdvancePaymentInfo.getProjectId());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getMainTypeId())){
            queryWrapper.like(AdvancePaymentInfo.MAIN_TYPE_ID,bmAdvancePaymentInfo.getMainTypeId());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getTotalSum())){
            queryWrapper.like(AdvancePaymentInfo.TOTAL_SUM,bmAdvancePaymentInfo.getTotalSum());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getHasVerAmount())){
            queryWrapper.like(AdvancePaymentInfo.HAS_VER_AMOUNT,bmAdvancePaymentInfo.getHasVerAmount());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getNoVerAmount())){
            queryWrapper.like(AdvancePaymentInfo.NO_VER_AMOUNT,bmAdvancePaymentInfo.getNoVerAmount());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getCurrencyCode())){
            queryWrapper.like(AdvancePaymentInfo.CURRENCY_CODE,bmAdvancePaymentInfo.getCurrencyCode());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getIsAgreedPromis())){
            queryWrapper.like(AdvancePaymentInfo.IS_AGREED_PROMIS,bmAdvancePaymentInfo.getIsAgreedPromis());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getRemark())){
            queryWrapper.like(AdvancePaymentInfo.REMARK,bmAdvancePaymentInfo.getRemark());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getCost())){
            queryWrapper.like(AdvancePaymentInfo.COST,bmAdvancePaymentInfo.getCost());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getUnitName())){
            queryWrapper.like(AdvancePaymentInfo.UNIT_NAME,bmAdvancePaymentInfo.getUnitName());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getDeptName())){
            queryWrapper.like(AdvancePaymentInfo.DEPT_NAME,bmAdvancePaymentInfo.getDeptName());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getMainTypeName())){
            queryWrapper.like(AdvancePaymentInfo.MAIN_TYPE_NAME,bmAdvancePaymentInfo.getMainTypeName());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getProjectName())){
            queryWrapper.like(AdvancePaymentInfo.PROJECT_NAME,bmAdvancePaymentInfo.getProjectName());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getPaymentType())){
            queryWrapper.like(AdvancePaymentInfo.PAYMENT_TYPE,bmAdvancePaymentInfo.getPaymentType());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getSupplier())){
            queryWrapper.like(AdvancePaymentInfo.SUPPLIER,bmAdvancePaymentInfo.getSupplier());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getSupplierId())){
            queryWrapper.like(AdvancePaymentInfo.SUPPLIER_ID,bmAdvancePaymentInfo.getSupplierId());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getContactNumberId())){
            queryWrapper.like(AdvancePaymentInfo.CONTACT_NUMBER_ID,bmAdvancePaymentInfo.getContactNumberId());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getContactNumber())){
            queryWrapper.like(AdvancePaymentInfo.CONTACT_NUMBER,bmAdvancePaymentInfo.getContactNumber());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getSupportFileNum())){
            queryWrapper.like(AdvancePaymentInfo.SUPPORT_FILE_NUM,bmAdvancePaymentInfo.getSupportFileNum());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getTotalSumPosition())){
            queryWrapper.like(AdvancePaymentInfo.TOTAL_SUM_POSITION,bmAdvancePaymentInfo.getTotalSumPosition());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getHasVerAmountPosition())){
            queryWrapper.like(AdvancePaymentInfo.HAS_VER_AMOUNT_POSITION,bmAdvancePaymentInfo.getHasVerAmountPosition());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getNoVerAmountPosition())){
            queryWrapper.like(AdvancePaymentInfo.NO_VER_AMOUNT_POSITION,bmAdvancePaymentInfo.getNoVerAmountPosition());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt1())){
            queryWrapper.like(AdvancePaymentInfo.EXT1,bmAdvancePaymentInfo.getExt1());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt2())){
            queryWrapper.like(AdvancePaymentInfo.EXT2,bmAdvancePaymentInfo.getExt2());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt3())){
            queryWrapper.like(AdvancePaymentInfo.EXT3,bmAdvancePaymentInfo.getExt3());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt4())){
            queryWrapper.like(AdvancePaymentInfo.EXT4,bmAdvancePaymentInfo.getExt4());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt5())){
            queryWrapper.like(AdvancePaymentInfo.EXT5,bmAdvancePaymentInfo.getExt5());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt6())){
            queryWrapper.like(AdvancePaymentInfo.EXT6,bmAdvancePaymentInfo.getExt6());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt7())){
            queryWrapper.like(AdvancePaymentInfo.EXT7,bmAdvancePaymentInfo.getExt7());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt8())){
            queryWrapper.like(AdvancePaymentInfo.EXT8,bmAdvancePaymentInfo.getExt8());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt9())){
            queryWrapper.like(AdvancePaymentInfo.EXT9,bmAdvancePaymentInfo.getExt9());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt10())){
            queryWrapper.like(AdvancePaymentInfo.EXT10,bmAdvancePaymentInfo.getExt10());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt11())){
            queryWrapper.like(AdvancePaymentInfo.EXT11,bmAdvancePaymentInfo.getExt11());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt12())){
            queryWrapper.like(AdvancePaymentInfo.EXT12,bmAdvancePaymentInfo.getExt12());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt13())){
            queryWrapper.like(AdvancePaymentInfo.EXT13,bmAdvancePaymentInfo.getExt13());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt14())){
            queryWrapper.like(AdvancePaymentInfo.EXT14,bmAdvancePaymentInfo.getExt14());
        }
        if(StringUtils.isNotBlank(bmAdvancePaymentInfo.getExt15())){
            queryWrapper.like(AdvancePaymentInfo.EXT15,bmAdvancePaymentInfo.getExt15());
        }
        return queryWrapper;
    }
     */
}

