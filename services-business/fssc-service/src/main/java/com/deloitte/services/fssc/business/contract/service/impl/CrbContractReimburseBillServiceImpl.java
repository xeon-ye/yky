package com.deloitte.services.fssc.business.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.advance.vo.ContactDetailVo;
import com.deloitte.platform.api.fssc.bank.param.BankUnitInfoQueryForm;
import com.deloitte.platform.api.fssc.bank.vo.BankUnitInfoVo;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankVo;
import com.deloitte.platform.api.fssc.contract.param.CrbContractReimburseBillQueryForm;
import com.deloitte.platform.api.fssc.contract.vo.CrbContractReimburseBillVo;
import com.deloitte.platform.api.fssc.general.param.GeExpenseBorrowPrepayQueryForm;
import com.deloitte.platform.api.fssc.general.vo.BorrowPrepayListVo;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayVo;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.business.advance.entity.ContactDetail;
import com.deloitte.services.fssc.business.advance.mapper.ContactDetailMapper;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.borrow.mapper.BmBorrowBankMapper;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.contract.entity.CrbContractReimburseBill;
import com.deloitte.services.fssc.business.contract.mapper.CrbContractReimburseBillMapper;
import com.deloitte.services.fssc.business.contract.service.ICrbContractReimburseBillService;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.general.entity.GeExpensePaymentList;
import com.deloitte.services.fssc.business.general.mapper.GeExpensePaymentListMapper;
import com.deloitte.services.fssc.business.general.service.IGeExpenseBorrowPrepayService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.bank.service.IBankUnitInfoService;
import com.deloitte.services.fssc.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-03-26
 * @Description :  CrbContractReimburseBill服务实现类
 * @Modified :
 */
@Service
@Transactional
public class CrbContractReimburseBillServiceImpl extends ServiceImpl<CrbContractReimburseBillMapper, CrbContractReimburseBill> implements ICrbContractReimburseBillService {

    @Autowired
    private IGeExpenseBorrowPrepayService prepayService;

    @Autowired
    private CrbContractReimburseBillMapper crbContractReimburseBillMapper;

    @Autowired
    private ContactDetailMapper contactDetailMapper;



    @Autowired
    private GeExpensePaymentListMapper gGeExpensePaymentListMapper;

    @Autowired
    private BpmTaskBiz bpmTaskBiz;



    @Autowired
    private IBankUnitInfoService bankUnitInfoService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private BmBorrowBankMapper bmBorrowBankMapper;

