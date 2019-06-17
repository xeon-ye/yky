package com.deloitte.services.fssc.system.bank.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.bank.param.BankInfoQueryForm;
import com.deloitte.platform.api.fssc.bank.vo.BankInfoForm;
import com.deloitte.platform.api.fssc.bank.vo.BankInfoVo;
import com.deloitte.platform.api.fssc.bank.vo.DataAuditHisVo;
import com.deloitte.platform.api.fssc.unit.vo.ModifyUnitStatusForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.bank.entity.BankInfo;
import com.deloitte.services.fssc.system.bank.entity.BankUnitInfo;
import com.deloitte.services.fssc.system.bank.entity.DataAuditHis;
import com.deloitte.services.fssc.system.bank.service.IBankInfoService;
import com.deloitte.services.fssc.system.bank.service.IBankUnitInfoService;
import com.deloitte.services.fssc.system.bank.service.IDataAuditHisService;
import com.deloitte.services.fssc.system.util.BaseBusinessUtil;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.DocumentNumberUtil;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * @Author : qiliao
 * @Date : Create in 2019-02-26
 * @Description :   BankInfo控制器实现类
 * @Modified :
 */
@Api(tags = "银行基本信息操作接口")
@Slf4j
@Controller
@RequestMapping(value = "bank/bank-info")
public class BankInfoController{

    @Autowired
    public IBankInfoService  bankInfoService;

    @Autowired
    private IDataAuditHisService auditHisService;
    @Autowired
    private FsscCommonServices fsscCommonServices;
    @Autowired
    private IBankUnitInfoService bankUnitInfoService;


    @ApiOperation(value = "新增修改银行基本信息", notes = "新增修改银行基本信息")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/addOrUpdateBaseBank")
    @ResponseBody
    @Transactional
    public Result addOrUpdateBaseBank(@Valid @ApiParam(name="bankInfoForm",value="新增BankInfo的form表单",required=true)
                                  @RequestBody BankInfoForm bankInfoForm) {
        log.info("form:{}",  bankInfoForm.toString());
        if(bankInfoForm.getId()==null){
            bankInfoForm.setBankCode(DocumentNumberUtil.generateBankCode());
        }
        BankInfo bankInfo =new BeanUtils<BankInfo>().copyObj(bankInfoForm,BankInfo.class);
        return Result.success( bankInfoService.saveOrUpdate(bankInfo));
    }


    @ApiOperation(value = "删除BankInfo", notes = "根据url的id来指定删除对象")
    @DeleteMapping(value = "deleteByIds")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result delete(@RequestBody List<Long> ids) {
        log.info("ids：{}", ids);
        Collection<BankInfo> bankUnitInfos = bankInfoService.listByIds(ids);
        if(CollectionUtils.isNotEmpty(bankUnitInfos)){
            for (BankInfo info:bankUnitInfos){
                AssertUtils.asserts(FsscEums.NEW.getValue().equals(info.getIsValid())
                                ||FsscEums.UN_VALID.getValue().equals(info.getIsValid()),
                        FsscErrorType.ONLY_DELETE_NEW);
            }
        }
        QueryWrapper<BankUnitInfo> bankUnitInfoQueryWrapper=new QueryWrapper<>();
        bankUnitInfoQueryWrapper.in(BankUnitInfo.BANK_ID,ids);
        List<BankUnitInfo> infos = bankUnitInfoService.list(bankUnitInfoQueryWrapper);
        AssertUtils.asserts(CollectionUtils.isEmpty(infos),FsscErrorType.REFERENCES_BY_OTHER_FOR_DELETE);
        bankInfoService.removeByIds(ids);
        return Result.success();
    }


    @ApiOperation(value = "分页查询BankInfo", notes = "根据条件查询BankInfo分页数据")
    @GetMapping(value = "/page/conditions")
    @ResponseBody
    public Result<IPage<BankInfoVo>> search(@Valid  @ApiParam(name="bankInfoQueryForm",value="BankInfo查询参数",required=true)
                                                        BankInfoQueryForm bankInfoQueryForm) {
        log.info("search with bankInfoQueryForm:{}", JSON.toJSONString(bankInfoQueryForm));
        IPage<BankInfo> bankInfoPage=bankInfoService.selectPage(bankInfoQueryForm);
        IPage<BankInfoVo> bankInfoVoPage=new BeanUtils<BankInfoVo>().copyPageObjs(bankInfoPage,BankInfoVo.class);
        return Result.success(bankInfoVoPage);
    }



