package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.BasicInfoQueryForm;
import com.deloitte.platform.api.contract.param.ProcessTaskQueryForm;
import com.deloitte.platform.api.contract.vo.*;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.contract.vo.*;
import com.deloitte.platform.api.fssc.basecontract.feign.FsscBaseContractInfoFeignService;
import com.deloitte.platform.api.isump.UserClient;
import com.deloitte.platform.api.isump.feign.RoleFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.RoleVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.common.util.*;
import com.deloitte.services.contract.entity.*;
import com.deloitte.services.contract.mapper.*;
import com.deloitte.services.contract.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.entity.ContentType;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  BasicInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BasicInfoServiceImpl extends ServiceImpl<BasicInfoMapper, BasicInfo> implements IBasicInfoService {

    @Autowired
    private BasicInfoMapper basicInfoMapper;
    @Autowired
    private BasicAttamentMapMapper basicAttamentMapMapper;
    @Autowired
    private BasicSubjectMapMapper basicSubjectMapMapper;
    @Autowired
    private SysSignSubjectInfoMapper sysSignSubjectInfoMapper;
    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;
    @Autowired
    private ISysSignSubjectInfoService iSysSignSubjectInfoService;
    @Autowired
    private ISysSignSubjectInfoService sysSignSubjectInfoService;
    @Autowired
    private FsscBaseContractInfoFeignService fsscBaseContractInfoFeignService;
    @Autowired
    private UserFeignService userClient;
    @Autowired
    public ICommonService commonService;
    @Autowired
    public ProcessTaskMapper processTaskMapper;
    @Autowired
    public SysDictMapper sysDictMapper;
    @Autowired
    public IFinanceInfoService iFinanceInfoService;
    @Autowired
    public RoleFeignService roleFeignService;
    @Autowired
    public BasicFinanceMapMapper basicFinanceMapMapper;
    @Autowired
    public BasicTicketMapMapper basicTicketMapMapper;
    @Autowired
    public BasicProjectMapMapper basicProjectMapMapper;
    @Autowired
    public BasicMonitorMapMapper basicMonitorMapMapper;
    @Autowired
    public BasicOrderMapMapper basicOrderMapMapper;
    @Autowired
    public ISysWatermarkService  sysWatermarkService;

    @Override
    public IPage<BasicInfo> selectPage(BasicInfoQueryForm queryForm,UserVo userVo ) {
        QueryWrapper<BasicInfo> queryWrapper = new QueryWrapper <BasicInfo>();
        getQueryWrapper(queryWrapper,queryForm,userVo);
        IPage<BasicInfo> basicInfobPage = basicInfoMapper.selectPage(new Page<BasicInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
        List<BasicInfo> basicInfobList = basicInfobPage.getRecords();
        basicInfobList = shrinkBasicInfoAmountList(basicInfobList);
        basicInfobPage.setRecords(basicInfobList);
        return basicInfobPage;
    }

    @Override
    public List<BasicInfo> selectList(BasicInfoQueryForm queryForm) {
        QueryWrapper<BasicInfo> queryWrapper = new QueryWrapper <BasicInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return basicInfoMapper.selectList(queryWrapper);
    }
    //    草稿箱查询
    public List<BasicInfo> queryLineInfo(BasicInfoQueryForm base){
//        List<BasicInfo> info=basicInfoMapper.queryLineInfo(base);
//        int total=base.getTotal();
//        int infoTotal=info.size();
//        int pageTotal=base.getPageTotal();
//        int total1 =(int) Math.ceil(infoTotal/pageTotal);
//        int start=(total-1)*pageTotal+1;
//        base.setStart(start);
//        base.setEnd(pageTotal);
//        Map<String,List> map=new HashMap<String,List>();
//        map.put("BasicInfoQueryForm",basicInfoMapper.queryLineInfo(base));
//        List list=new ArrayList();
//        list.add(total1);
//        map.put("total",list);
        return  basicInfoMapper.queryLineInfo(base);

    }
    public  BasicInfo queryCopyInfo() {
        BasicInfo basicInfo=new BasicInfo();
        return basicInfo;
    }

    @Override
    public BasicInfoQueryForm queryCurrentBasic(String contractId) {
        return basicInfoMapper.queryCurrentBasic(contractId);
    }

    @Override
    public BasicInfoVo queryCorrelationBasic(BasicInfoVo basicInfoVo) {
        String contractId = basicInfoVo.getId();
        List<BasicInfoVo> basicInfoVos= basicInfoMapper.queryCorrelationBasic(contractId);
        String contractNo = "";
        String contractName = "";
        if (basicInfoVos != null && basicInfoVos.size() > 0){
            String relationCode = basicInfoVos.get(0).getRelationCode();
            Collections.reverse(basicInfoVos);
            contractNo = basicInfoVos.get(0).getContractNo();
            contractName = basicInfoVos.get(0).getContractName();
            if (relationCode != null && relationCode.equals("REL3000")){
                int size = basicInfoVos.size();
                basicInfoVos.remove(size - 1);
            }else{
                basicInfoVos.remove(0);
                basicInfoVo.setContractNo(contractNo);
                basicInfoVo.setContractName(contractName);
                basicInfoVo.setListBasicInfoVo(basicInfoVos);
            }
        }else {
            AssertUtils.asserts(true, ContractErrorType.CONTRACT_IS_NULL);
        }
        return basicInfoVo;
    }

    @Override
    public int updateContractStatue(String contractId) {
        return basicInfoMapper.updateContractStatue(contractId);
    }

    //保存复制合同信息
    public BasicInfo InsertCopyInfo(String contractNumId,UserVo user, OrganizationVo organizationVo){
        BasicInfo basicInfo=basicInfoMapper.queryCopyInfo(contractNumId);
        BasicInfo basicInfo1=shrinkBasicInfoAmount(basicInfo);
        basicInfo1.setContractName(basicInfo1.getContractName()+"【复制】");
        ArrayList<BasicSubjectMapForm> list=basicInfoMapper.queryCopySubject(contractNumId);
        BasicInfoForm basicInfoForm=new BeanUtils<BasicInfoForm>().copyObj(basicInfo1,BasicInfoForm.class);
        basicInfoForm.setBasicSubjectList(list);
        BasicInfo info=addContractInfo(basicInfoForm,user,organizationVo);
        String contractId=info.getId().toString();
        BasicInfo copyBasicInfo=basicInfoMapper.queryCopyInfoId(contractId);
        return shrinkBasicInfoAmount(copyBasicInfo);

    }


    /**
     *  草稿箱查询条件
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<BasicInfo> getQueryWrapper(QueryWrapper<BasicInfo> queryWrapper,BasicInfoQueryForm queryForm,UserVo userVo){
        //条件拼接
        //合同名称
        if(StringUtils.isNotBlank(queryForm.getContractName())){
            String contractName = queryForm.getContractName();
            queryWrapper.and(wrapper -> wrapper.like(BasicInfo.CONTRACT_NAME, contractName).or().like(BasicInfo.CONTRACT_NO, contractName));
        }
        //签约主体
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.like("SUBJECT_NAME",queryForm.getSpareField1());
        }
        //合同类型
        if(StringUtils.isNotBlank(queryForm.getContractTypeCode())){
            queryWrapper.eq(BasicInfo.CONTRACT_TYPE_CODE,queryForm.getContractTypeCode());
        }
        //收支类型
        if(StringUtils.isNotBlank(queryForm.getIncomeExpenditureTypeCode())){
            queryWrapper.eq(BasicInfo.INCOME_EXPENDITURE_TYPE_CODE,queryForm.getIncomeExpenditureTypeCode());
        }
        //合同页面显示
        if(StringUtils.isNotBlank(queryForm.getStatueList())){
            queryWrapper.eq(BasicInfo.STATUE_LIST,queryForm.getStatueList());
        }
        //合同页面显示
        if(StringUtils.isNotBlank(queryForm.getOperatorCode())){
            queryWrapper.eq(BasicInfo.OPERATOR_CODE,queryForm.getOperatorCode());
        }
        if(StringUtils.isNotBlank(queryForm.getExecuterCode())){
            queryWrapper.eq(BasicInfo.EXECUTER_CODE,queryForm.getExecuterCode());
        }
        //起草时间开始
        if(null!=queryForm.getSpareField4()){
            queryWrapper.ge(BasicInfo.CREATE_TIME,queryForm.getSpareField4());
        }
        //起草时间结束
        if(null!=queryForm.getUpdateTime()){
            queryWrapper.le(BasicInfo.CREATE_TIME,queryForm.getUpdateTime());
        }
        //合同状态
        if(null!=queryForm.getStatue() && !"".equals(queryForm.getStatue())){
            String[] statues = queryForm.getStatue().split(",");
            queryWrapper.in(BasicInfo.STATUE, statues);
//            queryWrapper.eq(BasicInfo.STATUE,queryForm.getStatue());
        }
        //查询列表状态
        if(null!=queryForm.getStatueList()){
            String[] statues = queryForm.getStatueList().split(",");
            queryWrapper.in(BasicInfo.STATUE_LIST, statues);
//            queryWrapper.eq(BasicInfo.STATUE_LIST,queryForm.getStatueList());
        }
        //定稿状态
        if(null!=queryForm.getSureStatue() && !"".equals(queryForm.getSureStatue())){
            queryWrapper.eq(BasicInfo.SURE_STATUE,queryForm.getSureStatue());

        }
        //是否已经进行合同打印
        if(StringUtils.isNotBlank(queryForm.getIsPrint())){
            queryWrapper.isNotNull(BasicInfo.PRINT_STATUE);
        }
        //合同打印状态
        if(StringUtils.isNotBlank(queryForm.getPrintStatue())){
            queryWrapper.eq(BasicInfo.PRINT_STATUE,queryForm.getPrintStatue());
        }
        //签署状态
        if(StringUtils.isNotBlank(queryForm.getSignStatue())){
            queryWrapper.eq(BasicInfo.SIGN_STATUE,queryForm.getSignStatue());
        }
        //生效时间
        if(null!=queryForm.getValidTimeMin()){
            queryWrapper.ge(BasicInfo.VALID_TIME,queryForm.getValidTimeMin());
        }
        if(null!=queryForm.getValidTimeMax()){
            queryWrapper.le(BasicInfo.VALID_TIME,queryForm.getValidTimeMax());
        }

        //审批时间
        if(null!=queryForm.getStatueTimeMin()){
            queryWrapper.ge(BasicInfo.STATUE_TIME,queryForm.getStatueTimeMin());
        }
        if(null!=queryForm.getStatueTimeMax()){
            queryWrapper.le(BasicInfo.STATUE_TIME,queryForm.getStatueTimeMax());
        }
        //定稿时间
        if(null!=queryForm.getSureStatueTimeMin()){
            queryWrapper.ge(BasicInfo.SURE_STATUE_TIME,queryForm.getSureStatueTimeMin());
        }
        if(null!=queryForm.getSureStatueTimeMax()){
            queryWrapper.le(BasicInfo.SURE_STATUE_TIME,queryForm.getSureStatueTimeMax());
        }
        //签署时间
        if(null!=queryForm.getSignStatueTimeMin()){
            queryWrapper.ge(BasicInfo.SIGN_STATUE_TIME,queryForm.getSignStatueTimeMin());
        }
        if(null!=queryForm.getSignStatueTimeMax()){
            queryWrapper.le(BasicInfo.SIGN_STATUE_TIME,queryForm.getSignStatueTimeMax());
        }
        //履行时间
        if(null!=queryForm.getExecuteStatueTimeMin()){
            queryWrapper.ge(BasicInfo.EXECUTE_STATUE_TIME,queryForm.getExecuteStatueTimeMin());
        }
        if(null!=queryForm.getExecuteStatueTimeMax()){
            queryWrapper.le(BasicInfo.EXECUTE_STATUE_TIME,queryForm.getExecuteStatueTimeMax());
        }
        //合同评价状态
        if(StringUtils.isNotBlank(queryForm.getEvaluateStatue())){
            queryWrapper.eq(BasicInfo.EVALUATE_STATUE,queryForm.getEvaluateStatue());
        }
//        queryWrapper.orderByDesc(BasicInfo.UPDATE_TIME);
        return queryWrapper;
    }
    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<BasicInfo> getQueryWrapper(QueryWrapper<BasicInfo> queryWrapper,BasicInfoQueryForm queryForm){
    //条件拼接
    if(StringUtils.isNotBlank(queryForm.getContractNo())){
    queryWrapper.eq(BasicInfo.CONTRACT_NO,queryForm.getContractNo());
    }
    if(StringUtils.isNotBlank(queryForm.getContractName())){
    queryWrapper.eq(BasicInfo.CONTRACT_NAME,queryForm.getContractName());
    }
    if(StringUtils.isNotBlank(queryForm.getOldContractNo())){
    queryWrapper.eq(BasicInfo.OLD_CONTRACT_NO,queryForm.getOldContractNo());
    }
    if(StringUtils.isNotBlank(queryForm.getOldContractName())){
    queryWrapper.eq(BasicInfo.OLD_CONTRACT_NAME,queryForm.getOldContractName());
    }
    if(StringUtils.isNotBlank(queryForm.getContractSerialNumber())){
    queryWrapper.eq(BasicInfo.CONTRACT_SERIAL_NUMBER,queryForm.getContractSerialNumber());
    }
    if(StringUtils.isNotBlank(queryForm.getIncomeExpenditureTypeCode())){
    queryWrapper.eq(BasicInfo.INCOME_EXPENDITURE_TYPE_CODE,queryForm.getIncomeExpenditureTypeCode());
    }
    if(StringUtils.isNotBlank(queryForm.getIncomeExpenditureType())){
    queryWrapper.eq(BasicInfo.INCOME_EXPENDITURE_TYPE,queryForm.getIncomeExpenditureType());
    }
    if(StringUtils.isNotBlank(queryForm.getContractTypeCode())){
    queryWrapper.eq(BasicInfo.CONTRACT_TYPE_CODE,queryForm.getContractTypeCode());
    }
    if(StringUtils.isNotBlank(queryForm.getContractType())){
    queryWrapper.eq(BasicInfo.CONTRACT_TYPE,queryForm.getContractType());
    }
    if(StringUtils.isNotBlank(queryForm.getContractNatureCode())){
    queryWrapper.eq(BasicInfo.CONTRACT_NATURE_CODE,queryForm.getContractNatureCode());
    }
    if(StringUtils.isNotBlank(queryForm.getContractNature())){
    queryWrapper.eq(BasicInfo.CONTRACT_NATURE,queryForm.getContractNature());
    }
    if(StringUtils.isNotBlank(queryForm.getTemplateAttributeCode())){
    queryWrapper.eq(BasicInfo.TEMPLATE_ATTRIBUTE_CODE,queryForm.getTemplateAttributeCode());
    }
    if(StringUtils.isNotBlank(queryForm.getTemplateAttribute())){
    queryWrapper.eq(BasicInfo.TEMPLATE_ATTRIBUTE,queryForm.getTemplateAttribute());
    }
    if(StringUtils.isNotBlank(queryForm.getTemplateAttributeRemark())){
    queryWrapper.eq(BasicInfo.TEMPLATE_ATTRIBUTE_REMARK,queryForm.getTemplateAttributeRemark());
    }
    if(StringUtils.isNotBlank(queryForm.getTemplateCode())){
    queryWrapper.eq(BasicInfo.TEMPLATE_CODE,queryForm.getTemplateCode());
    }
    if(StringUtils.isNotBlank(queryForm.getTemplateName())){
    queryWrapper.eq(BasicInfo.TEMPLATE_NAME,queryForm.getTemplateName());
    }
    if(StringUtils.isNotBlank(queryForm.getContractCurrencyCode())){
    queryWrapper.eq(BasicInfo.CONTRACT_CURRENCY_CODE,queryForm.getContractCurrencyCode());
    }
    if(StringUtils.isNotBlank(queryForm.getContractCurrency())){
    queryWrapper.eq(BasicInfo.CONTRACT_CURRENCY,queryForm.getContractCurrency());
    }
    if(StringUtils.isNotBlank(queryForm.getContractCurrencyRemark())){
    queryWrapper.eq(BasicInfo.CONTRACT_CURRENCY_REMARK,queryForm.getContractCurrencyRemark());
    }
    if(StringUtils.isNotBlank(queryForm.getOtherWayCode())){
    queryWrapper.eq(BasicInfo.OTHER_WAY_CODE,queryForm.getOtherWayCode());
    }
    if(StringUtils.isNotBlank(queryForm.getOtherWay())){
    queryWrapper.eq(BasicInfo.OTHER_WAY,queryForm.getOtherWay());
    }
    if(StringUtils.isNotBlank(queryForm.getOtherWayRemark())){
    queryWrapper.eq(BasicInfo.OTHER_WAY_REMARK,queryForm.getOtherWayRemark());
    }
    if(StringUtils.isNotBlank(queryForm.getSourcesFunding())){
    queryWrapper.eq(BasicInfo.SOURCES_FUNDING,queryForm.getSourcesFunding());
    }
    if(StringUtils.isNotBlank(queryForm.getPaymentWayCode())){
    queryWrapper.eq(BasicInfo.PAYMENT_WAY_CODE,queryForm.getPaymentWayCode());
    }
    if(StringUtils.isNotBlank(queryForm.getPaymentWay())){
    queryWrapper.eq(BasicInfo.PAYMENT_WAY,queryForm.getPaymentWay());
    }
    if(StringUtils.isNotBlank(queryForm.getPaymentWayRemark())){
    queryWrapper.eq(BasicInfo.PAYMENT_WAY_REMARK,queryForm.getPaymentWayRemark());
    }
    if(StringUtils.isNotBlank(queryForm.getBillingRatio())){
    queryWrapper.eq(BasicInfo.BILLING_RATIO,queryForm.getBillingRatio());
    }
    if(StringUtils.isNotBlank(queryForm.getOldAmountIncludTax())){
    queryWrapper.eq(BasicInfo.OLD_AMOUNT_INCLUD_TAX,queryForm.getOldAmountIncludTax());
    }
    if(StringUtils.isNotBlank(queryForm.getOldTaxRate())){
    queryWrapper.eq(BasicInfo.OLD_TAX_RATE,queryForm.getOldTaxRate());
    }
    if(StringUtils.isNotBlank(queryForm.getOldTaxAmount())){
    queryWrapper.eq(BasicInfo.OLD_TAX_AMOUNT,queryForm.getOldTaxAmount());
    }
    if(StringUtils.isNotBlank(queryForm.getOldAmountExcludTax())){
    queryWrapper.eq(BasicInfo.OLD_AMOUNT_EXCLUD_TAX,queryForm.getOldAmountExcludTax());
    }
    if(StringUtils.isNotBlank(queryForm.getOldAmountExcludTaxCapital())){
    queryWrapper.eq(BasicInfo.OLD_AMOUNT_EXCLUD_TAX_CAPITAL,queryForm.getOldAmountExcludTaxCapital());
    }
    if(StringUtils.isNotBlank(queryForm.getReportedAmount())){
    queryWrapper.eq(BasicInfo.REPORTED_AMOUNT,queryForm.getReportedAmount());
    }
    if(StringUtils.isNotBlank(queryForm.getPaidAmount())){
    queryWrapper.eq(BasicInfo.PAID_AMOUNT,queryForm.getPaidAmount());
    }
    if(StringUtils.isNotBlank(queryForm.getAmountIncludTax())){
    queryWrapper.eq(BasicInfo.AMOUNT_INCLUD_TAX,queryForm.getAmountIncludTax());
    }
    if(StringUtils.isNotBlank(queryForm.getTaxRate())){
    queryWrapper.eq(BasicInfo.TAX_RATE,queryForm.getTaxRate());
    }
    if(StringUtils.isNotBlank(queryForm.getTaxRateRemark())){
    queryWrapper.eq(BasicInfo.TAX_RATE_REMARK,queryForm.getTaxRateRemark());
    }
    if(StringUtils.isNotBlank(queryForm.getTaxAmount())){
    queryWrapper.eq(BasicInfo.TAX_AMOUNT,queryForm.getTaxAmount());
    }
    if(StringUtils.isNotBlank(queryForm.getAmountExcludTax())){
    queryWrapper.eq(BasicInfo.AMOUNT_EXCLUD_TAX,queryForm.getAmountExcludTax());
    }
    if(StringUtils.isNotBlank(queryForm.getAmountExcludTaxCapital())){
    queryWrapper.eq(BasicInfo.AMOUNT_EXCLUD_TAX_CAPITAL,queryForm.getAmountExcludTaxCapital());
    }
    if(StringUtils.isNotBlank(queryForm.getIsPayDepositCode())){
    queryWrapper.eq(BasicInfo.IS_PAY_DEPOSIT_CODE,queryForm.getIsPayDepositCode());
    }
    if(StringUtils.isNotBlank(queryForm.getIsPayDeposit())){
    queryWrapper.eq(BasicInfo.IS_PAY_DEPOSIT,queryForm.getIsPayDeposit());
    }
    if(StringUtils.isNotBlank(queryForm.getContractDeposit())){
    queryWrapper.eq(BasicInfo.CONTRACT_DEPOSIT,queryForm.getContractDeposit());
    }
    if(StringUtils.isNotBlank(queryForm.getPerformanceEffectiveTime())){
    queryWrapper.eq(BasicInfo.PERFORMANCE_EFFECTIVE_TIME,queryForm.getPerformanceEffectiveTime());
    }
    if(StringUtils.isNotBlank(queryForm.getIsForeignContract())){
    queryWrapper.eq(BasicInfo.IS_FOREIGN_CONTRACT,queryForm.getIsForeignContract());
    }
    if(StringUtils.isNotBlank(queryForm.getOperatorCode())){
    queryWrapper.eq(BasicInfo.OPERATOR_CODE,queryForm.getOperatorCode());
    }
    if(StringUtils.isNotBlank(queryForm.getOperator())){
    queryWrapper.eq(BasicInfo.OPERATOR,queryForm.getOperator());
    }
    if(StringUtils.isNotBlank(queryForm.getOldOperatorCode())){
    queryWrapper.eq(BasicInfo.OLD_OPERATOR_CODE,queryForm.getOldOperatorCode());
    }
    if(StringUtils.isNotBlank(queryForm.getOldOperator())){
    queryWrapper.eq(BasicInfo.OLD_OPERATOR,queryForm.getOldOperator());
    }
    if(StringUtils.isNotBlank(queryForm.getOrgCode())){
    queryWrapper.eq(BasicInfo.ORG_CODE,queryForm.getOrgCode());
    }
    if(StringUtils.isNotBlank(queryForm.getOrg())){
    queryWrapper.eq(BasicInfo.ORG,queryForm.getOrg());
    }
    if(StringUtils.isNotBlank(queryForm.getContactNum())){
    queryWrapper.eq(BasicInfo.CONTACT_NUM,queryForm.getContactNum());
    }
    if(StringUtils.isNotBlank(queryForm.getOldOrgCode())){
    queryWrapper.eq(BasicInfo.OLD_ORG_CODE,queryForm.getOldOrgCode());
    }
    if(StringUtils.isNotBlank(queryForm.getOldOrg())){
    queryWrapper.eq(BasicInfo.OLD_ORG,queryForm.getOldOrg());
    }
    if(StringUtils.isNotBlank(queryForm.getOldContactNum())){
    queryWrapper.eq(BasicInfo.OLD_CONTACT_NUM,queryForm.getOldContactNum());
    }
    if(StringUtils.isNotBlank(queryForm.getExecuteInfoCode())){
    queryWrapper.eq(BasicInfo.EXECUTE_INFO_CODE,queryForm.getExecuteInfoCode());
    }
    if(StringUtils.isNotBlank(queryForm.getExecuteInfo())){
    queryWrapper.eq(BasicInfo.EXECUTE_INFO,queryForm.getExecuteInfo());
    }
    if(StringUtils.isNotBlank(queryForm.getExecuteInfoRemark())){
    queryWrapper.eq(BasicInfo.EXECUTE_INFO_REMARK,queryForm.getExecuteInfoRemark());
    }
    if(StringUtils.isNotBlank(queryForm.getExecuteCause())){
    queryWrapper.eq(BasicInfo.EXECUTE_CAUSE,queryForm.getExecuteCause());
    }
    if(StringUtils.isNotBlank(queryForm.getExecuteStartTime())){
    queryWrapper.eq(BasicInfo.EXECUTE_START_TIME,queryForm.getExecuteStartTime());
    }
    if(StringUtils.isNotBlank(queryForm.getExecuteEndTime())){
    queryWrapper.eq(BasicInfo.EXECUTE_END_TIME,queryForm.getExecuteEndTime());
    }
    if(StringUtils.isNotBlank(queryForm.getContractBody())){
    queryWrapper.eq(BasicInfo.CONTRACT_BODY,queryForm.getContractBody());
    }
    if(StringUtils.isNotBlank(queryForm.getContractAttament())){
    queryWrapper.eq(BasicInfo.CONTRACT_ATTAMENT,queryForm.getContractAttament());
    }
    if(StringUtils.isNotBlank(queryForm.getContractStartupBasis())){
    queryWrapper.eq(BasicInfo.CONTRACT_STARTUP_BASIS,queryForm.getContractStartupBasis());
    }
    if(StringUtils.isNotBlank(queryForm.getSurePersonCode())){
    queryWrapper.eq(BasicInfo.SURE_PERSON_CODE,queryForm.getSurePersonCode());
    }
    if(StringUtils.isNotBlank(queryForm.getSurePerson())){
    queryWrapper.eq(BasicInfo.SURE_PERSON,queryForm.getSurePerson());
    }
    if(StringUtils.isNotBlank(queryForm.getExecuterCode())){
    queryWrapper.eq(BasicInfo.EXECUTER_CODE,queryForm.getExecuterCode());
    }
    if(StringUtils.isNotBlank(queryForm.getExecuter())){
    queryWrapper.eq(BasicInfo.EXECUTER,queryForm.getExecuter());
    }
    if(StringUtils.isNotBlank(queryForm.getOldExecutePersonCode())){
    queryWrapper.eq(BasicInfo.OLD_EXECUTE_PERSON_CODE,queryForm.getOldExecutePersonCode());
    }
    if(StringUtils.isNotBlank(queryForm.getOldExecutePerson())){
    queryWrapper.eq(BasicInfo.OLD_EXECUTE_PERSON,queryForm.getOldExecutePerson());
    }
    if(StringUtils.isNotBlank(queryForm.getCreateTime())){
    queryWrapper.eq(BasicInfo.CREATE_TIME,queryForm.getCreateTime());
    }
    if(StringUtils.isNotBlank(queryForm.getCreateBy())){
    queryWrapper.eq(BasicInfo.CREATE_BY,queryForm.getCreateBy());
    }
    if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
    queryWrapper.eq(BasicInfo.UPDATE_TIME,queryForm.getUpdateTime());
    }
    if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
    queryWrapper.eq(BasicInfo.UPDATE_BY,queryForm.getUpdateBy());
    }
    if(StringUtils.isNotBlank(queryForm.getDisabledTime())){
    queryWrapper.eq(BasicInfo.DISABLED_TIME,queryForm.getDisabledTime());
    }
    if(StringUtils.isNotBlank(queryForm.getDisabledBy())){
    queryWrapper.eq(BasicInfo.DISABLED_BY,queryForm.getDisabledBy());
    }
    if(StringUtils.isNotBlank(queryForm.getStatue())){
    queryWrapper.eq(BasicInfo.STATUE,queryForm.getStatue());
    }
    if(StringUtils.isNotBlank(queryForm.getSureStatue())){
    queryWrapper.eq(BasicInfo.SURE_STATUE,queryForm.getSureStatue());
    }
    if(StringUtils.isNotBlank(queryForm.getSignStatue())){
    queryWrapper.eq(BasicInfo.SIGN_STATUE,queryForm.getSignStatue());
    }
    if(StringUtils.isNotBlank(queryForm.getExecuteStatue())){
    queryWrapper.eq(BasicInfo.EXECUTE_STATUE,queryForm.getExecuteStatue());
    }
    if(StringUtils.isNotBlank(queryForm.getFinishStatue())){
    queryWrapper.eq(BasicInfo.FINISH_STATUE,queryForm.getFinishStatue());
    }
    if(StringUtils.isNotBlank(queryForm.getSpareField1())){
    queryWrapper.eq(BasicInfo.SPARE_FIELD_1,queryForm.getSpareField1());
    }
    if(StringUtils.isNotBlank(queryForm.getSpareField2())){
    queryWrapper.eq(BasicInfo.SPARE_FIELD_2,queryForm.getSpareField2());
    }
    if(StringUtils.isNotBlank(queryForm.getSpareField3())){
    queryWrapper.eq(BasicInfo.SPARE_FIELD_3,queryForm.getSpareField3());
    }
    if(StringUtils.isNotBlank(queryForm.getSpareField4())){
    queryWrapper.eq(BasicInfo.SPARE_FIELD_4,queryForm.getSpareField4());
    }
    if(StringUtils.isNotBlank(queryForm.getSpareField5())){
    queryWrapper.eq(BasicInfo.SPARE_FIELD_5,queryForm.getSpareField5());
    }
    if(StringUtils.isNotBlank(queryForm.getRelationCode())){
    queryWrapper.eq(BasicInfo.RELATION_CODE,queryForm.getRelationCode());
    }
    if(StringUtils.isNotBlank(queryForm.getRelation())){
    queryWrapper.eq(BasicInfo.RELATION,queryForm.getRelation());
    }
    return queryWrapper;
    }
     */


    /**
     * yangyuanqing
     * @param basicInfoForm 传入表单信息
     * @return
     */
    public BasicInfo addContractInfo(BasicInfoForm basicInfoForm, UserVo userVo, OrganizationVo organizationVo){
        BasicInfo basicInfo =new BeanUtils<BasicInfo>().copyObj(basicInfoForm,BasicInfo.class);
        List<BasicAttamentMapForm> basicAttamentList = basicInfoForm.getBasicAttamentList();
        List<BasicSubjectMapForm> basicSubjectMapList = basicInfoForm.getBasicSubjectList();
        if (null != basicInfo){
            Long contractId = basicInfo.getId();
            if (null != contractId && !"".equals(contractId.toString())){
                // 修改
                basicInfo.setUpdateBy(userVo.getId());
                basicInfo = enlargeBasicInfoAmount(basicInfo);
                basicInfo.setOperator(userVo.getName());
                basicInfo.setOperatorCode(userVo.getId());
                basicInfo.setOrg(organizationVo.getName());
                basicInfo.setOrgCode(organizationVo.getCode());
                basicInfo.setDept(userVo.getDeptName());
                basicInfo.setDeptCode(userVo.getDept());
                basicInfoMapper.updateById(basicInfo);
                //签约主体
                // 需要先删除该类型对应签约主体
                QueryWrapper<BasicSubjectMap> queryWrapperSub = new QueryWrapper<>();
                queryWrapperSub.eq(BasicSubjectMap.CONTRACT_ID, contractId);
                basicSubjectMapMapper.delete(queryWrapperSub);
                if (basicSubjectMapList != null && basicSubjectMapList.size() > 0){
                    for (BasicSubjectMapForm basicSubjectFrom:basicSubjectMapList) {
                        BasicSubjectMap basicSubjectMap =new BeanUtils<BasicSubjectMap>().copyObj(basicSubjectFrom,BasicSubjectMap.class);
                        // 新增签约主体信息
                        basicSubjectMap.setCreateBy(userVo.getId());
                        basicSubjectMap.setContractId(contractId);
                        basicSubjectMapMapper.insert(basicSubjectMap);
                    }
                }
                // 关联文件
                // 需要先删除该类型对应文件
                QueryWrapper<BasicAttamentMap> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(BasicAttamentMap.CONTRACT_ID, contractId);
                queryWrapper.in(BasicAttamentMap.FILE_TYPE, "FIL1000","FIL2000","FIL3000");
                basicAttamentMapMapper.delete(queryWrapper);
                if (basicAttamentList != null && basicAttamentList.size() > 0){
                    for (BasicAttamentMapForm basicAttamentForm:basicAttamentList) {
                        BasicAttamentMap basicAttamentMap =new BeanUtils<BasicAttamentMap>().copyObj(basicAttamentForm,BasicAttamentMap.class);
                        // 新增关联文件
                        basicAttamentMap.setCreateBy(userVo.getId());
                        basicAttamentMap.setContractId(contractId);
                        basicAttamentMapMapper.insert(basicAttamentMap);
                    }
                }
            }else{
                //新增
                String relation = basicInfo.getRelation();
                if (relation != null && !relation.equals("")){
                    basicInfo = initChangeEntity(basicInfo, userVo, organizationVo);
                }else {
                    basicInfo = initBasicInfoEntity(basicInfo, userVo, organizationVo);
                }
                basicInfo = enlargeBasicInfoAmount(basicInfo);
                basicInfoMapper.insert(basicInfo);
                contractId = basicInfo.getId();
                if (basicSubjectMapList != null && basicSubjectMapList.size() > 0){
                    for (BasicSubjectMapForm basicSubjectFrom:basicSubjectMapList) {
                        BasicSubjectMap basicSubjectMap =new BeanUtils<BasicSubjectMap>().copyObj(basicSubjectFrom,BasicSubjectMap.class);
                        basicSubjectMap.setContractId(contractId);
                        basicSubjectMap.setCreateBy(userVo.getId());
                        basicSubjectMapMapper.insert(basicSubjectMap);
                    }
                }
                if (basicAttamentList != null && basicAttamentList.size() > 0){
                    for (BasicAttamentMapForm basicAttamentForm:basicAttamentList) {
                        BasicAttamentMap basicAttamentMap =new BeanUtils<BasicAttamentMap>().copyObj(basicAttamentForm,BasicAttamentMap.class);
                        basicAttamentMap.setContractId(contractId);
                        basicAttamentMap.setCreateBy(userVo.getId());
                        basicAttamentMapMapper.insert(basicAttamentMap);
                    }
                }
            }
        }else{
            // 如果没有传入信息
            AssertUtils.asserts(true, ContractErrorType.No_FROM);
        }
        basicInfo = shrinkBasicInfoAmount(basicInfo);
        return basicInfo;
    }

    public BasicInfo selectContractInfoById(Long contractId){
        return shrinkBasicInfoAmount(basicInfoMapper.selectById(contractId));
    }

    public List<BasicAttamentMap> selectbasicAttamentByContractId(Long contractId){
        QueryWrapper<BasicAttamentMap> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BasicAttamentMap.CONTRACT_ID, contractId);
//        queryWrapper.in(BasicAttamentMap.FILE_TYPE, "FIL1000","FIL2000","FIL3000");
        return basicAttamentMapMapper.selectList(queryWrapper);
    }

    public List<BasicSubjectMap> selectBasicSubjectByContractId(Long contractId){
        QueryWrapper<BasicSubjectMap> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BasicSubjectMap.CONTRACT_ID, contractId);
        return basicSubjectMapMapper.selectList(queryWrapper);
    }

    public BasicInfo initBasicInfoEntity(BasicInfo basicInfo, UserVo userVo, OrganizationVo organizationVo){
        basicInfo.setContractNo("");
        basicInfo.setOldContractNo("");
        basicInfo.setOldContactNum("");
        basicInfo.setContractSerialNumber("");
        basicInfo.setOldAmountIncludTax(null);
        basicInfo.setOldTaxRate(null);
        basicInfo.setOldTaxRateValue("");
        basicInfo.setOldTaxAmount(null);
        basicInfo.setOldAmountExcludTax(null);
        basicInfo.setOldAmountExcludTaxCapital("");
        basicInfo.setReportedAmount(null);
        basicInfo.setPaidAmount(null);
        basicInfo.setOldOrgCode("");
        basicInfo.setOldOrg("");
        basicInfo.setOldContactNum("");
        basicInfo.setSurePersonCode("");
        basicInfo.setSurePerson("");
        basicInfo.setExecuterCode("");
        basicInfo.setExecuter("");
        basicInfo.setOldExecutePersonCode("");
        basicInfo.setOldExecutePerson("");
        // 创建时间
        basicInfo.setCreateTime(LocalDateTime.now());
        // 创建人
        basicInfo.setCreateBy(userVo.getId());
        basicInfo.setUpdateTime(null);
        basicInfo.setUpdateBy("");
        basicInfo.setDisabledTime(null);
        basicInfo.setDisabledBy("");
        // 状态暂定前端传入
        basicInfo.setStatue("EXA3000");
        basicInfo.setSureStatue("");
        basicInfo.setSignStatue("");
        basicInfo.setExecuteStatue("");
        basicInfo.setFinishStatue("");
        // 关联性质
        basicInfo.setRelationCode("REL1000");
        basicInfo.setRelation("原合同");
        basicInfo.setSpareField1("");
        basicInfo.setSpareField2("");
        basicInfo.setSpareField3("");
        basicInfo.setSpareField4(null);
        basicInfo.setSpareField5(null);
        basicInfo.setSureStatueTime(null);
        basicInfo.setSignStatueTime(null);
        basicInfo.setExecuteStatueTime(null);
        basicInfo.setFinishStatueTime(null);
        basicInfo.setStatueTime(null);
        basicInfo.setStatueList("STA1000");
        basicInfo.setIsImprint("N");
        //经办人
        basicInfo.setOperatorCode(userVo.getId());
        basicInfo.setOperator(userVo.getName());
        basicInfo.setContactNum(userVo.getPhone());
        basicInfo.setOrg(organizationVo.getName());
        basicInfo.setOrgCode(organizationVo.getCode());
        basicInfo.setDept(userVo.getDeptName());
        basicInfo.setDeptCode(userVo.getDept());
        return basicInfo;
    }

    /**
     * 初始化变更合同保存信息
     * @param basicInfo
     * @param userVo
     * @param organizationVo
     * @return
     */
    public BasicInfo initChangeEntity(BasicInfo basicInfo, UserVo userVo, OrganizationVo organizationVo){
        // 创建人
        basicInfo.setCreateBy(userVo.getId());
        // 状态
        basicInfo.setStatue("EXA3000");
        // 关联性质
        basicInfo.setStatueList("STA1000");
        basicInfo.setIsImprint("N");
        //经办人
        basicInfo.setOperatorCode(userVo.getId());
        basicInfo.setOperator(userVo.getName());
        basicInfo.setContactNum(userVo.getPhone());
        basicInfo.setOrg(organizationVo.getName());
        basicInfo.setOrgCode(organizationVo.getCode());
        basicInfo.setDept(userVo.getDeptName());
        basicInfo.setDeptCode(userVo.getDept());
        return basicInfo;
    }

    /**
     * 将传入的合同基础信息里的金额扩大100倍
     * @param basicInfo
     * @return
     */
    public BasicInfo enlargeBasicInfoAmount(BasicInfo basicInfo){
        AssertUtils.asserts(null == basicInfo,ContractErrorType.CONTRACT_IS_NULL);
        // 乘法
        BigDecimal multiply = new BigDecimal(100);
        // 原含税合同金额
        BigDecimal oldAmountIncludTax = basicInfo.getOldAmountIncludTax();
        if (oldAmountIncludTax != null){
            basicInfo.setOldAmountIncludTax(oldAmountIncludTax.multiply(multiply));
        }
        // 原税额
        BigDecimal oldTaxAmount = basicInfo.getOldTaxAmount();
        if (oldTaxAmount != null){
            basicInfo.setOldTaxAmount(oldTaxAmount.multiply(multiply));
        }
        // 原不含税合同金额
        BigDecimal oldAmountExcludTax = basicInfo.getOldAmountExcludTax();
        if (oldAmountExcludTax != null){
            basicInfo.setOldAmountExcludTax(oldAmountExcludTax.multiply(multiply));
        }
        // 已报账金额
        BigDecimal reportedAmount = basicInfo.getReportedAmount();
        if (reportedAmount != null){
            basicInfo.setReportedAmount(reportedAmount.multiply(multiply));
        }
        // 已付款金额
        BigDecimal paidAmount = basicInfo.getPaidAmount();
        if (paidAmount != null){
            basicInfo.setPaidAmount(paidAmount.multiply(multiply));
        }
        // 含税合同金额
        BigDecimal amountIncludTax = basicInfo.getAmountIncludTax();
        if (amountIncludTax != null){
            basicInfo.setAmountIncludTax(amountIncludTax.multiply(multiply));
        }
        // 含税合同金额
        BigDecimal taxAmount = basicInfo.getTaxAmount();
        if (taxAmount != null){
            basicInfo.setTaxAmount(taxAmount.multiply(multiply));
        }
        // 不含税合同金额
        BigDecimal amountExcludTax = basicInfo.getAmountExcludTax();
        if (amountExcludTax != null){
            basicInfo.setAmountExcludTax(amountExcludTax.multiply(multiply));
        }
        // 保证金
        BigDecimal contractDeposit = basicInfo.getContractDeposit();
        if (contractDeposit != null){
            basicInfo.setContractDeposit(contractDeposit.multiply(multiply));
        }
        // 原保证金
        BigDecimal oldContractDeposit = basicInfo.getOldContractDeposit();
        if (oldContractDeposit != null){
            basicInfo.setOldContractDeposit(oldContractDeposit.multiply(multiply));
        }
        return basicInfo;
    }

    /**
     * 将传入的合同基础信息集合里的金额扩大100倍
     * @param basicInfos
     * @return
     */
    public List<BasicInfo> enlargeBasicInfoAmountList(List<BasicInfo> basicInfos){
        if (basicInfos != null && basicInfos.size() > 0){
            BasicInfo basicInfo = null;
            for (int i = 0; i < basicInfos.size(); i++){
                basicInfo = enlargeBasicInfoAmount(basicInfos.get(i));
                basicInfos.set(i, basicInfo);
            }
        }
        return basicInfos;
    }

    /**
     * 将传入的合同基础信息里的金额缩小100倍
     * @param basicInfo
     * @return
     */
    public BasicInfo shrinkBasicInfoAmount(BasicInfo basicInfo){
        AssertUtils.asserts(null == basicInfo,ContractErrorType.CONTRACT_IS_NULL);
        // 除法
        BigDecimal divide = new BigDecimal(100);
        // 原含税合同金额
        BigDecimal oldAmountIncludTax = basicInfo.getOldAmountIncludTax();
        if (oldAmountIncludTax != null){
            basicInfo.setOldAmountIncludTax(oldAmountIncludTax.divide(divide));
        }
        // 原税额
        BigDecimal oldTaxAmount = basicInfo.getOldTaxAmount();
        if (oldTaxAmount != null){
            basicInfo.setOldTaxAmount(oldTaxAmount.divide(divide));
        }
        // 原不含税合同金额
        BigDecimal oldAmountExcludTax = basicInfo.getOldAmountExcludTax();
        if (oldAmountExcludTax != null){
            basicInfo.setOldAmountExcludTax(oldAmountExcludTax.divide(divide));
        }
        // 已报账金额
        BigDecimal reportedAmount = basicInfo.getReportedAmount();
        if (reportedAmount != null){
            basicInfo.setReportedAmount(reportedAmount.divide(divide));
        }
        // 已付款金额
        BigDecimal paidAmount = basicInfo.getPaidAmount();
        if (paidAmount != null){
            basicInfo.setPaidAmount(paidAmount.divide(divide));
        }
        // 含税合同金额
        BigDecimal amountIncludTax = basicInfo.getAmountIncludTax();
        if (amountIncludTax != null){
            basicInfo.setAmountIncludTax(amountIncludTax.divide(divide));
        }
        // 含税合同金额
        BigDecimal taxAmount = basicInfo.getTaxAmount();
        if (taxAmount != null){
            basicInfo.setTaxAmount(taxAmount.divide(divide));
        }
        // 不含税合同金额
        BigDecimal amountExcludTax = basicInfo.getAmountExcludTax();
        if (amountExcludTax != null){
            basicInfo.setAmountExcludTax(amountExcludTax.divide(divide));
        }
        // 保证金
        BigDecimal contractDeposit = basicInfo.getContractDeposit();
        if (contractDeposit != null){
            basicInfo.setContractDeposit(contractDeposit.divide(divide));
        }
        // 原保证金
        BigDecimal oldContractDeposit = basicInfo.getOldContractDeposit();
        if (oldContractDeposit != null){
            basicInfo.setOldContractDeposit(oldContractDeposit.divide(divide));
        }
        return basicInfo;
    }

    public BasicInfoVo shrinkBasicInfoAmount(BasicInfoVo basicInfo){
        AssertUtils.asserts(null == basicInfo,ContractErrorType.CONTRACT_IS_NULL);
        // 除法
        BigDecimal divide = new BigDecimal(100);
        // 原含税合同金额
        BigDecimal oldAmountIncludTax = basicInfo.getOldAmountIncludTax();
        if (oldAmountIncludTax != null){
            basicInfo.setOldAmountIncludTax(oldAmountIncludTax.divide(divide));
        }
        // 原税额
        BigDecimal oldTaxAmount = basicInfo.getOldTaxAmount();
        if (oldTaxAmount != null){
            basicInfo.setOldTaxAmount(oldTaxAmount.divide(divide));
        }
        // 原不含税合同金额
        BigDecimal oldAmountExcludTax = basicInfo.getOldAmountExcludTax();
        if (oldAmountExcludTax != null){
            basicInfo.setOldAmountExcludTax(oldAmountExcludTax.divide(divide));
        }
        // 已报账金额
        BigDecimal reportedAmount = basicInfo.getReportedAmount();
        if (reportedAmount != null){
            basicInfo.setReportedAmount(reportedAmount.divide(divide));
        }
        // 已付款金额
        BigDecimal paidAmount = basicInfo.getPaidAmount();
        if (paidAmount != null){
            basicInfo.setPaidAmount(paidAmount.divide(divide));
        }
        // 含税合同金额
        BigDecimal amountIncludTax = basicInfo.getAmountIncludTax();
        if (amountIncludTax != null){
            basicInfo.setAmountIncludTax(amountIncludTax.divide(divide));
        }
        // 含税合同金额
        BigDecimal taxAmount = basicInfo.getTaxAmount();
        if (taxAmount != null){
            basicInfo.setTaxAmount(taxAmount.divide(divide));
        }
        // 不含税合同金额
        BigDecimal amountExcludTax = basicInfo.getAmountExcludTax();
        if (amountExcludTax != null){
            basicInfo.setAmountExcludTax(amountExcludTax.divide(divide));
        }
        // 保证金
        BigDecimal contractDeposit = basicInfo.getContractDeposit();
        if (contractDeposit != null){
            basicInfo.setContractDeposit(contractDeposit.divide(divide));
        }
        // 原保证金
        BigDecimal oldContractDeposit = basicInfo.getOldContractDeposit();
        if (oldContractDeposit != null){
            basicInfo.setOldContractDeposit(oldContractDeposit.divide(divide));
        }
        return basicInfo;
    }

    /**
     * 将传入的合同基础信息集合里的金额缩小100倍
     * @param basicInfos
     * @return
     */
    public List<BasicInfo> shrinkBasicInfoAmountList(List<BasicInfo> basicInfos){
        if (basicInfos != null && basicInfos.size() > 0){
            BasicInfo basicInfo = null;
            for (int i = 0; i < basicInfos.size(); i++){
                basicInfo = shrinkBasicInfoAmount(basicInfos.get(i));
                basicInfos.set(i, basicInfo);
            }
        }
        return basicInfos;
    }

    public List<BasicInfoVo> shrinkBasicInfoAmountListVo(List<BasicInfoVo> basicInfos){
        if (basicInfos != null && basicInfos.size() > 0){
            BasicInfoVo basicInfo = null;
            for (int i = 0; i < basicInfos.size(); i++){
                basicInfo = shrinkBasicInfoAmount(basicInfos.get(i));
                basicInfos.set(i, basicInfo);
            }
        }
        return basicInfos;
    }

    /**
     * 保存定稿信息
     * @param basicInfoForm
     * @return
     */
    public BasicInfoVo saveContractFinalize(BasicInfoForm basicInfoForm, UserVo userVo){
//        BasicInfo basicInfo =new BeanUtils<BasicInfo>().copyObj(basicInfoForm,BasicInfo.class);
        // 获取水印信息
        OrganizationVo organizationVo = commonService.getOrganization();
        String departmentCode = organizationVo.getCode();
        String watermarkMap=sysWatermarkService.getWatermark(departmentCode);

        List<BasicAttamentMap> basicAttamentList = new ArrayList<>();

        Long contractId = basicInfoForm.getId();
        BasicInfo basicInfo = basicInfoMapper.selectById(contractId);

        basicInfo.setSurePersonCode(userVo.getId());
        basicInfo.setSurePerson(userVo.getName());
        basicInfo.setSureStatue("SUR1000");
        basicInfo.setSureStatueTime(LocalDateTime.now());

        basicInfoMapper.updateById(basicInfo);
        BasicInfoVo basicInfoVo =new BeanUtils<BasicInfoVo>().copyObj(basicInfo, BasicInfoVo.class);
        // 需要先删除该类型对应文件
        QueryWrapper<BasicAttamentMap> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BasicAttamentMap.CONTRACT_ID, contractId);
        queryWrapper.in(BasicAttamentMap.FILE_TYPE, "FIL1000","FIL2000");
        List<BasicAttamentMap> oldBasicAttamentMapList = basicAttamentMapMapper.selectList(queryWrapper);
        // 异常，如果没有查询出文件信息，这抛异常
        AssertUtils.asserts(null == oldBasicAttamentMapList ||  oldBasicAttamentMapList.size() == 0 , ContractErrorType.CONTRACTNO_GET_OLD_ATTAMENT);
        for (BasicAttamentMap oldBasicAttamentMap:oldBasicAttamentMapList) {
            if (oldBasicAttamentMap.getFileExt().equals("docx") || oldBasicAttamentMap.getFileExt().equals("doc")){
                String name = oldBasicAttamentMap.getFileName();
                String realName = name.substring(0, name.lastIndexOf("."));
                String size = oldBasicAttamentMap.getFileSize().toString();
                // pdf生成路径
//                String outpath = "C:\\Users\\Administrator\\Desktop\\work\\" + realName + ".pdf";
                File file = null;
                File fileWater = null;
                MultipartFile multFile = null;
                try {
                    String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
                    String outpath = jarPath + File.separator + realName + "Water.pdf";
                    String outpathWater = jarPath + File.separator + realName + ".pdf";
                    System.out.println(outpath);
                    // 获取word文件输入流
                    InputStream source = Fileutil.getInputStreamFromUrl(oldBasicAttamentMap.getFileUrl());
//                    File fileDoc = new File(outpathDoc);
//                    OutputStream os = new FileOutputStream(fileDoc);
//                    byte[] data = new byte[Integer.parseInt(size)];
//                    source.read(data);
//                    os.write(data);
//                    os.flush();
//                    // 获取转换后的pdf输出流
//                    OutputStream target = new FileOutputStream(outpath);
//                    PdfOptions options = PdfOptions.create();
                    // 转换word为pdf
//                    WordToPDF.wordConverterToPdf(source, target, options);
                    WordToPDF.docx2PDF(source, outpath);
//                    // 关闭流 删除word文件
//                    source.close();
//                    os.close();
//                    fileDoc.delete();
                    // 获取pdf文件
                    PDFSetWater.waterMark(outpath, outpathWater, watermarkMap);
                    fileWater = new File(outpathWater);
                    FileInputStream fileInputStream = new FileInputStream(fileWater);
                    // 转换file对象为MultipartFile对象，name固定写死为 “file”
                    multFile = new MockMultipartFile("file",fileWater.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
                    // 上传文件
                    Result<FileInfoVo> result =  fileOperatorFeignService.uploadFile(multFile);
                    FileInfoVo fileInfoVo = result.getData();
                    BasicAttamentMap basicAttamentMap = new BasicAttamentMap();
                    basicAttamentMap.setId(NumberUtils.toLong(fileInfoVo.getId()));
                    basicAttamentMap.setAttamentId(NumberUtils.toLong(fileInfoVo.getId()));
                    basicAttamentMap.setFileName(fileInfoVo.getFileName());
                    basicAttamentMap.setFileUrl(fileInfoVo.getFileUrl());
                    basicAttamentMap.setFileSize(fileInfoVo.getFileSize());
                    basicAttamentMap.setFileExt(fileInfoVo.getFileExt());
                    basicAttamentMap.setFileType(oldBasicAttamentMap.getFileType());
                    basicAttamentMap.setUploadTime(fileInfoVo.getUploadTime());
                    basicAttamentList.add(basicAttamentMap);
                    // 删除pdf
                    fileInputStream.close();
                    fileWater.delete();
                    file = new File(outpath);
                    file.delete();
                }catch (Exception e){
                    AssertUtils.asserts(true , ContractErrorType.WORD_TO_PDF);
                }
            }else{
                BasicAttamentMap basicAttamentMap = new BasicAttamentMap();
                basicAttamentMap.setId(oldBasicAttamentMap.getId());
                basicAttamentMap.setAttamentId(oldBasicAttamentMap.getAttamentId());
                basicAttamentMap.setFileName(oldBasicAttamentMap.getFileName());
                basicAttamentMap.setFileUrl(oldBasicAttamentMap.getFileUrl());
                basicAttamentMap.setFileSize(oldBasicAttamentMap.getFileSize());
                basicAttamentMap.setFileExt(oldBasicAttamentMap.getFileExt());
                basicAttamentMap.setFileType(oldBasicAttamentMap.getFileType());
                basicAttamentMap.setUploadTime(oldBasicAttamentMap.getUploadTime());
                basicAttamentList.add(basicAttamentMap);
            }
        }
        basicAttamentMapMapper.delete(queryWrapper);

        if (basicAttamentList != null && basicAttamentList.size() > 0){
            List<BasicAttamentMapVo> basicAttamentListVo = new ArrayList<>();
            for (int i = 0; i < basicAttamentList.size(); i++){
                // 新增关联文件
                BasicAttamentMap basicAttament = basicAttamentList.get(i);
                basicAttament.setCreateBy(userVo.getId());
                basicAttament.setContractId(contractId);
                basicAttamentMapMapper.insert(basicAttament);
                basicAttamentList.set(i, basicAttament);
            }
            basicAttamentListVo=new BeanUtils<BasicAttamentMapVo>().copyObjs(basicAttamentList,BasicAttamentMapVo.class);
            basicInfoVo.setBasicAttamentList((ArrayList<BasicAttamentMapVo>) basicAttamentListVo);
        }
        return basicInfoVo;
    }

    public int updateBasicInfoRemarkById(BasicInfoForm basicInfoForm){
        return basicInfoMapper.updateBasicInfoRemarkById(basicInfoForm);
    }

    public UserVo getUserById(String id){
        UserVo userVo = null;
        if (null != id && !"".equals(id)){
            Result<UserVo> result = userClient.get(Long.parseLong(id));
            if (result.isSuccess()){
                userVo = result.getData();
            }
        }
        return userVo;
    }

    public BasicInfoVo selectContractExecuteCheck(BasicInfoQueryForm basicInfoQueryForm,UserVo userVo){
        basicInfoQueryForm.setExecuterCode(userVo.getId());
        int page = basicInfoQueryForm.getCurrentPage();
        int size = basicInfoQueryForm.getPageSize();
        int minPage = (page - 1) * size + 1;
        int maxPage = page * size;
        basicInfoQueryForm.setMinPage(minPage);
        basicInfoQueryForm.setMaxPage(maxPage);
        basicInfoQueryForm = setQuery(basicInfoQueryForm);
        List<BasicInfoVo> basicInfoVos = shrinkBasicInfoAmountListVo(basicInfoMapper.selectContractExecuteCheck(basicInfoQueryForm));
        BigDecimal count = basicInfoMapper.selectContractExecuteCheckCount(basicInfoQueryForm);
        BasicInfoVo basicInfoVo = new BasicInfoVo();
        basicInfoVo.setListBasicInfoVo(basicInfoVos);
        basicInfoVo.setTotal(count.toString());
        return basicInfoVo;
    }

    /**
     * 保存印花税信息
     * @param basicInfoForm
     * @return
     */
    public int saveImprint(BasicInfoForm basicInfoForm){
        Long contractId = basicInfoForm.getId();
        List<BasicInfoVo> basicInfoVos = basicInfoMapper.queryCorrelationBasicNew(contractId.toString());
        AssertUtils.asserts(basicInfoVos == null || basicInfoVos.size() <= 0, ContractErrorType.CONTRACT_IS_NULL);
        BasicInfoVo basicInfo = basicInfoVos.get(0);
        String imprintAmount = basicInfo.getImprintAmount();
        if (imprintAmount != null && !imprintAmount.equals("")){
            Long imprintAmountLong = Long.parseLong(imprintAmount);
            Long imprintAmountNewLong = 0L;
            if (basicInfoForm.getImprintAmount() != null && !basicInfoForm.getImprintAmount().equals("")){
                imprintAmountNewLong = Long.parseLong(imprintAmount);
                if (imprintAmountLong < imprintAmountNewLong){
                    for (int i = 0; i < basicInfoVos.size(); i++){
                        BasicInfoVo basicInfoVo = basicInfoVos.get(i);
                        BasicInfo basicInfoUpdate = new BeanUtils<BasicInfo>().copyObj(basicInfoVo, BasicInfo.class);
                        basicInfoUpdate.setId(Long.parseLong(basicInfoVo.getId()));
                        basicInfoUpdate.setIsImprint(basicInfoForm.getIsImprint());
                        basicInfoUpdate.setImprintAmount(basicInfoForm.getImprintAmount());
                        basicInfoUpdate.setImprintCases(basicInfoForm.getImprintCases());
                        basicInfoMapper.updateById(basicInfoUpdate);
                    }
                }
            }
        }else{
            for (int i = 0; i < basicInfoVos.size(); i++){
                BasicInfoVo basicInfoVo = basicInfoVos.get(i);
                BasicInfo basicInfoUpdate = new BeanUtils<BasicInfo>().copyObj(basicInfoVo, BasicInfo.class);
                basicInfoUpdate.setId(Long.parseLong(basicInfoVo.getId()));
                basicInfoUpdate.setIsImprint(basicInfoForm.getIsImprint());
                basicInfoUpdate.setImprintAmount(basicInfoForm.getImprintAmount());
                basicInfoUpdate.setImprintCases(basicInfoForm.getImprintCases());
                basicInfoMapper.updateById(basicInfoUpdate);
            }
        }
        return 1;
    }

    public Result selectBasicInfoToFssc(Long contractId){
        BasicInfo basicInfo = selectContractInfoById(contractId);
        BasicInfoVoToFssc basicInfoVo = new BeanUtils<BasicInfoVoToFssc>().copyObj(basicInfo, BasicInfoVoToFssc.class);
        // 查询合同关联信息
        List<BasicInfoVo> basicInfoVos = basicInfoMapper.queryCorrelationBasic(contractId.toString());
        basicInfoVos = shrinkBasicInfoAmountListVo(basicInfoVos);
        AssertUtils.asserts(basicInfoVos == null || basicInfoVos.size() <= 0, ContractErrorType.CONTRACT_IS_NULL);
        basicInfoVos.remove(0);
        List<BasicInfoVoToFssc> basicInfoVoToFsscs = new BeanUtils<BasicInfoVoToFssc>().copyObjs(basicInfoVos, BasicInfoVoToFssc.class);
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SysDict.CODE, basicInfoVo.getStatue());
        queryWrapper.eq(SysDict.TYPE, "EXAMINE_STATUE");
        SysDict sysDict = sysDictMapper.selectOne(queryWrapper);
        basicInfoVo.setStatueName(sysDict.getValue());
        if ("REL3000".equals(basicInfoVo.getRelationCode())){
            basicInfoVo.setOldContractId(null);
        }else{
            for (int i=0; i < basicInfoVoToFsscs.size(); i++) {
                BasicInfoVoToFssc basicInfoVoToFssc = basicInfoVoToFsscs.get(i);
                QueryWrapper<SysDict> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq(SysDict.CODE, basicInfoVoToFssc.getStatue());
                queryWrapper1.eq(SysDict.TYPE, "EXAMINE_STATUE");
                SysDict sysDict1 = sysDictMapper.selectOne(queryWrapper1);
                basicInfoVoToFssc.setStatueName(sysDict1.getValue());
                basicInfoVoToFsscs.set(i, basicInfoVoToFssc);
            }
            basicInfoVo.setListBasicInfoVo(basicInfoVoToFsscs);
        }

        //查询合同签约主体信息
        List<BasicSubjectMap> basicSubjectMapList = selectBasicSubjectByContractId(contractId);
        List<BasicSubjectMapVo> basicSubjectMapVoList = new BeanUtils<BasicSubjectMapVo>().copyObjs(basicSubjectMapList,BasicSubjectMapVo.class);
        for (int i=0; i < basicSubjectMapVoList.size(); i++) {
            BasicSubjectMapVo basicSubjectMapVo = basicSubjectMapVoList.get(i);
            basicSubjectMapVo.setCreateTime(null);
            basicSubjectMapVo.setUpdateTime(null);
            basicSubjectMapVoList.set(i, basicSubjectMapVo);
        }
        basicInfoVo.setBasicSubjectList((ArrayList<BasicSubjectMapVo>)basicSubjectMapVoList);
        List<BasicInfoVoToFssc> basicInfoVoList = new ArrayList<>();
        basicInfoVoList.add(basicInfoVo);
        Result result = fsscBaseContractInfoFeignService.receive(basicInfoVoList);
        if (result.isSuccess()){
            //发送成功，将合同基本信息发送财务系统标记修改为1
            basicInfo.setIsToBasicFssc("1");
            basicInfo = enlargeBasicInfoAmount(basicInfo);
            basicInfoMapper.updateById(basicInfo);
        }
        return result;
    }

    /**
     * 根据合同是否推送财务标记，查询未推送财务的合同信息
     * @return
     */
    public Result selectBasicInfoByFlag(){
        QueryWrapper<BasicInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BasicInfo.IS_TO_FSSC, "0");
        List<BasicInfo> basicInfos = basicInfoMapper.selectList(queryWrapper);
        List<BasicInfoVoToFssc> basicInfoVos = new BeanUtils<BasicInfoVoToFssc>().copyObjs(basicInfos, BasicInfoVoToFssc.class);
        for (int i = 0; i < basicInfoVos.size(); i++){
            BasicInfoVoToFssc basicInfoVo = basicInfoVos.get(i);
            String contractId = basicInfoVo.getId();
            // 查询合同关联信息
            List<BasicInfoVo> basicInfoVoList = basicInfoMapper.queryCorrelationBasic(contractId);
            List<BasicInfoVoToFssc> basicInfoVoToFsscs = new BeanUtils<BasicInfoVoToFssc>().copyObjs(basicInfoVoList, BasicInfoVoToFssc.class);
            AssertUtils.asserts(basicInfoVoList == null || basicInfoVoList.size() <= 0, ContractErrorType.CONTRACT_IS_NULL);
            basicInfoVoList.remove(0);
            basicInfoVo.setListBasicInfoVo(basicInfoVoToFsscs);

            //查询合同签约主体信息
            List<BasicSubjectMap> basicSubjectMapList = selectBasicSubjectByContractId(Long.parseLong(contractId));
            List<BasicSubjectMapVo> basicSubjectMapVoList = new BeanUtils<BasicSubjectMapVo>().copyObjs(basicSubjectMapList,BasicSubjectMapVo.class);
            for (int j=0; j < basicSubjectMapVoList.size(); j++) {
                BasicSubjectMapVo basicSubjectMapVo = basicSubjectMapVoList.get(j);
                basicSubjectMapVo.setCreateTime(null);
                basicSubjectMapVo.setUpdateTime(null);
                basicSubjectMapVoList.set(j, basicSubjectMapVo);
            }
            basicInfoVo.setBasicSubjectList((ArrayList<BasicSubjectMapVo>)basicSubjectMapVoList);
        }
        Result result = fsscBaseContractInfoFeignService.receive(basicInfoVos);
        if (result.isSuccess()){
            //发送成功，将合同基本信息发送财务系统标记修改为1
            List<BasicInfo> basicInfosFlag = new BeanUtils<BasicInfo>().copyObjs(basicInfoVos, BasicInfo.class);
            for (BasicInfo basicInfo: basicInfosFlag) {
                basicInfo.setIsToBasicFssc("1");
                basicInfoMapper.updateById(basicInfo);
            }
        }
        return result;
    }

    /**
     * 获取变动合同信息
     * @param basicInfoForm
     * @return
     */
    public BasicInfoVo getChangeContract(BasicInfoForm basicInfoForm){
        Long contractId = basicInfoForm.getId();
        BasicInfo basicInfo = basicInfoMapper.selectById(contractId);
        BasicInfoVo basicInfoVo =new BeanUtils<BasicInfoVo>().copyObj(basicInfo, BasicInfoVo.class);
        basicInfoVo = shrinkBasicInfoAmount(basicInfoVo);
        //财务信息
        List<FinanceInfoVo> financeInfoVoList = iFinanceInfoService.selectFinanceInfo(contractId.toString());
        // 实际付款总金额
        Double actPayRateAll = 0d;
        // 实际收款总金额
        Double actIncomeRateAll = 0d;
        if (financeInfoVoList != null && financeInfoVoList.size() > 0){
            for (FinanceInfoVo financeInfoVo: financeInfoVoList) {
                Double actPayRate = financeInfoVo.getActPayRate() != null ? financeInfoVo.getActPayRate() : 0d;
                actPayRateAll = actPayRateAll + actPayRate;
                Double actIncomeRate = financeInfoVo.getActIncomeRate() != null ? financeInfoVo.getActIncomeRate() : 0d;
                actIncomeRateAll = actIncomeRateAll + actIncomeRate;
            }
            String incomeExpenditureTypeCode = basicInfoVo.getIncomeExpenditureTypeCode();
            if (incomeExpenditureTypeCode != null && !incomeExpenditureTypeCode.equals("")){
                if (incomeExpenditureTypeCode.equals("INC1000")){
                    // 支出类
                    basicInfoVo.setReportedAmount(BigDecimal.valueOf(actPayRateAll));
                    basicInfoVo.setPaidAmount(BigDecimal.valueOf(actPayRateAll));
                }else if (incomeExpenditureTypeCode.equals("INC2000")){
                    // 收入类
                    basicInfoVo.setReportedAmount(BigDecimal.valueOf(actIncomeRateAll));
                    basicInfoVo.setPaidAmount(BigDecimal.valueOf(actIncomeRateAll));
                }else if (incomeExpenditureTypeCode.equals("INC3000")){
                    // 有收有支类
                    basicInfoVo.setReportedAmount(BigDecimal.valueOf(actIncomeRateAll));
                    basicInfoVo.setPaidAmount(BigDecimal.valueOf(actIncomeRateAll));
                }
            }
        }else {
            basicInfoVo.setReportedAmount(BigDecimal.valueOf(0));
            basicInfoVo.setPaidAmount(BigDecimal.valueOf(0));
        }
        basicInfoVo.setOldContractId(basicInfoVo.getId());
        basicInfoVo.setOldContractNo(basicInfoVo.getContractNo());
        basicInfoVo.setOldContractName(basicInfoVo.getContractName());
        basicInfoVo.setOldAmountIncludTax(basicInfoVo.getAmountIncludTax());
        basicInfoVo.setOldTaxRate(basicInfoVo.getTaxRate());
        basicInfoVo.setOldTaxRateValue(basicInfoVo.getTaxRateValue());
        basicInfoVo.setOldTaxAmount(basicInfoVo.getTaxAmount());
        basicInfoVo.setOldAmountExcludTax(basicInfoVo.getAmountExcludTax());
        basicInfoVo.setOldAmountExcludTaxCapital(basicInfoVo.getAmountExcludTaxCapital());
        basicInfoVo.setOldOrgCode(basicInfoVo.getOrgCode());
        basicInfoVo.setOldOrg(basicInfoVo.getOrg());
        basicInfoVo.setOldContactNum(basicInfoVo.getContactNum());
        basicInfoVo.setRelationCode(basicInfoForm.getRelationCode());
        basicInfoVo.setRelation(basicInfoForm.getRelation());
        basicInfoVo.setOldContractDeposit(basicInfoVo.getContractDeposit());
        basicInfoVo.setId(null);
        basicInfoVo.setSubmitPocessKey("");
        List<BasicSubjectMap> basicSubjectMapList = selectBasicSubjectByContractId(contractId);
        List<BasicSubjectMapVo> basicSubjectMapVoList=new BeanUtils<BasicSubjectMapVo>().copyObjs(basicSubjectMapList,BasicSubjectMapVo.class);
        basicInfoVo.setBasicSubjectList((ArrayList<BasicSubjectMapVo>)basicSubjectMapVoList);
        basicInfoVo.setBasicAttamentList(new ArrayList<>());
        return basicInfoVo;
    }

    /**
     * 保存合同变更信息
     * @param basicInfoForm
     * @param userVo
     * @param organizationVo
     * @return
     */
    public BasicInfo insertChangeContract(BasicInfoForm basicInfoForm, UserVo userVo, OrganizationVo organizationVo){
        BasicInfo basicInfo = addContractInfo(basicInfoForm, userVo, organizationVo);
        Long contractId = basicInfo.getId();
        basicInfo = basicInfoMapper.selectById(contractId);
        BasicInfo basicInfoOld = basicInfoMapper.selectById(basicInfo.getOldContractId());
        basicInfo.setExecuter(basicInfoOld.getExecuter());
        basicInfo.setExecuterCode(basicInfoOld.getExecuterCode());
        basicInfo.setExecuterOrg(basicInfoOld.getExecuterOrg());
        basicInfo.setExecuterOrgId(basicInfoOld.getExecuterOrgId());
        basicInfoMapper.updateById(basicInfo);
        return basicInfo;
    }

    /**
     *
     * @param rel2000 变更/补充
     * @param rel4000 终止/解除
     * @param rel5000 单方解除
     * @return
     */
    public Result saveBasicRelationStatus(String contractId, String rel2000, String rel4000, String rel5000){
        // 查询合同关联信息
        List<BasicInfoVo> basicInfoVos = basicInfoMapper.queryCorrelationBasic(contractId);
        AssertUtils.asserts(basicInfoVos == null || basicInfoVos.size() <= 0, ContractErrorType.CONTRACT_IS_NULL);
        String relationCode = basicInfoVos.get(0).getRelationCode();
        basicInfoVos.remove(0);
//        List<BasicInfo> basicInfos = new BeanUtils<BasicInfo>().copyObjs(basicInfoVos, BasicInfo.class);
        if (basicInfoVos != null && relationCode !=null && !relationCode.equals("")){
            if (relationCode.equals("REL2000")){
                for (BasicInfoVo basicInfoVo: basicInfoVos) {
                    BasicInfo basicInfo = basicInfoMapper.selectById(basicInfoVo.getId());
                    basicInfo.setStatue(rel2000);
                    basicInfoMapper.updateById(basicInfo);
                }
            }else if (relationCode.equals("REL4000")){
                for (BasicInfoVo basicInfoVo: basicInfoVos) {
                    BasicInfo basicInfo = basicInfoMapper.selectById(basicInfoVo.getId());
                    basicInfo.setStatue(rel4000);
                    basicInfoMapper.updateById(basicInfo);
                }
            }else if (relationCode.equals("REL5000")){
                for (BasicInfoVo basicInfoVo: basicInfoVos) {
                    BasicInfo basicInfo = basicInfoMapper.selectById(basicInfoVo.getId());
                    basicInfo.setStatue(rel5000);
                    basicInfoMapper.updateById(basicInfo);
                }
            }
        }
        return Result.success();
    }

    /**
     * 退回时只修改上一个合同的状态为变更前的状态
     * @return
     */
    public Result saveBasicRelationStatusLast(Long contractId){
        // 查询合同关联信息
        BasicInfo basicInfo = basicInfoMapper.selectById(contractId);
        String oldId = basicInfo.getOldContractId();
        if (null != oldId && !"".equals(oldId) && !"REL3000".equals(basicInfo.getRelationCode())){
            BasicInfo basicInfoOld = basicInfoMapper.selectById(Long.parseLong(oldId));
            basicInfoOld.setStatue("EXA6000");
            basicInfoMapper.updateById(basicInfoOld);
        }
        return Result.success();
    }

    /**
     * 将上一个合同状态修改回履行状态
     * @return
     */
    public Result saveBasicOldContractStatus(String contractId){
        BasicInfo basicInfo = basicInfoMapper.selectById(contractId);
        String oldId = basicInfo.getOldContractId();
        if (oldId != null && !oldId.equals("")){
            BasicInfo oldBasicInfo = basicInfoMapper.selectById(oldId);
            if (oldBasicInfo != null){
                oldBasicInfo.setStatue("EXA6000");
                basicInfoMapper.updateById(oldBasicInfo);
            }
        }
        return Result.success();
    }

    private BasicInfoQueryForm setQuery(BasicInfoQueryForm basicInfoQueryForm){
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        String orgCode = organizationVo.getCode();
        if (!"1001002".equals(orgCode) && !"1001045".equals(orgCode) && !"1001018".equals(orgCode)){
            String userId = userVo.getId();
            basicInfoQueryForm.setUserCanGet(userId);
            QueryWrapper<ProcessTask> queryWrapper = new QueryWrapper <>();
            queryWrapper.eq(ProcessTask.APPROVER_ACOUNT, userId);
            queryWrapper.eq(ProcessTask.OBJECT_TYPE, "1");
            List<ProcessTask> processTaskList =  processTaskMapper.selectList(queryWrapper);
            List<ProcessTaskQueryForm> processTaskQueryFormList = new BeanUtils<ProcessTaskQueryForm>().copyObjs(processTaskList, ProcessTaskQueryForm.class);
            if (processTaskQueryFormList != null && processTaskQueryFormList.size() > 0){
                basicInfoQueryForm.setProcessTaskList(processTaskQueryFormList);
            }
            // 获取角色，如果为处长，则将其下面所有部门获取出来
            Result<List<RoleVo>> roleResult = roleFeignService.getByDeputyAccountId(userVo.getCurrentDeputyAccount().getId(), "data", "contract");
            Object obj = roleResult.getData();
            if (obj != null && obj instanceof List){
                List<RoleVo> roleList = (List<RoleVo> )obj;
                if (roleList != null && roleList.size() > 0){
                    for (RoleVo roleVo: roleList) {
                        if (roleVo.getId() == 84L){
                            basicInfoQueryForm.setOldOrgCode(orgCode);
                        }
                    }
                }
            }
        }
        return basicInfoQueryForm;
    }

    /**
     * 查询合同台账信息
     * @param basicInfoQueryForm
     * @return
     */
    public List<BasicInfoVo> selectContractLedger(BasicInfoQueryForm basicInfoQueryForm){
        int page = basicInfoQueryForm.getCurrentPage();
        int size = basicInfoQueryForm.getPageSize();
        int minPage = (page - 1) * size + 1;
        int maxPage = page * size;
        basicInfoQueryForm.setMinPage(minPage);
        basicInfoQueryForm.setMaxPage(maxPage);
        basicInfoQueryForm = setQuery(basicInfoQueryForm);
        List<BasicInfoVo> list = basicInfoMapper.selectContractLedger(basicInfoQueryForm);
        return list;
    }

    /**
     * 查询合同台账信息数量
     * @param basicInfoQueryForm
     */
    public BigDecimal selectContractLedgerCount(BasicInfoQueryForm basicInfoQueryForm){
        basicInfoQueryForm = setQuery(basicInfoQueryForm);
        return basicInfoMapper.selectContractLedgerCount(basicInfoQueryForm);
    }

    /**
     * 导出合同台账信息
     * @param basicInfoQueryForm
     * @return
     */
    public Result exportContractLedger(BasicInfoQueryForm basicInfoQueryForm){
        basicInfoQueryForm = setQuery(basicInfoQueryForm);
        List<BasicInfoVo> basicInfoVos = basicInfoMapper.exportContractLedger(basicInfoQueryForm);
        // 上传文件
        Result<FileInfoVo> result = null;
        try {
            String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateStr = sdf.format(new Date());
            String filePath = jarPath + File.separator + "合同台账" + dateStr + ".xls";
            String[] titles = {
                    "合同编号",
                    "合同名称",
                    "对方签约主体",
                    "我方签约主体",
                    "合同总金额（元）",
                    "签订日期",
                    "履行期限从",
                    "履行期限至",
                    "合同类型",
                    "合同状态",
                    "主办部门",
                    "经办人",
                    "是否缴纳印花税",
                    "是否为涉外合同"};
            List<String[]> list = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (BasicInfoVo basicInfoVo: basicInfoVos) {
                String[] str = {
                        basicInfoVo.getContractNo(),
                        basicInfoVo.getContractName(),
                        basicInfoVo.getOutSubject(),
                        basicInfoVo.getOtherSubject(),
                        basicInfoVo.getAmountIncludTax() != null ? basicInfoVo.getAmountIncludTax().toString() : "",
                        basicInfoVo.getValidTime() != null ? basicInfoVo.getValidTime().format(formatter) : "",
                        basicInfoVo.getExecuteStartTime() != null ? basicInfoVo.getExecuteStartTime().format(formatter) : "",
                        basicInfoVo.getExecuteEndTime() != null ? basicInfoVo.getExecuteEndTime().format(formatter) : "",
                        basicInfoVo.getIncomeExpenditureType() + "-" + basicInfoVo.getContractType(),
                        basicInfoVo.getStatue(),
                        basicInfoVo.getOrg(),
                        basicInfoVo.getOperator(),
                        basicInfoVo.getIsImprint() != null && !basicInfoVo.getIsImprint().equals("") ? basicInfoVo.getIsImprint() == "Y" ? "是" : "否" : "",
                        basicInfoVo.getIsForeignContract() != null && !basicInfoVo.getIsForeignContract().equals("") ? basicInfoVo.getIsForeignContract() == "1" ? "是" : "否" : ""
                };
                list.add(str);
            }
            System.out.println(filePath);
            ExcelUtil.writeExcel(filePath, titles, list);

            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            MultipartFile multFile = new MockMultipartFile("file",file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
            // 上传文件
            result =  fileOperatorFeignService.uploadFile(multFile);
            file.delete();
        }catch (Exception e){
            AssertUtils.asserts(true , ContractErrorType.EXPORT_EXCEPTION);
        }
        return result;
    }

    @Override
    public List<BasicInfoVo> selectNoBackDating(BasicInfoQueryForm basicInfoQueryForm) {
        basicInfoQueryForm = setQuery(basicInfoQueryForm);
        return basicInfoMapper.selectNoBackDating(basicInfoQueryForm);
    }

    @Override
    public BasicInfoVo selectApprovalOpinion(BasicInfoQueryForm basicInfoQueryForm) {
        basicInfoQueryForm = setQuery(basicInfoQueryForm);
        List<BasicInfoVo> basicInfoVos = basicInfoMapper.selectTotal(basicInfoQueryForm);
        //获取总数
        int total = basicInfoVos.size();
//       basicInfoQueryForm.setTotal(total);
        //获取传入的每页显示数和页数
        int pageSize = basicInfoQueryForm.getPageSize();//每页数
        int currentPage = basicInfoQueryForm.getCurrentPage();//当前页
        int minTotal = (currentPage-1)*pageSize + 1;
        int maxTotal = pageSize*currentPage;
        basicInfoQueryForm.setMaxPage(maxTotal);
        basicInfoQueryForm.setMinPage(minTotal);
        List<BasicInfoVo> basicInfoVoList = basicInfoMapper.selectOpinionBaseInfo(basicInfoQueryForm);
        List<BasicInfoVo> basicInfoVoTotal = new ArrayList<>();
        if(null != basicInfoVoList && basicInfoVoList.size()>0){
            for(BasicInfoVo basicInfoVo:basicInfoVoList){
                List<ApprovalOpinionVo> approvalOpinionVos = basicInfoMapper.selectOpinion(basicInfoVo.getId());
                basicInfoVo.setApprovalOpinionVoList(approvalOpinionVos);
                basicInfoVoTotal.add(basicInfoVo);
            }

        }
        BasicInfoVo basicInfoVo = new BasicInfoVo();
        basicInfoVo.setPaperBasicInfo(basicInfoVoTotal);
        basicInfoVo.setOpinionNum(total);
        return basicInfoVo;
    }

    public BasicInfoVo selectExecuteStatue(BasicInfoQueryForm basicInfoQueryForm){
        BasicInfoVo basicInfoVo = new BasicInfoVo();
        basicInfoQueryForm = setQuery(basicInfoQueryForm);
        // 正常履行合同比例
        List<BasicInfoExecuteVo> basicInfoExecuteVos = basicInfoMapper.selectExecuteRatio(basicInfoQueryForm);
        // 非正常履行合同分类统计图
        BigDecimal payDateBigDecimal = basicInfoMapper.getPayDate(basicInfoQueryForm);
        BigDecimal incomeDateBigDecimal = basicInfoMapper.getIncomeDate(basicInfoQueryForm);
        BigDecimal payMoneyBigDecimal = basicInfoMapper.getPayMoney(basicInfoQueryForm);
        BigDecimal incomeMoneyBigDecimal = basicInfoMapper.getIncomeMoney(basicInfoQueryForm);
        BasicInfoExecuteVo basicInfoExecuteVo = new BasicInfoExecuteVo();
        basicInfoExecuteVo.setPayDate(payDateBigDecimal.toString());
        basicInfoExecuteVo.setIncomeDate(incomeDateBigDecimal.toString());
        basicInfoExecuteVo.setPayMoney(payMoneyBigDecimal.toString());
        basicInfoExecuteVo.setIncomeMoney(incomeMoneyBigDecimal.toString());
        //年度统计
        List<BasicInfoExecuteVo> basicInfoExecuteVosYear = basicInfoMapper.getMoneyAll(basicInfoQueryForm);
        // 放入信息
        basicInfoVo.setBasicInfoExecuteVoList(basicInfoExecuteVos);
        basicInfoVo.setBasicInfoExecuteVo(basicInfoExecuteVo);
        basicInfoVo.setBasicInfoExecuteVoYear(basicInfoExecuteVosYear);
        return basicInfoVo;
    }

    /**
     * 设置合同履行期限信息
     * @param id
     * @return
     */
    public BasicInfo setExecuteTime(Long id){
        BasicInfo basicInfo = basicInfoMapper.selectById(id);
        if (basicInfo != null){
            String executeType = basicInfo.getExecuteTypeCode();
            if (executeType != null && !executeType.equals("")){
                if (executeType.equals("EXE2000")){
                    // 签署起+固定时长
                    LocalDateTime validTime = basicInfo.getValidTime();
                    basicInfo.setExecuteStartTime(validTime);
                    Long execucteLong = basicInfo.getExecuteLong();
                    if (execucteLong != null){
                        String timeType = basicInfo.getTimeType();
                        if (timeType != null && !timeType.equals("")) {
                            if (timeType.equals("TIM1000")){
                                // 年
                                LocalDateTime executeEndTime = validTime.plusYears(execucteLong);
                                basicInfo.setExecuteEndTime(executeEndTime);
                            }else if (timeType.equals("TIM2000")){
                                // 月
                                LocalDateTime executeEndTime = validTime.plusMonths(execucteLong);
                                basicInfo.setExecuteEndTime(executeEndTime);
                            }else if (timeType.equals("TIM3000")){
                                // 日
                                LocalDateTime executeEndTime = validTime.plusDays(execucteLong);
                                basicInfo.setExecuteEndTime(executeEndTime);
                            }
                        }
                    }
                }else if(executeType.equals("EXE3000")){
                    // 签署起+截止日期
                    LocalDateTime validTime = basicInfo.getValidTime();
                    basicInfo.setExecuteStartTime(validTime);
                }else if(executeType.equals("EXE4000")){
                    // 签署起+条件结束
                    LocalDateTime validTime = basicInfo.getValidTime();
                    basicInfo.setExecuteStartTime(validTime);
                }
                basicInfoMapper.updateById(basicInfo);
            }
        }
        return basicInfo;
    }

    /**
     * 根据合同oldid保存旅行信息
     */
    public Result saveInfoByOldContractId(BasicInfo basicInfo){
        if (basicInfo != null){
            Long id = basicInfo.getId();
            String oldId = basicInfo.getOldContractId();
            if (oldId != null && !oldId.equals("") && id != null){
                // 将财务信息放到新的合同上
                QueryWrapper<BasicFinanceMap> finWrapper = new QueryWrapper<>();
                finWrapper.eq(BasicFinanceMap.CONTRACT_ID, oldId);
                List<BasicFinanceMap> financeList = basicFinanceMapMapper.selectList(finWrapper);
                if (financeList != null && financeList.size() > 0){
                    for (BasicFinanceMap basicFinanceMap: financeList) {
                        basicFinanceMap.setId(null);
                        basicFinanceMap.setContractId(id);
                        basicFinanceMapMapper.insert(basicFinanceMap);
                    }
                }
                // 将订单信息放到新的合同上
                QueryWrapper<BasicTicketMap> ticketWrapper = new QueryWrapper<>();
                ticketWrapper.eq(BasicTicketMap.CONTRACT_ID, oldId);
                List<BasicTicketMap> ticketList = basicTicketMapMapper.selectList(ticketWrapper);
                if (ticketList != null && ticketList.size() > 0){
                    for (BasicTicketMap BasicTicketMap: ticketList) {
                        BasicTicketMap.setId(null);
                        BasicTicketMap.setContractId(id);
                        basicTicketMapMapper.insert(BasicTicketMap);
                    }
                }
                // 将项目信息放到新的合同上
                QueryWrapper<BasicProjectMap> projectWrapper = new QueryWrapper<>();
                projectWrapper.eq(BasicProjectMap.CONTRACT_ID, oldId);
                List<BasicProjectMap> projectList = basicProjectMapMapper.selectList(projectWrapper);
                if (projectList != null && projectList.size() > 0){
                    for (BasicProjectMap BasicProjectMap: projectList) {
                        BasicProjectMap.setId(null);
                        BasicProjectMap.setContractId(id);
                        basicProjectMapMapper.insert(BasicProjectMap);
                    }
                }
                // 将其他监控计划放到新的合同上
                QueryWrapper<BasicMonitorMap> monitorWrapper = new QueryWrapper<>();
                monitorWrapper.eq(BasicMonitorMap.CONTRACT_ID, oldId);
                List<BasicMonitorMap> monitorList = basicMonitorMapMapper.selectList(monitorWrapper);
                if (monitorList != null && monitorList.size() > 0){
                    for (BasicMonitorMap BasicMonitorMap: monitorList) {
                        BasicMonitorMap.setId(null);
                        BasicMonitorMap.setContractId(id);
                        basicMonitorMapMapper.insert(BasicMonitorMap);
                    }
                }
                // 将订单信息放到新的合同上
                QueryWrapper<BasicOrderMap> orderWrapper = new QueryWrapper<>();
                monitorWrapper.eq(BasicOrderMap.CONTRACT_ID, oldId);
                List<BasicOrderMap> orderList = basicOrderMapMapper.selectList(orderWrapper);
                if (orderList != null && orderList.size() > 0){
                    for (BasicOrderMap basicOrderMap: orderList) {
                        basicOrderMap.setId(null);
                        basicOrderMap.setContractId(id);
                        basicOrderMapMapper.insert(basicOrderMap);
                    }
                }
            }
        }
        return Result.success();
    }

    /**
     * 生成履行预警待阅
     * @return
     */
    public Result sendExecuteWaring(){
        List<FinanceInfoVo> financeInfoVoList = getExecuteWaringContract();
        if (financeInfoVoList != null && financeInfoVoList.size() > 0){
            for (FinanceInfoVo financeInfoVo: financeInfoVoList) {
                Map<String, String> map = isToSend(financeInfoVo);
                if (map.get("flag").equals("1")){
                    Result<UserVo>  result = userClient.get(Long.parseLong(financeInfoVo.getExecuterCode()));
                    if(result.isSuccess()){
                        //给某个id 发送一条待阅
                        UserVo userVo = result.getData();
                        String voucherName = "";
                        Integer day = Integer.parseInt(map.get("day"));
                        String formattedDateTime = map.get("time");
                        if(day >= 0){
                            voucherName = "[" + financeInfoVo.getContractName() + "]将于" + formattedDateTime + "履行财务收付款事项，请您知悉。";
                        }else{
                            voucherName = "[" + financeInfoVo.getContractName() + "]计划于" + formattedDateTime + "履行财务收付款事项尚未执行，请您知悉，感谢。";
                        }
                        commonService.sendProcessTask(userVo, financeInfoVo.getContractId().toString(), VoucherTypeEnums.CONTRACT_CONTRACT_EXECUTE, voucherName);
                        if (userVo.getCurrentDeputyAccount() != null){
                            Long orgId = userVo.getCurrentDeputyAccount().getOrgId();
                            Result<List<UserVo>> resultList = userClient.getByOrgCode(String.valueOf(orgId));
                            if (resultList.isSuccess()){
                                List<UserVo> userVoList = resultList.getData();
                                for (UserVo userVoExe: userVoList) {
                                    commonService.sendProcessTask(userVoExe, financeInfoVo.getContractId().toString(), VoucherTypeEnums.CONTRACT_CONTRACT_EXECUTE, voucherName);
                                }
                            }
                        }
                        Result<List<UserVo>> resultListCai = userClient.getByOrgCode("1001045");
                        if (resultListCai.isSuccess()){
                            List<UserVo> userVoList = resultListCai.getData();
                            for (UserVo userVoExe: userVoList) {
                                commonService.sendProcessTask(userVoExe, financeInfoVo.getContractId().toString(), VoucherTypeEnums.CONTRACT_CONTRACT_EXECUTE, voucherName);
                            }
                        }
                        if(day < 0){
                            Result<List<UserVo>> resultListShen = userClient.getByOrgCode("1001018");
                            if (resultListShen.isSuccess()){
                                List<UserVo> userVoList = resultListShen.getData();
                                for (UserVo userVoExe: userVoList) {
                                    commonService.sendProcessTask(userVoExe, financeInfoVo.getContractId().toString(), VoucherTypeEnums.CONTRACT_CONTRACT_EXECUTE, voucherName);
                                }
                            }
                        }
                    }
                }
            }
        }
        return Result.success();
    }

    /**
     * 获取需要预警的合同履行计划
     * @return
     */
    public List<FinanceInfoVo> getExecuteWaringContract(){
        return basicInfoMapper.getExecuteWaringContract();
    }

    private Map<String, String> isToSend(FinanceInfoVo financeInfoVo){
        Integer deadline = Integer.parseInt(financeInfoVo.getDeadline());
        Integer frequency = Integer.parseInt(financeInfoVo.getFrequency());
        // 用履行提醒时间除以履行提醒间隔计算提醒的次数
        Integer time = deadline / frequency;
        Map<String, String> map = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i <= time; i++){
            Integer day = deadline - frequency * i;
            if ("1".equals(financeInfoVo.getType())){
                String payWaring = financeInfoVo.getPayWaring();
                if (payWaring != null && !payWaring.equals("")){
                    if (payWaring.equals(day.toString()) || payWaring.equals("-1")){
                        map.put("flag", "1");
                        map.put("day", payWaring);
                        String formattedDateTime = financeInfoVo.getPlanPayTime().format(formatter);
                        map.put("time", formattedDateTime);
                        return map;
                    }
                }
            }else if ("2".equals(financeInfoVo.getType())){
                String incomeWaring = financeInfoVo.getIncomeWaring();
                if (incomeWaring != null && !incomeWaring.equals("")){
                    if (incomeWaring.equals(day.toString()) || incomeWaring.equals("-1")){
                        map.put("flag", "1");
                        map.put("day", incomeWaring);
                        String formattedDateTime = financeInfoVo.getPlanIncomeTime().format(formatter);
                        map.put("time", formattedDateTime);
                        return map;
                    }
                }
            }
        }
        map.put("flag", "0");
        return map;
    }

    /**
     * 生成合同履行期限预警待阅
     * @return
     */
    public Result sendContractWaring(){
        List<BasicInfoVo> basicInfoVoList = basicInfoMapper.getContractWaring();
        for (BasicInfoVo basicInfoVo: basicInfoVoList) {
            Map<String, String> map = isToSend(basicInfoVo);
            if (map.get("flag").equals("1")){
                Result<UserVo>  result = userClient.get(Long.parseLong(basicInfoVo.getExecuterCode()));
                if(result.isSuccess()){
                    //给某个id 发送一条待阅
                    UserVo userVo = result.getData();
                    String voucherName = "";
                    Integer day = Integer.parseInt(map.get("day"));
                    String formattedDateTime = map.get("time");
                    if(day >= 0){
                        voucherName = "[" + basicInfoVo.getContractName() + "]将于" + formattedDateTime + "履行完毕，请您知悉。。";
                    }else{
                        voucherName = "[" + basicInfoVo.getContractName() + "]计划于" + formattedDateTime + "履行完毕尚未执行，请您知悉，感谢。";
                    }
                    commonService.sendProcessTask(userVo, basicInfoVo.getId(), VoucherTypeEnums.CONTRACT_CONTRACT_EXECUTE, voucherName);
                    if (userVo.getCurrentDeputyAccount() != null){
                        Long orgId = userVo.getCurrentDeputyAccount().getOrgId();
                        Result<List<UserVo>> resultList = userClient.getByOrgCode(String.valueOf(orgId));
                        if (resultList.isSuccess()){
                            List<UserVo> userVoList = resultList.getData();
                            for (UserVo userVoExe: userVoList) {
                                commonService.sendProcessTask(userVoExe, basicInfoVo.getId(), VoucherTypeEnums.CONTRACT_CONTRACT_EXECUTE, voucherName);
                            }
                        }
                    }
                    Result<List<UserVo>> resultListCai = userClient.getByOrgCode("1001045");
                    if (resultListCai.isSuccess()){
                        List<UserVo> userVoList = resultListCai.getData();
                        for (UserVo userVoExe: userVoList) {
                            commonService.sendProcessTask(userVoExe, basicInfoVo.getId(), VoucherTypeEnums.CONTRACT_CONTRACT_EXECUTE, voucherName);
                        }
                    }
                    if(day < 0){
                        Result<List<UserVo>> resultListShen = userClient.getByOrgCode("1001018");
                        if (resultListShen.isSuccess()){
                            List<UserVo> userVoList = resultListShen.getData();
                            for (UserVo userVoExe: userVoList) {
                                commonService.sendProcessTask(userVoExe, basicInfoVo.getId(), VoucherTypeEnums.CONTRACT_CONTRACT_EXECUTE, voucherName);
                            }
                        }
                    }
                }
            }
        }
        return Result.success();
    }

    private Map<String, String> isToSend(BasicInfoVo basicInfoVo){
        Integer deadline = Integer.parseInt(basicInfoVo.getDeadline());
        Integer frequency = Integer.parseInt(basicInfoVo.getFrequency());
        // 用履行提醒时间除以履行提醒间隔计算提醒的次数
        Integer time = deadline / frequency;
        Map<String, String> map = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i <= time; i++){
            Integer day = deadline - frequency * i;
            String dayWaring = basicInfoVo.getDayWaring();
            if (dayWaring != null && !dayWaring.equals("")){
                if (dayWaring.equals(day.toString()) || dayWaring.equals("-1")){
                    map.put("flag", "1");
                    map.put("day", dayWaring);
                    String formattedDateTime = basicInfoVo.getExecuteEndTime().format(formatter);
                    map.put("time", formattedDateTime);
                    return map;
                }
            }
        }
        map.put("flag", "0");
        return map;
    }
}

