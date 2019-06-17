package com.deloitte.services.fssc.engine.automatic.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.base.param.BaseIncomeMainTypeQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvAccountElementQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvChartOfAccountQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvUtilQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvAccountElementVo;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvBaseElementVo;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvChartOfAccountForm;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvChartOfAccountVo;
import com.deloitte.platform.api.fssc.engine.automatic.AvChartOfAccountClient;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvLedgerUnitRelation;
import com.deloitte.services.fssc.engine.automatic.service.IAvLedgerUnitRelationService;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import com.deloitte.services.fssc.engine.automatic.service.IAvChartOfAccountService;
import com.deloitte.services.fssc.engine.automatic.entity.AvChartOfAccount;


/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description :   AvChartOfAccount控制器实现类
 * @Modified :
 */
@Api(tags = "会计引擎-账薄设置-操作接口")
@Slf4j
@RestController
@RequestMapping("/fssc/automatic/avChart")
public class AvChartOfAccountController {

    @Autowired
    public IAvChartOfAccountService  avChartOfAccountService;
    @Autowired
    private FsscCommonServices commonServices;
    @Autowired
    private IAvLedgerUnitRelationService  avLedgerUnitRelationService;

    @ApiOperation(value = "新增AvChartOfAccount", notes = "新增一个AvChartOfAccount")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/add")
    public Result add(@Valid @RequestBody @ApiParam(name="avChartOfAccountForm",value="新增AvChartOfAccount的form表单",required=true)  AvChartOfAccountForm avChartOfAccountForm) {
        log.info("form:",  avChartOfAccountForm.toString());
        AvChartOfAccount avChartOfAccount =new BeanUtils<AvChartOfAccount>().copyObj(avChartOfAccountForm,AvChartOfAccount.class);
        return Result.success( avChartOfAccountService.save(avChartOfAccount));
    }


    @ApiOperation(value = "删除AvChartOfAccount", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AvChartOfAccountID", required = true, dataType = "long")
    @DeleteMapping (value = "/delete")
    public Result delete(@PathVariable long id) {
        avChartOfAccountService.removeById(id);
        return Result.success();
    }


    @ApiOperation(value = "修改AvChartOfAccount", notes = "修改指定AvChartOfAccount信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "AvChartOfAccount的ID", required = true, dataType = "long")
    @PostMapping(value = "/update")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="avChartOfAccountForm",value="修改AvChartOfAccount的form表单",required=true)  AvChartOfAccountForm avChartOfAccountForm) {
        AvChartOfAccount avChartOfAccount =new BeanUtils<AvChartOfAccount>().copyObj(avChartOfAccountForm,AvChartOfAccount.class);
        avChartOfAccount.setId(id);
        avChartOfAccountService.saveOrUpdate(avChartOfAccount);
        return Result.success();
    }


