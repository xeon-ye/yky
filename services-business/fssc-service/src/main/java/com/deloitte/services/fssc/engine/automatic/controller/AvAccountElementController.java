package com.deloitte.services.fssc.engine.automatic.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.vo.ModifyMainTypeStatusForm;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvAccountElementQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvAccountElementQueryParam;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvChartOfAccountQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvModifyMainTypeFrom;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvAccountElementForm;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvAccountElementVo;
import com.deloitte.platform.api.fssc.engine.automatic.AvAccountElementClient;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvBaseElementVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.engine.automatic.entity.*;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountElementReleService;
import com.deloitte.services.fssc.engine.automatic.service.IAvBaseElementService;
import com.deloitte.services.fssc.engine.automatic.service.IAvLedgerUnitRelationService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.beans.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deloitte.services.fssc.engine.automatic.service.IAvAccountElementService;


/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description :   AvAccountElement控制器实现类
 * @Modified :
 */
@Api(tags = "会计引擎-核算要素值-操作接口")
@Slf4j
@RestController
@RequestMapping("/fssc/automatic/avAccountE")
public class AvAccountElementController  {

    @Autowired
    public IAvAccountElementService  avAccountElementService;
    @Autowired
    public IAvAccountElementReleService avAccountElementReleService;
    @Autowired
    public IAvBaseElementService  avBaseElementService;
    @Autowired
    private FsscCommonServices commonServices;
    @Autowired
    private IAvLedgerUnitRelationService  avLedgerUnitRelationService;



    @ApiOperation(value = "新增和编辑AvAccountElement", notes = "新增和编辑一个AvAccountElement")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/addAndUpdate")
    public Result add(@Valid @RequestBody @ApiParam(name="avAccountElementForm",value="新增AvAccountElement的form表单",required=true)  AvAccountElementForm avAccountElementForm) {
        log.info("form:",  avAccountElementForm.toString());
        AvAccountElement avAccountElement =new BeanUtils<AvAccountElement>().copyObj(avAccountElementForm,AvAccountElement.class);
        DeptVo deptVo = commonServices.getCurrentDept();
        UserVo userVo = commonServices.getCurrentUser();
        try {
            if(avAccountElementForm.getSegmentDesc()!=null&&avAccountElementForm.getSegmentCode()!=null){
                QueryWrapper<AvAccountElement> queryWrapper = new QueryWrapper <AvAccountElement>();
                queryWrapper.eq(AvAccountElement.SEGMENT_CODE,avAccountElementForm.getSegmentCode());
                List<AvAccountElement>  oldList=  avAccountElementService.list(queryWrapper);
                Boolean status = true;//主要来控制是否编码判断重复
                if(oldList.size()>0){
                   if(avAccountElementForm.getId()!=null){
                       if(!avAccountElementForm.getId().equals(oldList.get(0).getId())){
                           status =false;
                       }
                   }else{
                       status =false;
                   }
                }
                if(status){
                    if(avAccountElementForm.getId()!=null){
                        avAccountElement.setUpdateBy(Long.parseLong(userVo.getId()));
                        avAccountElement.setUpdateDate(LocalDateTime.now());
                        avAccountElementService.saveOrUpdate(avAccountElement);
                    }else {
                        avAccountElement.setCreateBy(Long.parseLong(userVo.getId()));
                        avAccountElement.setCreateDate(LocalDateTime.now());
                        avAccountElementService.save(avAccountElement);

                    }
                }else {
                    return Result.fail("核算要素编码不能重复");
                }
                AvAccountElementRele releEntity = new AvAccountElementRele();
                releEntity.setElementId(avAccountElement.getId());
                releEntity.setLedgerId(getLedgerId());
                avAccountElementReleService.saveOrUpdate(releEntity);


            }else{
                return  Result.fail("请输入核算要素名和编码");
            }

        }catch (Exception e){
            log.info("保存失败！"+e);
            return Result.fail();
        }


        return Result.success();
    }


    @ApiOperation(value = "删除AvAccountElement", notes = "根据url的id来指定删除对象")
    @DeleteMapping (value = "/delete")
    public Result delete(@RequestBody @Valid Long id) {
        try{
            List<AvAccountElement> list = avAccountElementService.whetherEnabledById(id);
            if(list.size()<=0){
                avAccountElementService.removeById(id);
            }else{
                return Result.fail("不能删除核算要素，该要素被已经被会计凭证中使用");
            }
        }catch (Exception e){
            log.info("删除失败"+e);
            throw new ServiceException(FsscErrorType.SAVE_FAIL, e.getMessage());
        }
       // avAccountElementService.removeById(id);
        return Result.success();
    }


