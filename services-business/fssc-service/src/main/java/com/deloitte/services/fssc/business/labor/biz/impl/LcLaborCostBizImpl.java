package com.deloitte.services.fssc.business.labor.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.labor.param.LcLaborCostLineQueryForm;
import com.deloitte.platform.api.fssc.labor.vo.*;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.labor.biz.LcLaborCostBiz;
import com.deloitte.services.fssc.business.labor.entity.GePrivatePaymentList;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCost;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCostLineChina;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCostLineForeign;
import com.deloitte.services.fssc.business.labor.mapper.GePrivatePaymentListMapper;
import com.deloitte.services.fssc.business.labor.service.IGePrivatePaymentListService;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostLineChinaService;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostLineForeignService;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostService;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.BigDecimalUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LcLaborCostBizImpl implements LcLaborCostBiz {

    @Autowired
    private ILcLaborCostService lcLaborCostService;
    @Autowired
    private IGePrivatePaymentListService privatePaymentListService;
    @Autowired
    private ILcLaborCostLineChinaService lcLaborCostLineChinaService;
    @Autowired
    private ILcLaborCostLineForeignService lcLaborCostLineForeignService;
    @Autowired
    private BpmTaskBiz bpmTaskBiz;
    @Autowired
    private IFileService fileService;
    @Autowired
    private GePrivatePaymentListMapper gePrivatePaymentListMapper;

    @Autowired
    private IBudgetProjectService budgetProjectService;
    /**
     * 保存劳务费报账单
     *
     * @param lcLaborCostForm
     * @return
     */
    @Transactional
    public LcLaborCost saveOrUpdateLaborCost(LcLaborCostForm lcLaborCostForm) {
        //验证单据是否存在
        FsscCommonUtil.valiHasData(lcLaborCostForm.getId()
                ,FsscTableNameEums.LC_LABOR_COST.getValue());
        //单据号待生成
        LcLaborCost lcLaborCost = new BeanUtils<LcLaborCost>().copyObj(lcLaborCostForm, LcLaborCost.class);
        Long projectId = lcLaborCost.getProjectId();
        if(projectId!=null){
            BudgetProject project = budgetProjectService.getById(projectId);
            if(project!=null){
                lcLaborCost.setProjectCode(project.getProjectCode());
                lcLaborCost.setProjectName(project.getProjectName());
                lcLaborCost.setProjectUnitCode(project.getResponsibleUnitCode());
                lcLaborCost.setProjectUnitName(project.getResponsibleUnitName());
                lcLaborCost.setFsscCode(project.getFsscCode());
            }
        }

        boolean saveOrUpdateFlag = lcLaborCostService.saveOrUpdate(lcLaborCost);
        AssertUtils.asserts(saveOrUpdateFlag, FsscErrorType.DATA_IS_NOT_LATEST);
        lcLaborCostForm.setId(lcLaborCost.getId());
        AssertUtils.asserts(CollectionUtils.isNotEmpty(lcLaborCostForm.getLaborCostLineChinaForms())
        ||CollectionUtils.isNotEmpty(lcLaborCostForm.getLaborCostLineForeignForms()),FsscErrorType.GE_LINE_IS_NOT_EMPTY);
        //保存发放明细中国籍
        saveLaborChina(lcLaborCostForm);
        //保存发放明细外国籍
        saveLaborForeign(lcLaborCostForm);
        //保存对私付款清单
        //savePrivatePaymentList(lcLaborCostForm);
        //文件列表保存
        List<Long> fileIds = lcLaborCostForm.getFileIds();
        if(fileIds!=null){
            fileIds.removeAll(Collections.singleton(null));
        }
        if(CollectionUtils.isNotEmpty(fileIds)){
            QueryWrapper<File> fileQueryWrapper=new QueryWrapper<>();
            fileQueryWrapper.eq(File.DOCUMENT_ID,lcLaborCost.getId())
                    .eq(File.DOCUMENT_TYPE,FsscTableNameEums.LC_LABOR_COST.getValue())
                    .notIn(File.ID,fileIds);
            fileService.remove(fileQueryWrapper);

            Collection<File> files = fileService.listByIds(fileIds);
            AssertUtils.asserts(CollectionUtils.isNotEmpty(files),
                    FsscErrorType.FILE_IS_NULL);
            files.stream().forEach(ka->ka.setDocumentId(lcLaborCost.getId()));
            fileService.saveOrUpdateBatch(files);
        }


        return lcLaborCostService.getById(lcLaborCost.getId());
    }

    /**
     * 根据id 查询
     *
     * @param id
     * @return
     */
    @Override
    public LcLaborCostVo getById(Long id) {
        AssertUtils.asserts(id != null, FsscErrorType.ID_CANT_BE_NULL);
        LcLaborCost lcLaborCost = lcLaborCostService.getById(id);
        LcLaborCostVo lcLaborCostVo = new BeanUtils<LcLaborCostVo>().copyObj(lcLaborCost, LcLaborCostVo.class);

        //发放明细中国籍
        QueryWrapper<LcLaborCostLineChina> chinaQueryWrapper = new QueryWrapper<>();
        chinaQueryWrapper.eq(LcLaborCostLineChina.DOCUMENT_ID, id);
        List<LcLaborCostLineChinaVo> lineChinaVos =
                new BeanUtils<LcLaborCostLineChinaVo>().copyObjs(lcLaborCostLineChinaService.list(chinaQueryWrapper)
                        , LcLaborCostLineChinaVo.class);
        lcLaborCostVo.setLaborCostLineChinaVos(lineChinaVos);
        //发放明细外国籍
        QueryWrapper<LcLaborCostLineForeign> foreignQueryWrapper = new QueryWrapper<>();
        foreignQueryWrapper.eq(LcLaborCostLineChina.DOCUMENT_ID, id);
        List<LcLaborCostLineForeignVo> foreignVos =
                new BeanUtils<LcLaborCostLineForeignVo>().copyObjs(lcLaborCostLineForeignService.list(foreignQueryWrapper),
                        LcLaborCostLineForeignVo.class);
        lcLaborCostVo.setLaborCostLineForeignVos(foreignVos);
        //对私付款
        QueryWrapper<GePrivatePaymentList> gePrivatePaymentListQueryWrapper = new QueryWrapper<>();
        gePrivatePaymentListQueryWrapper.eq(LcLaborCostLineChina.DOCUMENT_ID, id)
                .eq(GePrivatePaymentList.DOCUMENT_TYPE, FsscTableNameEums.LC_LABOR_COST.getValue());
        List<GePrivatePaymentListVo> privatePaymentListVos =
                new BeanUtils<GePrivatePaymentListVo>().copyObjs(privatePaymentListService.list(gePrivatePaymentListQueryWrapper),
                        GePrivatePaymentListVo.class);
        lcLaborCostVo.setPrivatePaymentListVos(privatePaymentListVos);
        //审批历史
        try {
            lcLaborCostVo.setBpmProcessTaskVos(bpmTaskBiz.findHistory(id, FsscTableNameEums.LC_LABOR_COST.getValue()));
        } catch (FSSCException e) {
            log.error(e.getMessage());
        }
        //预制凭证

        return lcLaborCostVo;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        AssertUtils.asserts(id != null, FsscErrorType.ID_CANT_BE_NULL);
        LcLaborCost laborCost = lcLaborCostService.getById(id);
        String documentStatus = laborCost.getDocumentStatus();
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus)||
                FsscEums.RECALLED.getValue().equals(documentStatus)||
                FsscEums.REJECTED.getValue().equals(documentStatus),FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);
        removeSign(id);
        String documentType = GePrivatePaymentList.DOCUMENT_TYPE;
        String documentId = GePrivatePaymentList.DOCUMENT_ID;

        //删除发放明细中国籍
        QueryWrapper<LcLaborCostLineChina> lineChinaQueryWrapper = new QueryWrapper<>();
        lineChinaQueryWrapper.eq(documentId, id);
        lcLaborCostLineChinaService.remove(lineChinaQueryWrapper);
        //删除发放明细外国籍
        QueryWrapper<LcLaborCostLineForeign> lineForeignQueryWrapper = new QueryWrapper<>();
        lineForeignQueryWrapper.eq(documentId, id);
        lcLaborCostLineForeignService.remove(lineForeignQueryWrapper);
        //删除对私付款
        QueryWrapper<GePrivatePaymentList> privatePaymentListQueryWrapper = new QueryWrapper<>();
        privatePaymentListQueryWrapper.eq(documentType, FsscTableNameEums.LC_LABOR_COST.getValue()).eq(documentId, id);
        privatePaymentListService.remove(privatePaymentListQueryWrapper);

        lcLaborCostService.removeById(id);
    }
    public void removeSign(Long id) {
        if (id != null) {
            LcLaborCost lcLaborCost = lcLaborCostService.getById(id);
            Long paymentId = lcLaborCost.getPayMentId();
            if (paymentId != null) {
                lcLaborCost.setPayMentId(null);
                lcLaborCostService.updateById(lcLaborCost);
            }
        }
    }
    @Override
    public IPage<LcLaborCostLineChinaAndForeignVo> findLineDetail(LcLaborCostLineQueryForm queryForm) {

        IPage<LcLaborCostLineChinaAndForeignVo> page = new Page<LcLaborCostLineChinaAndForeignVo>
                (queryForm.getCurrentPage(), queryForm.getPageSize());
        List<LcLaborCostLineChinaAndForeignVo> voList = gePrivatePaymentListMapper.findPrivatePayList(page, queryForm);
        page.setRecords(voList);
        return page;
    }

    @Override
    public void doTax(Long id) {

        LcLaborCost lcLaborCost = lcLaborCostService.getById(id);
        String isDeduted = lcLaborCost.getIsDeduted();
        AssertUtils.asserts(!"Y".equals(isDeduted), FsscErrorType.HAS_DUDETED_TAX);
        lcLaborCost.setIsDeduted("Y");

        //发放明细中国籍
        QueryWrapper<LcLaborCostLineChina> chinaQueryWrapper = new QueryWrapper<>();
        chinaQueryWrapper.eq(LcLaborCostLineChina.DOCUMENT_ID, id);
        List<LcLaborCostLineChina> costLineChinas = lcLaborCostLineChinaService.list(chinaQueryWrapper);
        BigDecimal totalRealHead=BigDecimal.ZERO;
        BigDecimal totalTaxAmount=BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(costLineChinas)) {
            for (LcLaborCostLineChina china : costLineChinas) {
                //当前扣税金额
                BigDecimal deductedAmount = BigDecimalUtil.convert(china.getDeductedAmount());
                //当前应发
                BigDecimal sholdAmount = BigDecimalUtil.convert(china.getShouldGiveAmount());
                //总共应发
                BigDecimal totalShould = BigDecimalUtil.convert(china.getShouldGiveAmount());

                LocalDateTime createTime = china.getCreateTime();
                String formatTime = LocalDateTimeUtils.formatTime(createTime, "yyyy-MM");
                //获得证件号
                String certNum = china.getCertNum();
                //根据证件号查询中国籍发放
                QueryWrapper<LcLaborCostLineChina> chinaQueryWrapper2 = new QueryWrapper<>();
                chinaQueryWrapper2.eq(LcLaborCostLineChina.CERT_NUM, certNum)
                        .apply("to_char(CREATE_TIME,'yyyy-mm')={0}", formatTime);
                List<LcLaborCostLineChina> list = lcLaborCostLineChinaService.list(chinaQueryWrapper2);
                //List<LcLaborCostLineChina> newChina = new ArrayList<>();
                for (LcLaborCostLineChina lineChina : list) {
                    if (lineChina.getDocumentId() != null) {
                        LcLaborCost service = lcLaborCostService.getById(lineChina.getDocumentId());
                        if (service != null) {
                            if ((FsscEums.APPROVED.getValue().equals(service.getDocumentStatus())
                                    ||FsscEums.HAS_ACCOUTED.getValue().equals(service.getDocumentStatus()))
                                    && !service.getId().equals(id)) {
                                //计算总税额
                                deductedAmount = deductedAmount.add(BigDecimalUtil.convert(lineChina.getDeductedAmount()));
                                //计算总应发
                                totalShould = totalShould.add(BigDecimalUtil.convert(lineChina.getShouldGiveAmount()));
                                //newChina.add(lineChina);
                            }
                        }
                    }
                }

                //根据总应发计算税额
                log.info("中国籍总应发:{}",totalShould.toString());
                BigDecimal resultTotalTax = caclucateTax(totalShould).subtract(deductedAmount);
                //实发金额
                BigDecimal realAmount = sholdAmount.subtract(resultTotalTax);
                //修改原税额
                china.setDeductedAmount(resultTotalTax);
                totalTaxAmount=totalTaxAmount.add(resultTotalTax);
                china.setRealGiveAmount(sholdAmount.subtract(resultTotalTax));
                totalRealHead=china.getRealGiveAmount().add(totalRealHead);
                //生成对私付款清单
                GePrivatePaymentList privatePaymentList = new GePrivatePaymentList();
                privatePaymentList.setBanAccount(china.getBankAccount());
                privatePaymentList.setBankBame(china.getBankName());
                privatePaymentList.setBankCode(china.getBankCode());
                privatePaymentList.setCertNum(china.getCertNum());
                privatePaymentList.setCertType(china.getCertType());
                privatePaymentList.setDocumentId(china.getDocumentId());
                privatePaymentList.setDocumentType(FsscTableNameEums.LC_LABOR_COST.getValue());
                privatePaymentList.setInterBranchNumber(china.getInterBranchNumber());
                privatePaymentList.setPayAmount(realAmount);
                privatePaymentList.setRecieveUserId(china.getRecieveUserId());
                privatePaymentList.setRecieveUserName(china.getRecieveUserName());
                privatePaymentList.setPayTime(LocalDateTime.now());
                privatePaymentList.setPayStatus("NO_PAY");
                gePrivatePaymentListMapper.insert(privatePaymentList);

            }
            lcLaborCostLineChinaService.saveOrUpdateBatch(costLineChinas);
        }
        //发放明细外国籍
        QueryWrapper<LcLaborCostLineForeign> foreignQueryWrapper = new QueryWrapper<>();
        foreignQueryWrapper.eq(LcLaborCostLineChina.DOCUMENT_ID, id);
        List<LcLaborCostLineForeign> lineForeigns = lcLaborCostLineForeignService.list(foreignQueryWrapper);
        if (CollectionUtils.isNotEmpty(lineForeigns)) {
            for (LcLaborCostLineForeign foreign : lineForeigns) {
                //当前扣税金额
                BigDecimal deductedAmount = BigDecimalUtil.convert(foreign.getDeductedAmount());
                //当前应发
                BigDecimal sholdAmount = BigDecimalUtil.convert(foreign.getShouldGiveAmount());
                //总共应发
                BigDecimal totalShould = BigDecimalUtil.convert(foreign.getShouldGiveAmount());

                LocalDateTime createTime = foreign.getCreateTime();
                String formatTime = LocalDateTimeUtils.formatTime(createTime, "yyyy-MM");
                //获得证件号
                String certNum = foreign.getCertNum();
                //根据证件号查询中国籍发放
                QueryWrapper<LcLaborCostLineForeign> chinaQueryWrapper2 = new QueryWrapper<>();
                chinaQueryWrapper2.eq(LcLaborCostLineForeign.CERT_NUM, certNum)
                        .apply("to_char(CREATE_TIME,'yyyy-mm')={0}", formatTime);
                List<LcLaborCostLineForeign> list = lcLaborCostLineForeignService.list(chinaQueryWrapper2);
                //List<LcLaborCostLineForeign> newForeign = new ArrayList<>();
                for (LcLaborCostLineForeign lineForeign : list) {
                    if (lineForeign.getDocumentId() != null) {
                        LcLaborCost service = lcLaborCostService.getById(lineForeign.getDocumentId());
                        if (service != null) {
                            if ((FsscEums.APPROVED.getValue().equals(service.getDocumentStatus())
                                    ||FsscEums.HAS_ACCOUTED.getValue().equals(service.getDocumentStatus()))
                                    && !service.getId().equals(id)) {
                                //计算总税额
                                deductedAmount = deductedAmount.add(BigDecimalUtil.convert(lineForeign.getDeductedAmount()));
                                //计算总应发
                                totalShould = totalShould.add(BigDecimalUtil.convert(lineForeign.getShouldGiveAmount()));
                                //newForeign.add(lineForeign);
                            }
                        }
                    }
                }

                //根据总应发计算税额
                log.info("外国籍总应发:{}",totalShould.toString());
                BigDecimal resultTotalTax = caclucateTaxForeign(totalShould).subtract(deductedAmount);
                //实发金额
                BigDecimal realAmount = sholdAmount.subtract(resultTotalTax);
                //修改原税额
                foreign.setDeductedAmount(resultTotalTax);

                totalTaxAmount=totalTaxAmount.add(resultTotalTax);

                foreign.setRealGiveAmount(sholdAmount.subtract(resultTotalTax));
                totalRealHead=foreign.getRealGiveAmount().add(totalRealHead);
                //生成对私付款清单
                GePrivatePaymentList privatePaymentList = new GePrivatePaymentList();
                privatePaymentList.setBanAccount(foreign.getBankAccount());
                privatePaymentList.setBankBame(foreign.getBankName());
                privatePaymentList.setBankCode(foreign.getBankCode());
                privatePaymentList.setCertNum(foreign.getCertNum());
                privatePaymentList.setCertType(foreign.getCertType());
                privatePaymentList.setDocumentId(foreign.getDocumentId());
                privatePaymentList.setDocumentType(FsscTableNameEums.LC_LABOR_COST.getValue());
                privatePaymentList.setInterBranchNumber(foreign.getInterBranchNumber());
                privatePaymentList.setPayAmount(realAmount);
                privatePaymentList.setRecieveUserId(foreign.getRecieveUserId());
                privatePaymentList.setRecieveUserName(foreign.getRecieveUserName());
                privatePaymentList.setPayTime(LocalDateTime.now());
                privatePaymentList.setPayStatus("NO_PAY");
                gePrivatePaymentListMapper.insert(privatePaymentList);
            }
            lcLaborCostLineForeignService.saveOrUpdateBatch(lineForeigns);
        }
        lcLaborCost.setRealGiveTotalAmount(totalRealHead);
        lcLaborCost.setDeductedTotalAmount(totalTaxAmount);
        lcLaborCost.setNoPaidAmount(totalRealHead);
        lcLaborCost.setPaidAmount(BigDecimal.ZERO);
        lcLaborCostService.updateById(lcLaborCost);
    }

    /**
     * 计税
     *
     * @param shloudAmount
     * @return
     */
    public BigDecimal caclucateTax(BigDecimal shloudAmount) {
        BigDecimal aa=shloudAmount;
        if(aa.compareTo(new BigDecimal("4000"))<=0){
            aa=aa.subtract(new BigDecimal("800"));
        }else {
            aa=aa.multiply(new BigDecimal("0.8"));
        }

        Double d = aa.doubleValue();
        BigDecimal bigDecimal = BigDecimal.ZERO;
        //不超过4000
        if (d <= 20000) {
            bigDecimal = aa.multiply(new BigDecimal("0.2"));
        } else if (d <= 50000) {
            bigDecimal = aa.multiply(new BigDecimal("0.3"))
                    .subtract(new BigDecimal("2000"));
        } else {
            bigDecimal = aa.multiply(new BigDecimal("0.4")).subtract(new BigDecimal("7000"));
        }
        if (shloudAmount.doubleValue() <= 800) {
            return BigDecimal.ZERO;
        }
        return bigDecimal;
    }


    public BigDecimal caclucateTaxForeign(BigDecimal shloudAmount){
        BigDecimal aa=shloudAmount.multiply(new BigDecimal("0.8"));
        Double d = aa.doubleValue();
        BigDecimal bigDecimal = BigDecimal.ZERO;

        if(d<=3000){
            bigDecimal=aa.multiply(new BigDecimal("0.03"));
        }else if(d<=12000){
            bigDecimal=aa.multiply(new BigDecimal("0.1")).subtract(new BigDecimal("210"));
        }else if(d<=25000){
            bigDecimal=aa.multiply(new BigDecimal("0.2")).subtract(new BigDecimal("1410"));
        }else if(d<=35000){
            bigDecimal=aa.multiply(new BigDecimal("0.25")).subtract(new BigDecimal("2660"));
        }else if(d<=55000){
            bigDecimal=aa.multiply(new BigDecimal("0.3")).subtract(new BigDecimal("4410"));
        }else if(d<=80000){
            bigDecimal=aa.multiply(new BigDecimal("0.35")).subtract(new BigDecimal("7160"));
        }else {
            bigDecimal=aa.multiply(new BigDecimal("0.45")).subtract(new BigDecimal("15160"));
        }
        return bigDecimal;
    }

