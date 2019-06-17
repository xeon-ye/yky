package com.deloitte.services.fssc.business.carryforward.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.carryforward.param.IncomeOfCarryForwardQueryForm;
import com.deloitte.platform.api.fssc.carryforward.vo.IncomeOfCarryForwardForm;
import com.deloitte.platform.api.fssc.carryforward.vo.IncomeOfCarryForwardVo;
import com.deloitte.platform.api.fssc.carryforward.client.IncomeOfCarryForwardClient;
import com.deloitte.platform.api.fssc.engine.manual.param.AvManualVoucherHeadQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherHead;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherLine;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deloitte.services.fssc.business.carryforward.service.IIncomeOfCarryForwardService;
import com.deloitte.services.fssc.business.carryforward.entity.IncomeOfCarryForward;


/**
 * @Author : chenx
 * @Date : Create in 2019-05-06
 * @Description :   IncomeOfCarryForward控制器实现类
 * @Modified :
 */
@Api(tags = "IncomeOfCarryForward操作接口")
@Slf4j
@RequestMapping("/fssc/incomeofcarrayforward")
@RestController
public class IncomeOfCarryForwardController  {

    @Autowired
    public IIncomeOfCarryForwardService  incomeOfCarryForwardService;

    @ApiOperation(value = "获取IncomeOfCarryForward", notes = "获取指定ID的IncomeOfCarryForward信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "IncomeOfCarryForward的ID", required = true, dataType = "long")
    public Result<IncomeOfCarryForwardVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        IncomeOfCarryForward incomeOfCarryForward=incomeOfCarryForwardService.getById(id);
        IncomeOfCarryForwardVo incomeOfCarryForwardVo=new BeanUtils<IncomeOfCarryForwardVo>().copyObj(incomeOfCarryForward,IncomeOfCarryForwardVo.class);
        return new Result<IncomeOfCarryForwardVo>().sucess(incomeOfCarryForwardVo);
    }

    @ApiOperation(value = "列表查询IncomeOfCarryForward", notes = "根据条件查询IncomeOfCarryForward列表数据")
    public Result<List<IncomeOfCarryForwardVo>> list(@Valid @RequestBody @ApiParam(name="incomeOfCarryForwardQueryForm",value="IncomeOfCarryForward查询参数",required=true) IncomeOfCarryForwardQueryForm incomeOfCarryForwardQueryForm) {
        log.info("search with incomeOfCarryForwardQueryForm:", incomeOfCarryForwardQueryForm.toString());
        List<IncomeOfCarryForward> incomeOfCarryForwardList=incomeOfCarryForwardService.selectList(incomeOfCarryForwardQueryForm);
        List<IncomeOfCarryForwardVo> incomeOfCarryForwardVoList=new BeanUtils<IncomeOfCarryForwardVo>().copyObjs(incomeOfCarryForwardList,IncomeOfCarryForwardVo.class);
        return new Result<List<IncomeOfCarryForwardVo>>().sucess(incomeOfCarryForwardVoList);
    }


    @ApiOperation(value = "分页查询IncomeOfCarryForward", notes = "根据条件查询IncomeOfCarryForward分页数据")
    @GetMapping(value = "/page")
    public Result<IPage<IncomeOfCarryForwardVo>> search(IncomeOfCarryForwardQueryForm incomeOfCarryForwardQueryForm) {
        log.info("search with incomeOfCarryForwardQueryForm:", incomeOfCarryForwardQueryForm.toString());
        IPage<IncomeOfCarryForward> incomeOfCarryForwardPage=incomeOfCarryForwardService.selectPage(incomeOfCarryForwardQueryForm);
        IPage<IncomeOfCarryForwardVo> incomeOfCarryForwardVoPage=new BeanUtils<IncomeOfCarryForwardVo>().copyPageObjs(incomeOfCarryForwardPage,IncomeOfCarryForwardVo.class);
        return new Result<IPage<IncomeOfCarryForwardVo>>().sucess(incomeOfCarryForwardVoPage);
    }


    @ApiOperation(value = "导出", httpMethod = "get" , notes = "导出")
    @ApiParam(name = "queryForm", value = "导出账薄信息", required = true)
    @GetMapping(value = "/export")
    public void exportExcel(HttpServletResponse response,  IncomeOfCarryForwardQueryForm queryForm){
        log.info("exportExcel with AvAccountElementQueryForm:{}", JSON.toJSONString(queryForm));
        List<IncomeOfCarryForward> records = incomeOfCarryForwardService.selectList(queryForm);
        List<ExcelHeader> headerList = new ArrayList<>();
        headerList.add(new ExcelHeader("序号").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("支出大类").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("单据编号").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("单据类型").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("项目").setOneCellWidth(8000));
        headerList.add(new ExcelHeader("归属单位").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("归属部门").setOneCellWidth(5000));
        headerList.add(new ExcelHeader("入账日期").setOneCellWidth(5000));
        headerList.add(new ExcelHeader("金额").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("结转状态").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("备注").setOneCellWidth(3000));
        String fileName = "收入结转";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Object[][] content = new Object[records.size()][];
        Map<String,String> map = new HashMap<>();
        map.put(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue(),FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getName());
        map.put(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue(),FsscTableNameEums.GE_GENERAL_EXPENSE.getName());
        map.put(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue(),FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getName());
        map.put(FsscTableNameEums.LC_LABOR_COST.getValue(),FsscTableNameEums.LC_LABOR_COST.getName());
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[headerList.size()];
            IncomeOfCarryForward vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getMainTypeName();
            content[i][2] = vo.getDocumentNum();
            content[i][3] = map.get(vo.getDocumentType());
            content[i][4] = vo.getProjectName();
            content[i][5] = vo.getUnitName();
            content[i][6] = vo.getDeptName();
            content[i][7] = dateFormat.format(vo.getEnterDate());
            content[i][8] = vo.getMoney()+"";
            content[i][9] = vo.getStatus()==null?"未结转": vo.getStatus()=="N"?"未结转":"结转";
            content[i][10] = vo.getRemark();
        }
        MultipleExcelExportUtil exportUtil = MultipleExcelExportUtil.getSimpleInstance2(headerList,response);
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    @ApiOperation(value = "查看收入结转是否被手工录入", notes = "查看收入结转是否被手工录入")
    @GetMapping (value = "/hasCarry")
    @Transactional
    public Result hasCarry(@RequestParam("id") Long id) {
        String  info ="";
        try {
           Integer count = incomeOfCarryForwardService.hasCarryId(id);
           if(count>0){
               return Result.fail("已经被手工结转！");
           }
        }catch (Exception e){
            log.error("查询结转失败"+e);
            info ="删除失败";
        }
        return Result.success();
    }


}