    @Override
    public IPage<CrbContractReimburseBill> selectPage(CrbContractReimburseBillQueryForm queryForm ) {
        QueryWrapper<CrbContractReimburseBill> queryWrapper = new QueryWrapper <CrbContractReimburseBill>();
        //getQueryWrapper(queryWrapper,queryForm);
        queryWrapper.eq(FsscCommonUtil.getCurrentUserId()!=null,"CREATE_BY", FsscCommonUtil.getCurrentUserId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPayStatus()),"PAY_STATUS",queryForm.getPayStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),"UNIT_ID",queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),"DEPT_ID",queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getMainTypeId()),"MAIN_TYPE_ID",queryForm.getMainTypeId());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"TOTAL_SUM",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"TOTAL_SUM",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getSupplierId()),"SUPPLIER_ID",queryForm.getSupplierId());
        queryWrapper.eq(StringUtil.isNotEmpty(queryForm.getSupportFileNum()),"SUPPORT_FILE_NUM",queryForm.getSupportFileNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getContactNumber()),"CONTACT_NUMBER",queryForm.getContactNumber());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPaymentType()),"PAYMENT_TYPE",queryForm.getPaymentType());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()),"PROJECT_ID",queryForm.getProjectId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCurrencyCode()),"CURRENCY_CODE",queryForm.getCurrencyCode());
        queryWrapper.orderByDesc("CREATE_TIME");

        return crbContractReimburseBillMapper.selectPage(new Page<CrbContractReimburseBill>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<CrbContractReimburseBill> selectList(CrbContractReimburseBillQueryForm queryForm) {
        QueryWrapper<CrbContractReimburseBill> queryWrapper = new QueryWrapper <CrbContractReimburseBill>();
        //getQueryWrapper(queryWrapper,queryForm);
        return crbContractReimburseBillMapper.selectList(queryWrapper);
    }

    public CrbContractReimburseBillVo findByAllData(Long contractId) {
        CrbContractReimburseBill crbContractReimburseBill = crbContractReimburseBillMapper.selectById(contractId);
        CrbContractReimburseBillVo crbContractReimburseBillVo =
                new BeanUtils<CrbContractReimburseBillVo>().copyObj(crbContractReimburseBill, CrbContractReimburseBillVo.class);
        //合同详情
        QueryWrapper<ContactDetail> bankQueryWrapper = new QueryWrapper<>();
        bankQueryWrapper.eq("DOCUMENT_ID", contractId).eq("DOCUMENT_TYPE", FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        List<ContactDetail> bmBorrowBanks = contactDetailMapper.selectList(bankQueryWrapper);
        crbContractReimburseBillVo.setBmContactDetaiVos(new BeanUtils<ContactDetailVo>().copyObjs(bmBorrowBanks, ContactDetailVo.class));
       //关联预付款查询
        QueryWrapper<GeExpenseBorrowPrepay> crbAssocQuerryWrapper= new QueryWrapper<>();
        crbAssocQuerryWrapper.eq("DOCUMENT_ID",contractId).eq("DOCUMENT_TYPE", FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        List<GeExpenseBorrowPrepay>  CrbAssocAdvancePaymentList=prepayService.list(crbAssocQuerryWrapper);
        prepayService.paddingHexiaomingxi(CrbAssocAdvancePaymentList);
        List<GeExpenseBorrowPrepayVo> geExpenseBorrowPrepayVos = new BeanUtils<GeExpenseBorrowPrepayVo>().copyObjs(CrbAssocAdvancePaymentList, GeExpenseBorrowPrepayVo.class);
        crbContractReimburseBillVo.setBorrowPrepayVoList(geExpenseBorrowPrepayVos);
        //对公付款清单
        QueryWrapper<GeExpensePaymentList> geExpenseQueryWrapper= new QueryWrapper<>();
        geExpenseQueryWrapper.eq("DOCUMENT_ID",contractId).eq("DOCUMENT_TYPE",FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        List<GeExpensePaymentList> geExpensePaymentList=gGeExpensePaymentListMapper.selectList(geExpenseQueryWrapper);
        List<GeExpensePaymentListVo> geExpensePaymentListVos = new BeanUtils<GeExpensePaymentListVo>().copyObjs(geExpensePaymentList, GeExpensePaymentListVo.class);
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
        crbContractReimburseBillVo.setGeExpensePaymentFormList(geExpensePaymentListVos);
        //公务卡
        QueryWrapper<BmBorrowBank> businessQueryWrapper=new QueryWrapper<>();
        businessQueryWrapper.eq("DOCUMENT_ID",contractId)
                .eq("DOCUMENT_TYPE", FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue())
                .eq("GET_OR_RETURN","BUSINESS");
        List<BmBorrowBank> business = bmBorrowBankMapper.selectList(businessQueryWrapper);
        crbContractReimburseBillVo.setBusinessCards(new BeanUtils<BmBorrowBankVo>().copyObjs(business,BmBorrowBankVo.class));
        //审批历史
        String documentType=FsscEums.CRB_CONTRACT_REIMBURSE_BILL.getValue();
        try {
            List<BpmProcessTaskVo> history = bpmTaskBiz.findHistory(contractId, documentType);
            crbContractReimburseBillVo.setBpmProcessTaskVos(new BeanUtils<BpmProcessTaskVo>().copyObjs(history,BpmProcessTaskVo.class));
        }catch (FSSCException e){
            e.getMessage();
        }
        //todo凭证预览
        return crbContractReimburseBillVo;
    }



    @Override
    public boolean existsByExpenseMainTypeAdvIds(List<Long> idList) {
        return crbContractReimburseBillMapper.countByExpenseMainTypeIds(idList) > 0 ? true : false;
    }

    public  List<BorrowPrepayListVo> findAdpPrepayList(GeExpenseBorrowPrepayQueryForm form){
        DeptVo currentDept = commonServices.getCurrentDept();
        UserVo currentUser = commonServices.getCurrentUser();
        OrganizationVo currentOrg = commonServices.getCurrentOrg();
        String paymentTypes = form.getPaymentTypes();
        if(StringUtil.isNotEmpty(paymentTypes)){
            String[] split = paymentTypes.split(",");
            List<String> strings = Arrays.asList(split);
            form.setPaymentTypeList(strings);
        }else {
            return new ArrayList<BorrowPrepayListVo>();
        }
        form.setCreateBy(StringUtil.castTolong(currentUser.getId()));
        form.setDeptId(StringUtil.castTolong(currentOrg.getId()));
        form.setUnitId(StringUtil.castTolong(currentDept.getId()));

        return crbContractReimburseBillMapper.findBorrowPrepayList(form);
    }



    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<CrbContractReimburseBill> getQueryWrapper(QueryWrapper<CrbContractReimburseBill> queryWrapper,CrbContractReimburseBillQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(CrbContractReimburseBill.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(CrbContractReimburseBill.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(CrbContractReimburseBill.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(CrbContractReimburseBill.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(CrbContractReimburseBill.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(CrbContractReimburseBill.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentNum())){
            queryWrapper.eq(CrbContractReimburseBill.DOCUMENT_NUM,queryForm.getDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentStatus())){
            queryWrapper.eq(CrbContractReimburseBill.DOCUMENT_STATUS,queryForm.getDocumentStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getPayStatus())){
            queryWrapper.eq(CrbContractReimburseBill.PAY_STATUS,queryForm.getPayStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitId())){
            queryWrapper.eq(CrbContractReimburseBill.UNIT_ID,queryForm.getUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptId())){
            queryWrapper.eq(CrbContractReimburseBill.DEPT_ID,queryForm.getDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(CrbContractReimburseBill.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getMainTypeId())){
            queryWrapper.eq(CrbContractReimburseBill.MAIN_TYPE_ID,queryForm.getMainTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalSum())){
            queryWrapper.eq(CrbContractReimburseBill.TOTAL_SUM,queryForm.getTotalSum());
        }
        if(StringUtils.isNotBlank(queryForm.getCurrencyCode())){
            queryWrapper.eq(CrbContractReimburseBill.CURRENCY_CODE,queryForm.getCurrencyCode());
        }
        if(StringUtils.isNotBlank(queryForm.getIsAgreedPromis())){
            queryWrapper.eq(CrbContractReimburseBill.IS_AGREED_PROMIS,queryForm.getIsAgreedPromis());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(CrbContractReimburseBill.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitName())){
            queryWrapper.eq(CrbContractReimburseBill.UNIT_NAME,queryForm.getUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(CrbContractReimburseBill.DEPT_NAME,queryForm.getDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getMainTypeName())){
            queryWrapper.eq(CrbContractReimburseBill.MAIN_TYPE_NAME,queryForm.getMainTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.eq(CrbContractReimburseBill.PROJECT_NAME,queryForm.getProjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getPaymentType())){
            queryWrapper.eq(CrbContractReimburseBill.PAYMENT_TYPE,queryForm.getPaymentType());
        }
        if(StringUtils.isNotBlank(queryForm.getSupplier())){
            queryWrapper.eq(CrbContractReimburseBill.SUPPLIER,queryForm.getSupplier());
        }
        if(StringUtils.isNotBlank(queryForm.getSupplierId())){
            queryWrapper.eq(CrbContractReimburseBill.SUPPLIER_ID,queryForm.getSupplierId());
        }
        if(StringUtils.isNotBlank(queryForm.getContactNumberId())){
            queryWrapper.eq(CrbContractReimburseBill.CONTACT_NUMBER_ID,queryForm.getContactNumberId());
        }
        if(StringUtils.isNotBlank(queryForm.getContactNumber())){
            queryWrapper.eq(CrbContractReimburseBill.CONTACT_NUMBER,queryForm.getContactNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getSupportFileNum())){
            queryWrapper.eq(CrbContractReimburseBill.SUPPORT_FILE_NUM,queryForm.getSupportFileNum());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(CrbContractReimburseBill.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(CrbContractReimburseBill.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(CrbContractReimburseBill.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(CrbContractReimburseBill.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(CrbContractReimburseBill.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getExt6())){
            queryWrapper.eq(CrbContractReimburseBill.EXT6,queryForm.getExt6());
        }
        if(StringUtils.isNotBlank(queryForm.getExt7())){
            queryWrapper.eq(CrbContractReimburseBill.EXT7,queryForm.getExt7());
        }
        if(StringUtils.isNotBlank(queryForm.getExt8())){
            queryWrapper.eq(CrbContractReimburseBill.EXT8,queryForm.getExt8());
        }
        if(StringUtils.isNotBlank(queryForm.getExt9())){
            queryWrapper.eq(CrbContractReimburseBill.EXT9,queryForm.getExt9());
        }
        if(StringUtils.isNotBlank(queryForm.getExt10())){
            queryWrapper.eq(CrbContractReimburseBill.EXT10,queryForm.getExt10());
        }
        if(StringUtils.isNotBlank(queryForm.getExt11())){
            queryWrapper.eq(CrbContractReimburseBill.EXT11,queryForm.getExt11());
        }
        if(StringUtils.isNotBlank(queryForm.getExt12())){
            queryWrapper.eq(CrbContractReimburseBill.EXT12,queryForm.getExt12());
        }
        if(StringUtils.isNotBlank(queryForm.getExt13())){
            queryWrapper.eq(CrbContractReimburseBill.EXT13,queryForm.getExt13());
        }
        if(StringUtils.isNotBlank(queryForm.getExt14())){
            queryWrapper.eq(CrbContractReimburseBill.EXT14,queryForm.getExt14());
        }
        if(StringUtils.isNotBlank(queryForm.getExt15())){
            queryWrapper.eq(CrbContractReimburseBill.EXT15,queryForm.getExt15());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitCode())){
            queryWrapper.eq(CrbContractReimburseBill.UNIT_CODE,queryForm.getUnitCode());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptCode())){
            queryWrapper.eq(CrbContractReimburseBill.DEPT_CODE,queryForm.getDeptCode());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCode())){
            queryWrapper.eq(CrbContractReimburseBill.PROJECT_CODE,queryForm.getProjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getSupplierCode())){
            queryWrapper.eq(CrbContractReimburseBill.SUPPLIER_CODE,queryForm.getSupplierCode());
        }
        if(StringUtils.isNotBlank(queryForm.getMainTypeCode())){
            queryWrapper.eq(CrbContractReimburseBill.MAIN_TYPE_CODE,queryForm.getMainTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getContactName())){
            queryWrapper.eq(CrbContractReimburseBill.CONTACT_NAME,queryForm.getContactName());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalAmountReimburse())){
            queryWrapper.eq(CrbContractReimburseBill.TOTAL_AMOUNT_REIMBURSE,queryForm.getTotalAmountReimburse());
        }
        if(StringUtils.isNotBlank(queryForm.getVerAmount())){
            queryWrapper.eq(CrbContractReimburseBill.VER_AMOUNT,queryForm.getVerAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getActualPaymentAmount())){
            queryWrapper.eq(CrbContractReimburseBill.ACTUAL_PAYMENT_AMOUNT,queryForm.getActualPaymentAmount());
        }
        return queryWrapper;
    }
     */
}