    @ApiOperation(value = "查询审批历史", notes = "查询审批历史")
    @GetMapping(value ="/baseBankAuditHis")
    @ResponseBody
    public Result<DataAuditHisVo> findHis(@RequestParam Long bankId) {
        QueryWrapper<DataAuditHis> wrapper = new QueryWrapper<>();
        wrapper.eq(DataAuditHis.DOCUMENT_ID, bankId).eq(DataAuditHis.DOCUMENT_TYPE,
                FsscEums.BASE_BANK_TABLE.getValue())
        .orderByDesc(DataAuditHis.CREATE_TIME);
        List<DataAuditHis> list = auditHisService.list(wrapper);
        List<DataAuditHisVo> unitAuditHisVoList=new BeanUtils<DataAuditHisVo>().copyObjs(list, DataAuditHisVo.class);
        return Result.success(unitAuditHisVoList);
    }



    /**
     * 方法名: export 方法描述: 导出对公费用报账单
     */
    @GetMapping(value =  "/exportExcel")
    @ResponseBody
    public void exportExcel(BankInfoQueryForm bankInfoQueryForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(bankInfoQueryForm));
        Map<String,String> statusMap=fsscCommonServices.getValueByCode(fsscCommonServices.findDicValueList(FsscEums.IS_VALID.getValue()));

        List<BankInfo> records = bankInfoService.selectPage(bankInfoQueryForm).getRecords();
        String[] title = {"序号", "银行代码", "银行名称", "银行简称","银行国际代码", "分行名称", "联行号", "地址"
                ,  "状态", "备注"};
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s:title){
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String fileName = "银行基础信息";
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            BankInfo vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getBankCode();
            content[i][2] = vo.getBankName();
            content[i][3] = vo.getBankSimpleName();
            content[i][4] = vo.getBankInternationalCode();
            content[i][5] = vo.getBranchBankName();
            content[i][6] = vo.getInterBranchNumber();
            content[i][7] = vo.getAddress();
//            content[i][8] = vo.getCreateUserName();
            content[i][8] = statusMap.get(vo.getIsValid());
            content[i][9] = vo.getRemark();
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList,FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();

    }




    @ApiOperation(value = "提交,撤回,失效,修改状态", notes = "修改状态")
    @PostMapping(value = "/modifyUnitStatus")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result modifyUnitStatus(@RequestBody @Valid ModifyUnitStatusForm form) {
        List<Long> ids = form.getIds();
        String status = form.getStatus();
        QueryWrapper<BankInfo> wrapper = new QueryWrapper<>();
        wrapper.in(BankInfo.ID, ids);
        List<BankInfo> list = bankInfoService.list(wrapper);
        for (BankInfo info : list) {
            String isValid = info.getIsValid();
            String auditStatus = info.getAuditStatus();
            BaseBusinessUtil.checkAuditStatus(isValid,status);
            BaseBusinessUtil.doModifyStatus(info,status,auditStatus);
        }
        bankInfoService.updateBatchById(list);
        return Result.success();
    }


    @ApiOperation(value = "审批通过或者审批拒绝", notes = "通过或者拒绝")
    @PostMapping(value = "/doRefusedOrPass")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result refusedOrPass(@RequestBody @Valid ModifyUnitStatusForm form) {
        List<Long> ids = form.getIds();
        String passOrRefused = form.getPassOrRefused();
        List<BankInfo> unitInfos = (List<BankInfo>) bankInfoService.listByIds(ids);
        String refusedReason = form.getRefusedReason();
        List<DataAuditHis> hisList = new ArrayList<>();
        BaseBusinessUtil.refusedOrPass(passOrRefused,refusedReason,hisList,unitInfos);
        if (!CollectionUtils.isEmpty(hisList)) {
            bankInfoService.updateBatchById(unitInfos);
            auditHisService.saveOrUpdateBatch(hisList);
        }
        return Result.success();
    }

    @ApiOperation(value = "查询单个银行信息", notes = "查询单个银行信息")
    @GetMapping(value = "findOneById")
    @ResponseBody
    public Result<BankInfoVo> findOneById(Long bankId){
        if(bankId==null){
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
        BankInfo byId = bankInfoService.getById(bankId);
        if(byId!=null){
            return Result.success(byId);
        }else {
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
    }

}



