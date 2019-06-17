package com.deloitte.services.fssc.system.bank.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.bank.BankUnitInfoClient;
import com.deloitte.platform.api.fssc.bank.param.BankUnitInfoQueryForm;
import com.deloitte.platform.api.fssc.bank.vo.BankUnitInfoForm;
import com.deloitte.platform.api.fssc.bank.vo.BankUnitInfoVo;
import com.deloitte.platform.api.fssc.bank.vo.DataAuditHisVo;
import com.deloitte.platform.api.fssc.unit.vo.ModifyUnitStatusForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.system.bank.entity.BankUnitInfo;
import com.deloitte.services.fssc.system.bank.entity.DataAuditHis;
import com.deloitte.services.fssc.system.bank.service.IBankUnitInfoService;
import com.deloitte.services.fssc.system.bank.service.IDataAuditHisService;
import com.deloitte.services.fssc.system.util.BaseBusinessUtil;
import com.deloitte.services.fssc.util.AssertUtils;
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
 * @Date : Create in 2019-02-25
 * @Description :   BankInfo控制器实现类
 * @Modified :
 */
@Api(tags = "单位银行账户操作接口")
@Slf4j
@Controller
@RequestMapping(value = "bank/bank-unit-info")
public class BankUnitInfoController implements BankUnitInfoClient {

    @Autowired
    public IBankUnitInfoService bankUnitInfoService;

    @Autowired
    private IDataAuditHisService auditHisService;



    @Autowired
    private FsscCommonServices fsscCommonServices;

    @ApiOperation(value = "新增修改单位银行信息", notes = "新增修改单位银行信息")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @Transactional
    @PostMapping(value = "/addOrUpdateBankUnit")
    @ResponseBody
    public Result addOrUpdateBankUnit(@Valid
                          @ApiParam(name="bankUnitInfoForm",value="新增BankInfo的form表单",required=true)
                                @RequestBody  BankUnitInfoForm bankUnitInfoForm) {
        log.info("form:{}",  JSON.toJSONString(bankUnitInfoForm));
        BankUnitInfo bankUnitInfo =new BeanUtils<BankUnitInfo>().copyObj(bankUnitInfoForm, BankUnitInfo.class);
        boolean saveOrUpdate = bankUnitInfoService.saveOrUpdate(bankUnitInfo);
        return Result.success(saveOrUpdate);
    }


    @ApiOperation(value = "删除单位银行", notes = "根据url的id来指定删除对象")
    @ResponseBody
    @DeleteMapping(value = "deleteByIds")
    public Result delete(@RequestBody List<Long> ids) {
        Collection<BankUnitInfo> bankUnitInfos = bankUnitInfoService.listByIds(ids);
        if(CollectionUtils.isNotEmpty(bankUnitInfos)){
            for (BankUnitInfo info:bankUnitInfos){
                AssertUtils.asserts(FsscEums.NEW.getValue().equals(info.getIsValid())
                        ||FsscEums.UN_VALID.getValue().equals(info.getIsValid()),
                        FsscErrorType.ONLY_DELETE_NEW);
            }
        }
        bankUnitInfoService.removeByIds(ids);
        return Result.success();
    }





    @ApiOperation(value = "分页查询单位银行", notes = "根据条件查询单位银行分页数据")
    @GetMapping(value = "/page/conditions")
    @ResponseBody
    public Result<IPage<BankUnitInfoVo>> search(@Valid @ApiParam(name="bankInfoQueryForm",value="BankInfo查询参数",required=true)
                                                     BankUnitInfoQueryForm bankInfoQueryForm) {
        log.info("search with bankInfoQueryForm:{}",JSON.toJSONString(bankInfoQueryForm));
        IPage<BankUnitInfoVo> voIPage = bankUnitInfoService.selectPage(bankInfoQueryForm);
        return Result.success(voIPage);
    }

    @ApiOperation(value = "根据单位查询银行", notes = "根据单位查询银行")
    @GetMapping(value = "findBankByUnit")
    @ResponseBody
    public Result<List<BankUnitInfoVo>> findBankByUnit(Long unitId){
        BankUnitInfoQueryForm bankInfoQueryForm=new BankUnitInfoQueryForm();
        bankInfoQueryForm.setUnitId(unitId);
        bankInfoQueryForm.setPageSize(99999);
        bankInfoQueryForm.setIsValid("Y");
        IPage<BankUnitInfoVo> voIPage = bankUnitInfoService.selectPage(bankInfoQueryForm);

        return Result.success(voIPage.getRecords());
    }