//    /**
//     * 保存对私付款清单
//     *
//     * @param lcLaborCostForm
//     */
//    private void savePrivatePaymentList(LcLaborCostForm lcLaborCostForm) {
//        List<GePrivatePaymentList> paymentLists = new BeanUtils<GePrivatePaymentList>()
//                .copyObjs(lcLaborCostForm.getPrivatePaymentListForms(), GePrivatePaymentList.class);
//        //先删除差集的行
//        QueryWrapper<GePrivatePaymentList> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(GePrivatePaymentList.DOCUMENT_ID, lcLaborCostForm.getId())
//                .eq(GePrivatePaymentList.DOCUMENT_TYPE, FsscTableNameEums.LC_LABOR_COST.getValue());
//        List<Long> cuLineIds = paymentLists.stream().map(k -> k.getId()).collect(Collectors.toList());
//        cuLineIds.removeAll(Collections.singleton(null));
//        if (CollectionUtils.isNotEmpty(paymentLists)) {
//            queryWrapper.notIn(GePrivatePaymentList.ID, cuLineIds);
//        }
//        privatePaymentListService.remove(queryWrapper);
//        for (GePrivatePaymentList paymentList : paymentLists) {
//            paymentList.setDocumentType(FsscTableNameEums.LC_LABOR_COST.getValue());
//            paymentList.setDocumentId(lcLaborCostForm.getId());
//        }
//        privatePaymentListService.saveOrUpdateBatch(paymentLists);
//    }

    /**
     * 保存发放明细外国籍
     *
     * @param lcLaborCostForm
     */
    private void saveLaborForeign(LcLaborCostForm lcLaborCostForm) {

        List<LcLaborCostLineForeign> lcLaborCostLineForeigns = new BeanUtils<LcLaborCostLineForeign>()
                .copyObjs(lcLaborCostForm.getLaborCostLineForeignForms(), LcLaborCostLineForeign.class);
        //先删除差集的行
        QueryWrapper<LcLaborCostLineForeign> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(LcLaborCostLineForeign.DOCUMENT_ID, lcLaborCostForm.getId());
        List<Long> cuLineIds = lcLaborCostLineForeigns.stream().map(k -> k.getId()).collect(Collectors.toList());
        cuLineIds.removeAll(Collections.singleton(null));
        if (CollectionUtils.isNotEmpty(lcLaborCostLineForeigns)) {
            queryWrapper.notIn(LcLaborCostLineForeign.ID, cuLineIds);
        }
        lcLaborCostLineForeignService.remove(queryWrapper);
        if (CollectionUtils.isNotEmpty(lcLaborCostLineForeigns)) {
            Set<String> idsub=new HashSet<>();
            for (LcLaborCostLineForeign foreign:lcLaborCostLineForeigns){
                foreign.setDocumentId(lcLaborCostForm.getId());
                foreign.setDocumentType(FsscTableNameEums.LC_LABOR_COST.getValue());
                idsub.add(foreign.getRecieveUserId()+""+foreign.getSubTypeId());
            }
            AssertUtils.asserts(idsub.size()==lcLaborCostLineForeigns.size(),FsscErrorType.SAMEPERSON_CANT_SAMESUBTYPE);
            lcLaborCostLineForeignService.saveOrUpdateBatch(lcLaborCostLineForeigns);
        }

    }

    /**
     * 保存发放明细中国籍
     *
     * @param lcLaborCostForm
     */
    private void saveLaborChina(LcLaborCostForm lcLaborCostForm) {

        List<LcLaborCostLineChina> lcLaborCostLineChinas = new BeanUtils<LcLaborCostLineChina>()
                .copyObjs(lcLaborCostForm.getLaborCostLineChinaForms(), LcLaborCostLineChina.class);
        //先删除差集的行
        QueryWrapper<LcLaborCostLineChina> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(LcLaborCostLineChina.DOCUMENT_ID, lcLaborCostForm.getId());
        List<Long> cuLineIds = lcLaborCostLineChinas.stream().map(k -> k.getId()).collect(Collectors.toList());
        cuLineIds.removeAll(Collections.singleton(null));
        if (CollectionUtils.isNotEmpty(lcLaborCostLineChinas)) {
            queryWrapper.notIn(LcLaborCostLineChina.ID, cuLineIds);
        }
        lcLaborCostLineChinaService.remove(queryWrapper);
        if (CollectionUtils.isNotEmpty(lcLaborCostLineChinas)) {
            Set<String> idsub=new HashSet<>();
            for (LcLaborCostLineChina china:lcLaborCostLineChinas){
                china.setDocumentId(lcLaborCostForm.getId());
                china.setDocumentType(FsscTableNameEums.LC_LABOR_COST.getValue());
                idsub.add(china.getRecieveUserId()+""+china.getSubTypeId());
            }
            AssertUtils.asserts(idsub.size()==lcLaborCostLineChinas.size(),FsscErrorType.SAMEPERSON_CANT_SAMESUBTYPE);
            lcLaborCostLineChinaService.saveOrUpdateBatch(lcLaborCostLineChinas);
        }

    }


}
