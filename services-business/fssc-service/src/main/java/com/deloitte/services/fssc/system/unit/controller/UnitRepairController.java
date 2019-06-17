package com.deloitte.services.fssc.system.unit.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.bank.vo.DataAuditHisVo;
import com.deloitte.platform.api.fssc.unit.param.UnitInfoQueryForm;
import com.deloitte.platform.api.fssc.unit.vo.ModifyUnitStatusForm;
import com.deloitte.platform.api.fssc.unit.vo.UnitInfoForm;
import com.deloitte.platform.api.fssc.unit.vo.UnitInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.system.bank.entity.DataAuditHis;
import com.deloitte.services.fssc.system.bank.service.IDataAuditHisService;
import com.deloitte.services.fssc.system.unit.entity.UnitInfo;
import com.deloitte.services.fssc.system.unit.service.IUnitInfoService;
import com.deloitte.services.fssc.system.util.BaseBusinessUtil;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Api(tags = "单位信息维护审核操作接口")
@Controller
@Slf4j
public class UnitRepairController {
    private static final String path="unit/unit-info";
    @Autowired
    private IUnitInfoService unitInfoService;
    @Autowired
    private IDataAuditHisService auditHisService;
    @Autowired
    private FsscCommonServices fsscCommonServices;


    @ApiOperation(value = "新增一个单位", notes = "新增一个单位")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = path + "/addOrUpdateUnit")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result addOrUpdateUnit(@Valid @ApiParam(name = "unitInfoForm", value = "新增UnitInfo的form表单", required = true)
                                        @RequestBody  UnitInfoForm unitInfoForm) {
        log.info("form:{}", JSON.toJSONString(unitInfoForm));
        String unitType = unitInfoForm.getUnitType();
        //todo 单位编码生成待完善 获取当前用户待完善

        UnitInfo unitInfo = new BeanUtils<UnitInfo>().copyObj(unitInfoForm, UnitInfo.class);
        unitInfo.setUnitCode(4223423423L);
        unitInfo.setIsValid(FsscEums.NEW.getValue());
        return Result.success(unitInfoService.saveOrUpdate(unitInfo));
    }


    @ApiOperation(value = "删除单位", notes = "删除单位")
    @DeleteMapping(value = path + "/deleteByIds")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result delete(@RequestBody List<Long> ids) {
        log.info("ids：{}", ids);
        //后台待验证是否为新建的
        //todo判断其他是否使用过这条记录 使用过的不能删除
        unitInfoService.removeByIds(ids);
        return Result.success();
    }



    @ApiOperation(value = "分页查询单位", notes = "根据条件查询单位")
    @GetMapping(value = path + "/page/conditions")
    @ResponseBody
    public Result<IPage<UnitInfoVo>> search(@Valid
                                         @ApiParam(name = "unitInfoQueryForm", value = "UnitInfo查询参数", required = true)
                                                 UnitInfoQueryForm unitInfoQueryForm) {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(unitInfoQueryForm));
        IPage<UnitInfoVo> unitInfoPage = unitInfoService.selectPage(unitInfoQueryForm);
        return Result.success(unitInfoPage);
    }

    @ApiOperation(value = "查询审批历史", notes = "查询审批历史")
    @GetMapping(value = path + "/unitAuditHisTable")
    @ResponseBody
    public Result<List<DataAuditHisVo>> findHis(@RequestParam Long unitId) {
        QueryWrapper<DataAuditHis> wrapper = new QueryWrapper<>();
        wrapper.eq(DataAuditHis.DOCUMENT_ID, unitId).eq(DataAuditHis.DOCUMENT_TYPE,FsscEums.BASE_UNIT_TABLE.getValue());
        List<DataAuditHis> list = auditHisService.list(wrapper);
        List<DataAuditHisVo> unitAuditHisVoList=new BeanUtils<DataAuditHisVo>().copyObjs(list, DataAuditHisVo.class);
        return Result.success(unitAuditHisVoList);
    }


    @ApiOperation(value = "修改状态", notes = "修改状态")
    @PostMapping(value = path + "/modifyUnitStatus")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result modifyUnitStatus(@RequestBody @Valid ModifyUnitStatusForm form) {
        List<Long> ids = form.getIds();
        String status = form.getStatus();
        QueryWrapper<UnitInfo> wrapper = new QueryWrapper<>();
        wrapper.in(UnitInfo.ID, ids);
        List<UnitInfo> list = unitInfoService.list(wrapper);
        for (UnitInfo info : list) {
            String isValid = info.getIsValid();
            String auditStatus = info.getAuditStatus();
            BaseBusinessUtil.checkAuditStatus(isValid,status);
            BaseBusinessUtil.doModifyStatus(info,status,auditStatus);
        }
        unitInfoService.updateBatchById(list);
        return Result.success();
    }

    @ApiOperation(value = "通过或者拒绝", notes = "通过或者拒绝")
    @PostMapping(value = path + "/doRefusedOrPass")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result refusedOrPass(@RequestBody @Valid ModifyUnitStatusForm form) {
        List<Long> ids = form.getIds();
        String passOrRefused = form.getPassOrRefused();
        List<UnitInfo> unitInfos = (List<UnitInfo>) unitInfoService.listByIds(ids);
        String refusedReason = form.getRefusedReason();
        List<DataAuditHis> hisList = new ArrayList<>();
        BaseBusinessUtil.refusedOrPass(passOrRefused,refusedReason,hisList,unitInfos);
        if (!CollectionUtils.isEmpty(hisList)) {
            unitInfoService.updateBatchById(unitInfos);
            auditHisService.saveOrUpdateBatch(hisList);
        }
        return Result.success();
    }


    /**
     * 方法名: export 方法描述: 导出对公费用报账单
     */
    @GetMapping(value = path + "/exportExcel")
    @ResponseBody
    public void exportExcel( UnitInfoQueryForm unitInfoQueryForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(unitInfoQueryForm));
        Map<String,String> unitTypeMap=fsscCommonServices.getValueByCode(fsscCommonServices.findDicValueList(FsscEums.BASE_UNIT_INFO_UNIT_TYPE.getValue()));
        Map<String,String> isvalidMap=fsscCommonServices.getValueByCode(fsscCommonServices.findDicValueList(FsscEums.IS_VALID.getValue()));
        List<UnitInfoVo> records = unitInfoService.selectPage(unitInfoQueryForm).getRecords();
        String[] title = {"序号", "单位编码", "单位名称", "单位类型", "所属区域", "联系人", "联系方式"
                , "地址", "纳税识别号","统一信用代码","创建人", "状态"};
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s:title){
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String fileName = "单位信息";
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            UnitInfoVo vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = StringUtil.objectToString(vo.getUnitCode());
            content[i][2] = vo.getUnitName();
            content[i][3] = unitTypeMap.get(vo.getUnitType());
            content[i][4] = StringUtil.objectToString(vo.getAreaId());
            content[i][5] = vo.getConcatUserName();
            content[i][6] = vo.getConcatWay();
            content[i][7] = StringUtil.objectToString(vo.getAddress());
            content[i][8] = vo.getTaxRegistNum();
            content[i][9]=vo.getCommonCreditCode();
            content[i][10] = StringUtil.objectToString(vo.getCreateBy());
            content[i][11] = isvalidMap.get(vo.getIsValid());
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList,FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }
}