    @ApiOperation(value = "获取AvChartOfAccount", notes = "获取指定ID的AvChartOfAccount信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AvChartOfAccount的ID", required = true, dataType = "long")
    @GetMapping(value = "/getInfoById")
    public Result<AvChartOfAccountVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        AvChartOfAccount avChartOfAccount=avChartOfAccountService.getById(id);
        AvChartOfAccountVo avChartOfAccountVo=new BeanUtils<AvChartOfAccountVo>().copyObj(avChartOfAccount,AvChartOfAccountVo.class);
        return new Result<AvChartOfAccountVo>().sucess(avChartOfAccountVo);
    }

    @ApiOperation(value = "列表查询AvChartOfAccount", notes = "根据条件查询AvChartOfAccount列表数据")
    @GetMapping(value = "/getListByFrom")
    public Result<List<AvChartOfAccountVo>> list(AvChartOfAccountQueryForm avChartOfAccountQueryForm) {
        log.info("search with avChartOfAccountQueryForm:", avChartOfAccountQueryForm.toString());
        DeptVo deptVo = commonServices.getCurrentDept();
        avChartOfAccountQueryForm.setLedgerId(avLedgerUnitRelationService.getLedgerId(deptVo.getDeptCode()));
        List<AvChartOfAccount> avChartOfAccountList=avChartOfAccountService.selectList(avChartOfAccountQueryForm);
        List<AvChartOfAccountVo> avChartOfAccountVoList=new BeanUtils<AvChartOfAccountVo>().copyObjs(avChartOfAccountList,AvChartOfAccountVo.class);
        return new Result<List<AvChartOfAccountVo>>().sucess(avChartOfAccountVoList);
    }


    @ApiOperation(value = "分页查询AvChartOfAccount", notes = "根据条件查询AvChartOfAccount分页数据")
    @GetMapping(value = "/getListPageByFrom")
    @ResponseBody
    public Result<IPage<AvChartOfAccountVo>> search(AvChartOfAccountQueryForm avChartOfAccountQueryForm) {
        log.info("search with avChartOfAccountQueryForm:", avChartOfAccountQueryForm.toString());
        DeptVo deptVo = commonServices.getCurrentDept();
        avChartOfAccountQueryForm.setLedgerId(avLedgerUnitRelationService.getLedgerId(deptVo.getDeptCode()));
        IPage<AvChartOfAccount> avChartOfAccountPage=avChartOfAccountService.selectPage(avChartOfAccountQueryForm);
        IPage<AvChartOfAccountVo> avChartOfAccountVoPage=new BeanUtils<AvChartOfAccountVo>().copyPageObjs(avChartOfAccountPage,AvChartOfAccountVo.class);
        return new Result<IPage<AvChartOfAccountVo>>().sucess(avChartOfAccountVoPage);
    }

    @ApiOperation(value = "列表查询AvUtil", notes = "根据条件查询账薄适用于哪些公司列表数据")
    @GetMapping(value = "/getUtilListByFrom")
    public Result<IPage<AvBaseElementVo>> utilList( AvUtilQueryForm avUtilQueryForm) {
        log.info("search with avUtilQueryForm:", avUtilQueryForm.toString());
        List<AvBaseElement> avUtilList=avChartOfAccountService.selectUnitList(avUtilQueryForm);
        List<AvBaseElementVo> avChartOfAccountVoList=new BeanUtils<AvBaseElementVo>().copyObjs(avUtilList,AvBaseElementVo.class);
        Integer count = avChartOfAccountService.selectUnitListCount(avUtilQueryForm);
        GdcPage<AvBaseElementVo> vo = new GdcPage<AvBaseElementVo>();
        vo.setContent(avChartOfAccountVoList);
        vo.setPageNo(avUtilQueryForm.getCurrentPage());
        vo.setPageSize(avUtilQueryForm.getPageSize());
        vo.setTotal(count);
        return new Result<IPage<AvBaseElementVo>>().sucess(vo);
    }


    @ApiOperation(value = "根据账薄ID获取到COA结构", notes = "根据条件查询AvChartOfAccount列表数据")
    @GetMapping(value = "/getAccountFrameListByFrom")
    public Result<List<AvAccountElementVo>> accountFramelist(@RequestParam("ledgerId") Long  ledgerId) {
        List<AvAccountElement> avAccountElementsForStruList=avChartOfAccountService.selectAccountFrameList(ledgerId);
        List<AvAccountElementVo>  aList=new BeanUtils<AvAccountElementVo>().copyObjs(avAccountElementsForStruList,AvAccountElementVo.class);
        return new Result<List<AvAccountElementVo>>().sucess(aList);
    }


    @ApiOperation(value = "获取会计科目COA结构值", notes = "根据登录人员附带的单位信息回去当前单位应该分配的会计科目值")
    @GetMapping(value = "/getAccountForC")
    public Result<AvBaseElementVo> getAccountForC(@RequestParam("type")String type, HttpServletResponse response, HttpServletRequest request) {
        DeptVo deptVo = commonServices.getCurrentDept();
        String code = deptVo.getDeptCode();//公司编码
        log.info("get with id:{}", code);
        List<AvBaseElement> list = new ArrayList<AvBaseElement>();
        list = avChartOfAccountService.selectBaseElementForAccount(code,type);
        return new Result<AvBaseElementVo>().sucess(list);
    }

    private Long getLedgerId(){
        DeptVo deptVo = commonServices.getCurrentDept();
        UserVo userVo = commonServices.getCurrentUser();
        QueryWrapper<AvLedgerUnitRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AvLedgerUnitRelation.BALANCE_CODE,deptVo.getDeptCode());
        AvLedgerUnitRelation entity = avLedgerUnitRelationService.getOne(queryWrapper);

        return entity.getLedgerId();
    }

    @ApiOperation(value = "导出", httpMethod = "get" , notes = "导出")
    @ApiParam(name = "queryForm", value = "导出账薄信息", required = true)
    @GetMapping(value = "/export")
    public void exportExcel(HttpServletResponse response, AvChartOfAccountQueryForm queryForm){
        log.info("exportExcel with AvChartOfAccountQueryForm:{}", JSON.toJSONString(queryForm));
        queryForm.setLedgerId(getLedgerId());
        List<AvChartOfAccount> records = avChartOfAccountService.selectList(queryForm);
        List<ExcelHeader> headerList = new ArrayList<>();
        headerList.add(new ExcelHeader("序号").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("账薄编码").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("账薄名称").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("本位币").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("是否有效").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("说明").setOneCellWidth(5000));
        String fileName = "账薄信息";
        Object[][] content = new Object[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[headerList.size()];
            AvChartOfAccount vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getName();
            content[i][2] = vo.getShortName();
            content[i][3] = vo.getCurrencyCode();
            content[i][4] =FsscEums.VALID.getValue().equals(vo.getStatus()) ? "是" : "否";
            content[i][5] = vo.getDescription();
        }
        MultipleExcelExportUtil exportUtil = MultipleExcelExportUtil.getSimpleInstance2(headerList,response);
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }


}