    @ApiOperation(value = "修改状态", notes = "修改状态")
    @PostMapping(value = "/modifyUnitStatus")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result modifyUnitStatus(@RequestBody @Valid ModifyUnitStatusForm form) {
        List<Long> ids = form.getIds();
        String status = form.getStatus();
        QueryWrapper<BankUnitInfo> wrapper = new QueryWrapper<>();
        wrapper.in(BankUnitInfo.ID, ids);
        List<BankUnitInfo> list = bankUnitInfoService.list(wrapper);
        for (BankUnitInfo info : list) {
            String isValid = info.getIsValid();
            String auditStatus = info.getAuditStatus();
            BaseBusinessUtil.checkAuditStatus(isValid,status);
            BaseBusinessUtil.doModifyStatus(info,status,auditStatus);
        }
        bankUnitInfoService.updateBatchById(list);
        return Result.success();
    }


    @ApiOperation(value = "通过或者拒绝", notes = "通过或者拒绝")
    @PostMapping(value = "/doRefusedOrPass")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result refusedOrPass(@RequestBody @Valid ModifyUnitStatusForm form) {
        List<Long> ids = form.getIds();
        String passOrRefused = form.getPassOrRefused();
        List<BankUnitInfo> unitInfos = (List<BankUnitInfo>) bankUnitInfoService.listByIds(ids);
        String refusedReason = form.getRefusedReason();
        List<DataAuditHis> hisList = new ArrayList<>();
        BaseBusinessUtil.refusedOrPass(passOrRefused,refusedReason,hisList,unitInfos);
        if (!CollectionUtils.isEmpty(hisList)) {
            bankUnitInfoService.updateBatchById(unitInfos);
            auditHisService.saveOrUpdateBatch(hisList);
        }
        return Result.success();
    }


    @ApiOperation(value = "查询审批历史", notes = "查询审批历史")
    @GetMapping(value ="/baseBankAuditHis")
    @ResponseBody
    public Result<List<DataAuditHisVo>> findHis(@RequestParam Long bankUnitId) {
        QueryWrapper<DataAuditHis> wrapper = new QueryWrapper<>();
        wrapper.eq(DataAuditHis.DOCUMENT_ID, bankUnitId).eq(DataAuditHis.DOCUMENT_TYPE,
                FsscEums.BASE_BANK_UNIT_TABLE.getValue());
        List<DataAuditHis> list = auditHisService.list(wrapper);
        List<DataAuditHisVo> unitAuditHisVoList=new BeanUtils<DataAuditHisVo>().copyObjs(list, DataAuditHisVo.class);
        return Result.success(unitAuditHisVoList);
    }



    /**
     * 方法名: export 方法描述: 导出对公费用报账单
     */
    @GetMapping(value =  "/exportExcel")
    @ResponseBody
    public void exportExcel( BankUnitInfoQueryForm bankUnitInfoQueryForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(bankUnitInfoQueryForm));

        Map<String,String> bankTypeMap=fsscCommonServices.getValueByCode(fsscCommonServices.findDicValueList(FsscEums.BANK_TYPE.getValue()));
        Map<String,String> isvalidMap=fsscCommonServices.getValueByCode(fsscCommonServices.findDicValueList(FsscEums.IS_VALID.getValue()));
        List<BankUnitInfoVo> records = bankUnitInfoService.selectPage(bankUnitInfoQueryForm).getRecords();
        String[] title = {"序号", "归属单位", "银行代码", "银行国际代码", "银行名称", "分行名称", "账户性质"
                , "银行账户","银行账户名称", "联行号", "地址","是否银企直联","财务会计科目","预算会计科目","创建人","状态"};
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s:title){
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String fileName = "单位银行信息";
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            BankUnitInfoVo vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getUnitName();
            content[i][2] = vo.getBankCode();
            content[i][3] = vo.getBankInternationalCode();
            content[i][4] = vo.getBankName();
            content[i][5] = vo.getBranchBankName();
            content[i][6] = bankTypeMap.get(vo.getBankType());
            content[i][7] = vo.getBankAccount();

            content[i][8] =vo.getBankAccountName();
            content[i][9] = vo.getInterBranchNumber();
            content[i][10] = vo.getAddress();
            if("Y".equals(vo.getIsBankDrectLink())){
                content[i][11] ="是";
            }else if("N".equals(vo.getIsBankDrectLink())){
                content[i][11] ="否";
            }else {
                content[i][11] ="";
            }
            content[i][12] = vo.getSubjectName();
            content[i][13] =vo.getBudgetSubjectName();
            content[i][14] = vo.getCreateUserName();
            content[i][15] = isvalidMap.get(vo.getIsValid());;
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList,FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }
}