    @ApiOperation(value = "修改AvAccountElement", notes = "修改指定AvAccountElement信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "AvAccountElement的ID", required = true, dataType = "long")
    @PostMapping(value = "/update")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="avAccountElementForm",value="修改AvAccountElement的form表单",required=true)  AvAccountElementForm avAccountElementForm) {
        AvAccountElement avAccountElement =new BeanUtils<AvAccountElement>().copyObj(avAccountElementForm,AvAccountElement.class);
        avAccountElement.setId(id);
        avAccountElementService.saveOrUpdate(avAccountElement);
        return Result.success();
    }



    @ApiOperation(value = "获取AvAccountElement", notes = "获取指定ID的AvAccountElement信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AvAccountElement的ID", required = true, dataType = "long")
    @GetMapping(value = "/getInfo")
    public Result<AvAccountElementVo> get(@RequestParam long id) {
        log.info("get with id:{}", id);
        AvAccountElement avAccountElement=avAccountElementService.getById(id);
        AvAccountElementVo avAccountElementVo=new BeanUtils<AvAccountElementVo>().copyObj(avAccountElement,AvAccountElementVo.class);
        return new Result<AvAccountElementVo>().sucess(avAccountElementVo);
    }


    @ApiOperation(value = "列表查询AvAccountElement", notes = "根据条件查询AvAccountElement列表数据")
    @GetMapping(value = "/queryList")
    public Result<List<AvAccountElementVo>> list(@Valid @RequestBody @ApiParam(name="avAccountElementQueryForm",value="AvAccountElement查询参数",required=true) AvAccountElementQueryForm avAccountElementQueryForm) {
        log.info("search with avAccountElementQueryForm:", avAccountElementQueryForm.toString());
        List<AvAccountElement> avAccountElementList=avAccountElementService.selectList(avAccountElementQueryForm);
        List<AvAccountElementVo> avAccountElementVoList=new BeanUtils<AvAccountElementVo>().copyObjs(avAccountElementList,AvAccountElementVo.class);
        return new Result<List<AvAccountElementVo>>().sucess(avAccountElementVoList);
    }

    private Long getLedgerId(){
        DeptVo deptVo = commonServices.getCurrentDept();
        UserVo userVo = commonServices.getCurrentUser();
        QueryWrapper<AvLedgerUnitRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AvLedgerUnitRelation.BALANCE_CODE,deptVo.getDeptCode());
        AvLedgerUnitRelation entity = avLedgerUnitRelationService.getOne(queryWrapper);

        return entity.getLedgerId();
    }

    @ApiOperation(value = "分页查询AvAccountElement", notes = "根据条件查询AvAccountElement分页数据")
    @GetMapping(value = "/queryPageList")
    public Result<IPage<AvAccountElementVo>> search( AvAccountElementQueryForm avAccountElementQueryForm) {
        log.info("search with avAccountElementQueryForm:", avAccountElementQueryForm.toString());
        avAccountElementQueryForm.setLedgerId(getLedgerId());
        List<AvAccountElement> avUtilList=avAccountElementService.queryPageListByParam(avAccountElementQueryForm);
        List<AvAccountElementVo> avVoList=new BeanUtils<AvAccountElementVo>().copyObjs(avUtilList,AvAccountElementVo.class);
        Integer count = avAccountElementService.queryPageListByParamCount(avAccountElementQueryForm);
        GdcPage<AvAccountElementVo> vo = new GdcPage<AvAccountElementVo>();
        vo.setContent(avVoList);
        vo.setPageNo(avAccountElementQueryForm.getCurrentPage());
        vo.setPageSize(avAccountElementQueryForm.getPageSize());
        vo.setTotal(count);
        return new Result<IPage<AvAccountElementVo>>().sucess(vo);
    }

    @ApiOperation(value = "分页查询核算要素值", notes = "根据条件查询核算要素值分页数据")
    @GetMapping(value = "/queryElementBasePageList")
    public Result<IPage<AvBaseElementVo>> searchBaseElement( AvAccountElementQueryForm queryFrom) {
        log.info("search with avBaseElement:", queryFrom.toString());
        List<AvBaseElement> avBaseElementPage=avAccountElementService.baseValueListByElementId(queryFrom);
        Integer count = avAccountElementService.baseValueCountByElementId(queryFrom);
        GdcPage<AvBaseElement> vo = new GdcPage<AvBaseElement>();
        vo.setContent(avBaseElementPage);
        vo.setPageSize(queryFrom.getPageSize());
        vo.setPageNo(queryFrom.getCurrentPage());
        vo.setTotal(count);
        IPage<AvBaseElementVo> avBaseElementVoPage=new BeanUtils<AvBaseElementVo>().copyPageObjs(vo,AvBaseElementVo.class);
        return new Result<IPage<AvBaseElementVo>>().sucess(avBaseElementVoPage);
    }

    @ApiOperation(value = "查询核算要素分配到具体的账薄信息", notes = "查询核算要素分配到具体的账薄信息")
    @GetMapping(value = "/queryLedgersByElementList")
    public Result<List<AvChartOfAccount>> queryLedgersByElementList(AvAccountElementQueryForm queryForm) {
        List<AvChartOfAccount> List = avAccountElementService.elementReleationLedgerList(queryForm);
        Integer count = avAccountElementService.elementReleationLedgerListCount(queryForm);
        GdcPage<AvChartOfAccount> vo = new GdcPage<AvChartOfAccount>();
        vo.setContent(List);
        vo.setPageSize(queryForm.getPageSize());
        vo.setPageNo(queryForm.getCurrentPage());
        vo.setTotal(count);
        return new Result<List<AvChartOfAccount>>().sucess(vo);
    }


    @ApiOperation(value = "批量失效和批量启用",notes="根据类型区分验证要素是否能失效和启用")
    @PostMapping(value = "/batchVaild")
    public Result batchVaild(HttpServletRequest request, HttpServletResponse response,@RequestBody @Valid ModifyMainTypeStatusForm form){
        StringBuffer info =new StringBuffer();
        List<Long> ids = form.getIds();
        String status = form.getStatus();
        UserVo userVo = commonServices.getCurrentUser();
        try {
            //启用

           if("Y".equals(status)){
               for (int i=0;i<ids.size();i++){
                   AvAccountElement avAccountElement = new AvAccountElement();
                   avAccountElement.setId(ids.get(i));
                   avAccountElement.setUpdateDate(LocalDateTime.now());
                   avAccountElement.setUpdateBy(Long.parseLong(userVo.getId()));
                   avAccountElement.setStatus("Y");
                   avAccountElementService.saveOrUpdate(avAccountElement);
               }
               info.append("修改成功");
           }else{
               //失效
               for (int i=0;i<ids.size();i++){
                Long element_id = ids.get(i);
                List<AvAccountElement> list = avAccountElementService.whetherEnabledById(element_id);
                if(list.size()>0){
                    info.append("核算要素编码："+list.get(0).getSegmentCode()+"已经被会计凭证使用，不能被失效").append("/n");
                }else{
                    AvAccountElement avAccountElement = new AvAccountElement();
                    avAccountElement.setId(element_id);
                    avAccountElement.setUpdateDate(LocalDateTime.now());
                    avAccountElement.setUpdateBy(Long.parseLong(userVo.getId()));
                    avAccountElement.setStatus("N");
                    avAccountElementService.saveOrUpdate(avAccountElement);
                }
                   info.append("修改成功");
               }
           }
        }catch (Exception e){
            log.info("启动和失效核算要素失败:"+e);
        }
        return Result.success(info.toString());
    }
    @ApiOperation(value="保存核算要素的具体关联的账薄信息",notes="保存核算要素的具体关联的账薄信息")
    @PostMapping(value="/saveRelationToLedger")
    public Result saveRelationToLedger(HttpServletRequest request, HttpServletResponse response,@Valid @RequestBody  AvAccountElementForm param){
        AvAccountElementRele entity = new AvAccountElementRele();
        UserVo userVo = commonServices.getCurrentUser();
        if(param.getId()!=null||param.getLedgerId()!=null){
            QueryWrapper<AvAccountElementRele> queryWrapper = new QueryWrapper <AvAccountElementRele>();
            queryWrapper.eq(AvAccountElementRele.ELEMENT_ID,param.getId());
            queryWrapper.eq(AvAccountElementRele.LEDGER_ID,param.getLedgerId());
            List<AvAccountElementRele> list = avAccountElementReleService.list(queryWrapper);
            if(list.size()<=0){
                entity.setElementId(param.getId());
                entity.setLedgerId(param.getLedgerId());
                entity.setCreateBy(Long.parseLong(userVo.getId()));
                entity.setCreateDate(LocalDateTime.now());
                avAccountElementReleService.save(entity);
            }

        }

        return Result.success();
    }

    @ApiOperation(value="删除核算要素的具体关联的账薄信息",notes="删除核算要素的具体关联的账薄信息")
    @DeleteMapping (value="/deleteRelationToLedger")
    public Result deleteRelationToLedger(HttpServletRequest request, HttpServletResponse response,@Valid @RequestBody @ApiParam(name="AvModifyMainTypeFrom",value="AvModifyMainTypeFrom查询参数",required=true) AvModifyMainTypeFrom from){
         try{
             List<Long> ledgerIds = from.getLedgerIds();
             Long  elementId = from.getElementId();
             for(int i=0;i<ledgerIds.size();i++){
                 QueryWrapper<AvAccountElementRele> queryWrapper = new QueryWrapper <AvAccountElementRele>();
                 queryWrapper.eq(AvAccountElementRele.ELEMENT_ID,elementId);
                 queryWrapper.eq(AvAccountElementRele.LEDGER_ID,ledgerIds.get(i));
                 avAccountElementReleService.remove(queryWrapper);
             }
         }catch (Exception e){
             log.error("删除出错"+e);
             return Result.fail("删除失败");
         }
         return Result.success("删除成功");

    }


    @ApiOperation(value = "保存数据来源", notes = "保存数据来源信息到核算要素列表数据")
    @Transactional
    @PostMapping(value = "/saveSourceFrom")
    public Result saveSourceFrom(@Valid @RequestBody @ApiParam(name="AvAccountElement",value="AvAccountElement查询参数",required=true) AvAccountElement entity) {
         if(entity.getDataFrom()!=null){
             UserVo userVo = commonServices.getCurrentUser();
             try{
                 entity.setUpdateBy(Long.parseLong(userVo.getId()));
                 entity.setHasValue("Y");
                 entity.setUpdateDate(LocalDateTime.now());
                 avAccountElementService.saveSourceFromObtain(entity);
                 avAccountElementService.saveOrUpdate(entity);
             }catch (Exception e){
               log.error("保存数据源出错"+e);
             }

         }
         return  Result.success();
       // return new Result<List<AvAccountElementVo>>().sucess(avAccountElementVoList);
    }

    @ApiOperation(value="得到币种信息账薄信息",notes="得到币种信息账薄信息列表")
    @GetMapping (value="/currencyFromEbs")
    public Result<List<AvBaseElementVo>> currencyFromEbs(HttpServletRequest request, HttpServletResponse response){
        List<AvBaseElementVo> voList = new ArrayList<AvBaseElementVo>();
        try{
            QueryWrapper<AvBaseElement> queryWrapper = new QueryWrapper <AvBaseElement>();
            queryWrapper.eq(AvBaseElement.DATA_TYPE,"CURRENCY");
            queryWrapper.eq(AvBaseElement.DATA_STATUS,"Y");
            List<AvBaseElement> list = avBaseElementService.list(queryWrapper);
            voList = new BeanUtils<AvBaseElementVo>().copyObjs(list,AvBaseElementVo.class);
            for(AvBaseElementVo p:voList){
                p.setDataStatus(null);
            }
        }catch (Exception e){
            log.error("删除出错"+e);
            return Result.fail("删除失败");
        }
        return new  Result<List<AvBaseElementVo>>().sucess(voList);
    }



    @ApiOperation(value = "导出", httpMethod = "get" , notes = "导出")
    @ApiParam(name = "queryForm", value = "导出账薄信息", required = true)
    @GetMapping(value = "/export")
    public void exportExcel(HttpServletResponse response, AvAccountElementQueryForm queryForm){
        log.info("exportExcel with AvAccountElementQueryForm:{}", JSON.toJSONString(queryForm));
        List<AvAccountElement> records = avAccountElementService.selectList(queryForm);
        List<ExcelHeader> headerList = new ArrayList<>();
        headerList.add(new ExcelHeader("序号").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("核算要素编码").setOneCellWidth(4000));
        headerList.add(new ExcelHeader("核算要素名称").setOneCellWidth(6000));
        headerList.add(new ExcelHeader("核算要素值").setOneCellWidth(3000));
        headerList.add(new ExcelHeader("是否有效").setOneCellWidth(2000));
        headerList.add(new ExcelHeader("要素类型").setOneCellWidth(5000));
        String fileName = "核算要素信息";
        Object[][] content = new Object[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[headerList.size()];
            AvAccountElement vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getSegmentCode();
            content[i][2] = vo.getSegmentDesc();
            content[i][3] = vo.getHasValue();
            content[i][4] = FsscEums.VALID.getValue().equals(vo.getStatus()) ? "是" : "否";
            content[i][5] = FsscEums.AV_ACCOUNT_ELEMENT_TYPE_COA.getValue().equals(vo.getType())?"COA":(FsscEums.AV_ACCOUNT_ELEMENT_TYPE_HEAD.getValue().equals((vo.getType()))?"凭证头":"凭证行");
        }
        MultipleExcelExportUtil exportUtil = MultipleExcelExportUtil.getSimpleInstance2(headerList,response);
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

}



