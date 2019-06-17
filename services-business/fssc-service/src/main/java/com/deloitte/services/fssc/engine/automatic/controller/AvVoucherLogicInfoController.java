package com.deloitte.services.fssc.engine.automatic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypeVo;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvChartOfAccountQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvLogicCopyDocumentForm;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvVoucherLogicInfoQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.vo.*;
import com.deloitte.platform.api.fssc.engine.automatic.AvVoucherLogicInfoClient;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.fssc.base.entity.BaseContentCommitmentUnit;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.engine.automatic.entity.*;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountLogicHeadService;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountLogicLineService;
import com.deloitte.services.fssc.engine.automatic.service.IAvLogicHeadLineDicService;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.StringUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.deloitte.services.fssc.engine.automatic.service.IAvVoucherLogicInfoService;


/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description :   AvVoucherLogicInfo控制器实现类
 * @Modified :
 */
@Api(tags = "会计引擎-凭证逻辑-操作接口")
@Slf4j
@RestController
@RequestMapping("/fssc/av-voucher-logic-info")
public class AvVoucherLogicInfoController  {

    @Autowired
    public IAvVoucherLogicInfoService  avVoucherLogicInfoService;

    @Autowired
    private FsscCommonServices fsscCommonServices;
    @Autowired
    private IAvAccountLogicHeadService  avAccountLogicHeadService;
    @Autowired
    private IAvAccountLogicLineService avAccountLogicLineService;
    @Autowired
    private FsscCommonServices commonServices;
    @Autowired
    private IAvLogicHeadLineDicService avLogicHeadLineDicService;

    @ApiOperation(value = "新增AvVoucherLogicInfo", notes = "新增一个AvVoucherLogicInfo")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @Transactional
    @PostMapping(value = "/saveOrUpdate")
    public Result add(@Valid @RequestBody @ApiParam(name="avVoucherLogicInfoForm",value="新增AvVoucherLogicInfo的form表单",required=true)  AvVoucherLogicInfoForm avVoucherLogicInfoForm) {
        log.info("form:",  avVoucherLogicInfoForm.toString());
        AvVoucherLogicInfo avVoucherLogicInfo =new BeanUtils<AvVoucherLogicInfo>().copyObj(avVoucherLogicInfoForm,AvVoucherLogicInfo.class);
        List<AvAccountLogicHead> headList = new BeanUtils<AvAccountLogicHead>().copyObjs(avVoucherLogicInfoForm.getHeadList(),AvAccountLogicHead.class);
        List<AvAccountLogicLine> lineList = new BeanUtils<AvAccountLogicLine>().copyObjs(avVoucherLogicInfoForm.getLineList(),AvAccountLogicLine.class);
        UserVo userVo = commonServices.getCurrentUser();
        if(avVoucherLogicInfo.getLedgerId()!=null&&avVoucherLogicInfo.getType()!=null){
            if(headList.size()>0&&lineList.size()>1){
                //保存凭证逻辑信息
                if(avVoucherLogicInfo.getId()!=null){
                    avVoucherLogicInfo.setUpdateBy(Long.parseLong(userVo.getId()));
                    avVoucherLogicInfo.setUpdateDate(LocalDateTime.now());
                    avVoucherLogicInfoService.updateById(avVoucherLogicInfo);
                }else{
                    avVoucherLogicInfo.setCreateBy(Long.parseLong(userVo.getId()));
                    avVoucherLogicInfo.setCreateDate(LocalDateTime.now());
                    avVoucherLogicInfoService.save(avVoucherLogicInfo);
                }
                List<AvAccountLogicHead> oldHeadList =avVoucherLogicInfoService.getLogicHeadListByLogicId(avVoucherLogicInfo.getId()) ;//获取数据库之前凭证头数据
                List<AvAccountLogicLine> OldLineList = avVoucherLogicInfoService.getLogicLineListByLogicId(avVoucherLogicInfo.getId());//获取数据库之前凭证行数据
                List<Long> headIds = oldHeadList.stream().map(AvAccountLogicHead:: getId).collect(
                        Collectors.toList());
                List<Long> lineIds = OldLineList.stream().map(AvAccountLogicLine:: getId).collect(
                        Collectors.toList());
                //新增，编辑，删除凭证头信息
                List<Long> newHeadIds = new ArrayList<Long>();
                for (AvAccountLogicHead p:headList){
                    p.setLogicId(avVoucherLogicInfo.getId());
                    if(!headIds.contains(p.getId())){
                        p.setLogicId(avVoucherLogicInfo.getId());
                        p.setCreateDate(LocalDateTime.now());
                        p.setCrreteBy(Long.parseLong(userVo.getId()));
                        avAccountLogicHeadService.save(p);
                    }else{
                        p.setLogicId(avVoucherLogicInfo.getId());
                        p.setUpdateBy(Long.parseLong(userVo.getId()));
                        p.setUpdateDate(LocalDateTime.now());
                        avAccountLogicHeadService.updateById(p);
                    }
                    newHeadIds.add(p.getId());
                }
                headIds.removeAll(newHeadIds);//获取被删除的
                for(int i=0;i<headIds.size();i++){
                    avAccountLogicHeadService.removeById(headIds.get(i));
                }
                //新增，编辑，删除凭证行信息
                List<Long> newLineIds = new ArrayList<Long>();
                for (AvAccountLogicLine p:lineList){
                    p.setLogicId(avVoucherLogicInfo.getId());
                    if(!lineIds.contains(p.getId())){
                        //新增凭证行
                        p.setLogicId(avVoucherLogicInfo.getId());
                        p.setCreateDate(LocalDateTime.now());
                        p.setCreateBy(Long.parseLong(userVo.getId()));
                        avAccountLogicLineService.save(p);
                    }else{
                        //编辑凭证行
                        p.setLogicId(avVoucherLogicInfo.getId());
                        p.setUpdateBy(Long.parseLong(userVo.getId()));
                        p.setUpdateDate(LocalDateTime.now());
                        avAccountLogicLineService.updateById(p);
                    }
                    newLineIds.add(p.getId());
                }
                //移除删除的凭证行信息
                lineIds.removeAll(newLineIds);//获取被删除的
                for(int i=0;i<lineIds.size();i++){
                    avAccountLogicLineService.removeById(lineIds.get(i));
                }
            }else{
                return Result.fail("凭证头必须有一列以上，凭证行信息必须有两列以上(至少一借一贷)");
            }
        }else{
            return  Result.fail("请选择内容");
        }

        return Result.success("保存成功");
    }

    @ApiOperation(value="获取凭证逻辑设置信息",notes="根据账薄Id和单据类型获取得到详细的凭证头信息 和行信息")
    @GetMapping(value="/getLogicInfo")
    public Result<AvVoucherLogicInfoVo> getLogicInfo (@RequestParam("ledgerId")Long ledgerId,@RequestParam("typeCode")String typeCode ){
        QueryWrapper<AvVoucherLogicInfo> fQueryWrapper = new QueryWrapper <AvVoucherLogicInfo>();
        fQueryWrapper.eq(AvVoucherLogicInfo.LEDGER_ID,ledgerId);
        fQueryWrapper.eq(AvVoucherLogicInfo.TYPE,typeCode);
        AvVoucherLogicInfo logicEntity = avVoucherLogicInfoService.getOne(fQueryWrapper);
        AvVoucherLogicInfoVo avVoucherLogicInfoVo=new BeanUtils<AvVoucherLogicInfoVo>().copyObj(logicEntity,AvVoucherLogicInfoVo.class);
        List<AvAccountLogicHeadVo> headVoList =new ArrayList<AvAccountLogicHeadVo>();
        List<AvAccountLogicLineVo> lineVoList = new  ArrayList<AvAccountLogicLineVo>();
        if(logicEntity!=null){
            List<AvAccountLogicHead> headList =avVoucherLogicInfoService.getLogicHeadListByLogicId(logicEntity.getId()) ;
            List<AvAccountLogicLine> lineList = avVoucherLogicInfoService.getLogicLineListByLogicId(logicEntity.getId());
            headVoList=new BeanUtils<AvAccountLogicHeadVo>().copyObjs(headList,AvAccountLogicHeadVo.class);
            lineVoList=new BeanUtils<AvAccountLogicLineVo>().copyObjs(lineList,AvAccountLogicLineVo.class);
        }
        avVoucherLogicInfoVo.setLedgerId(ledgerId);
        avVoucherLogicInfoVo.setType(typeCode);
        avVoucherLogicInfoVo.setHeadList(headVoList);
        avVoucherLogicInfoVo.setLineList(lineVoList);
        return  Result.success(avVoucherLogicInfoVo);
    }



    @ApiOperation(value = "删除AvVoucherLogicInfo", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AvVoucherLogicInfoID", required = true, dataType = "long")
    @DeleteMapping (value = "/delete")
    public Result delete(@PathVariable long id) {
        avVoucherLogicInfoService.removeById(id);
        return Result.success();
    }


    @ApiOperation(value = "修改AvVoucherLogicInfo", notes = "修改指定AvVoucherLogicInfo信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "AvVoucherLogicInfo的ID", required = true, dataType = "long")
    @PostMapping(value = "/update")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="avVoucherLogicInfoForm",value="修改AvVoucherLogicInfo的form表单",required=true)  AvVoucherLogicInfoForm avVoucherLogicInfoForm) {
        AvVoucherLogicInfo avVoucherLogicInfo =new BeanUtils<AvVoucherLogicInfo>().copyObj(avVoucherLogicInfoForm,AvVoucherLogicInfo.class);
        avVoucherLogicInfo.setId(id);
        avVoucherLogicInfoService.saveOrUpdate(avVoucherLogicInfo);
        return Result.success();
    }


    @ApiOperation(value = "获取AvVoucherLogicInfo", notes = "获取指定ID的AvVoucherLogicInfo信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AvVoucherLogicInfo的ID", required = true, dataType = "long")
    @GetMapping(value = "/get")
    public Result<AvVoucherLogicInfoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        AvVoucherLogicInfo avVoucherLogicInfo=avVoucherLogicInfoService.getById(id);
        AvVoucherLogicInfoVo avVoucherLogicInfoVo=new BeanUtils<AvVoucherLogicInfoVo>().copyObj(avVoucherLogicInfo,AvVoucherLogicInfoVo.class);
        return new Result<AvVoucherLogicInfoVo>().sucess(avVoucherLogicInfoVo);
    }

    @ApiOperation(value = "列表查询AvVoucherLogicInfo", notes = "根据条件查询AvVoucherLogicInfo列表数据")
    @GetMapping(value = "/list")
    public Result<List<AvVoucherLogicInfoVo>> list(@Valid @RequestBody @ApiParam(name="avVoucherLogicInfoQueryForm",value="AvVoucherLogicInfo查询参数",required=true) AvVoucherLogicInfoQueryForm avVoucherLogicInfoQueryForm) {
        log.info("search with avVoucherLogicInfoQueryForm:", avVoucherLogicInfoQueryForm.toString());
        List<AvVoucherLogicInfo> avVoucherLogicInfoList=avVoucherLogicInfoService.selectList(avVoucherLogicInfoQueryForm);
        List<AvVoucherLogicInfoVo> avVoucherLogicInfoVoList=new BeanUtils<AvVoucherLogicInfoVo>().copyObjs(avVoucherLogicInfoList,AvVoucherLogicInfoVo.class);
        return new Result<List<AvVoucherLogicInfoVo>>().sucess(avVoucherLogicInfoVoList);
    }

/*
    @ApiOperation(value = "分页查询AvVoucherLogicInfo", notes = "根据条件查询AvVoucherLogicInfo分页数据")
    @GetMapping(value = "/search")
    public Result<IPage<AvVoucherLogicInfoVo>> search(@Valid @RequestBody @ApiParam(name="avVoucherLogicInfoQueryForm",value="AvVoucherLogicInfo查询参数",required=true) AvVoucherLogicInfoQueryForm avVoucherLogicInfoQueryForm) {
        log.info("search with avVoucherLogicInfoQueryForm:", avVoucherLogicInfoQueryForm.toString());
        IPage<AvVoucherLogicInfo> avVoucherLogicInfoPage=avVoucherLogicInfoService.selectPage(avVoucherLogicInfoQueryForm);
        IPage<AvVoucherLogicInfoVo> avVoucherLogicInfoVoPage=new BeanUtils<AvVoucherLogicInfoVo>().copyPageObjs(avVoucherLogicInfoPage,AvVoucherLogicInfoVo.class);
        return new Result<IPage<AvVoucherLogicInfoVo>>().sucess(avVoucherLogicInfoVoPage);
    }*/

    @ApiOperation(value = "获取得到单据类型", notes = "根据账薄Id获取得到相关联的单据类型")
    @GetMapping(value = "/getDocumentTypeList")
    public Result getDocumentTypeList(@RequestParam("ledgerId")Long ledgerId){
        List<BaseDocumentType> list =  avVoucherLogicInfoService.getDocumentTypeList(ledgerId);
        List<BaseDocumentTypeVo> boList = new BeanUtils<BaseDocumentTypeVo>().copyObjs(list,BaseDocumentTypeVo.class);
        return  Result.success(boList);
    }

    @ApiOperation(value = "复制单据类型",notes ="根据单据类型code，复制到相应的单据类型下")
    @Transactional
    @PostMapping(value = "/copyDocumentType")
    public  Result copyDocumentType(@RequestBody @Valid AvLogicCopyDocumentForm form){
        String info = "";
        String fromTypeCode = form.getFromTypeCode();
        String toTypeCode = form.getToTypeCode();
        Long ledgerId = form.getLedgerId();
        UserVo userVo = commonServices.getCurrentUser();
        if(StringUtil.isNotEmpty(fromTypeCode)&&StringUtil.isNotEmpty(toTypeCode)){
            if(fromTypeCode.equals(toTypeCode)){
                info = "单据类型相同不能复制！";
                return Result.fail(info);
            }else{
                //查看需要被复制的单据下面是否有值。
                QueryWrapper<AvVoucherLogicInfo> queryWrapper = new QueryWrapper <AvVoucherLogicInfo>();
                queryWrapper.eq(AvVoucherLogicInfo.LEDGER_ID,ledgerId);
                queryWrapper.eq(AvVoucherLogicInfo.TYPE,toTypeCode);
                List<AvVoucherLogicInfo> toTypeList = avVoucherLogicInfoService.list(queryWrapper);
                if(toTypeList.size()<=0){
                    QueryWrapper<AvVoucherLogicInfo> fQueryWrapper = new QueryWrapper <AvVoucherLogicInfo>();
                    fQueryWrapper.eq(AvVoucherLogicInfo.LEDGER_ID,ledgerId);
                    fQueryWrapper.eq(AvVoucherLogicInfo.TYPE,fromTypeCode);
                    AvVoucherLogicInfo logicEntity = avVoucherLogicInfoService.getOne(fQueryWrapper);
                    if(logicEntity!=null){
                        List<AvAccountLogicHead> headList =avVoucherLogicInfoService.getLogicHeadListByLogicId(logicEntity.getId()) ;
                        List<AvAccountLogicLine> lineList = avVoucherLogicInfoService.getLogicLineListByLogicId(logicEntity.getId());
                        logicEntity.setId(null);
                        logicEntity.setType(toTypeCode);
                        logicEntity.setCreateBy(Long.parseLong(userVo.getId()));
                        logicEntity.setCreateDate(LocalDateTime.now());
                        avVoucherLogicInfoService.save(logicEntity);
                        for(AvAccountLogicHead p:headList){
                            p.setLogicId(logicEntity.getId());
                            p.setId(null);
                            p.setCrreteBy(Long.parseLong(userVo.getId()));
                            p.setCreateDate(LocalDateTime.now());
                            avAccountLogicHeadService.save(p);
                        }
                        for(AvAccountLogicLine p:lineList){
                            p.setLogicId(logicEntity.getId());
                            p.setCreateBy(Long.parseLong(userVo.getId()));
                            p.setId(null);
                            p.setCreateDate(LocalDateTime.now());
                            avAccountLogicLineService.save(p);
                        }
                    }else{
                        return Result.fail("被复制的单据类型下没有数据!");
                    }


                }else{
                    return Result.success("复制的单据类型下已经存在数据!");
                }
            }
        }
        return Result.success("保存成功");
    }

    @ApiOperation(value = "获取得到业务单据头和行信息的映射", notes = "获取得到业务单据头和行信息的映射")
    @GetMapping(value = "/getLogicDicRelationList")
    public Result<List<AvLogicHeadLineDicVo>> getLogicDicRelationList(@RequestParam("documentType")String documentType){
        QueryWrapper<AvLogicHeadLineDic> queryWrapper = new QueryWrapper<>();
        List<AvLogicHeadLineDic> dicList = new ArrayList<AvLogicHeadLineDic>();
        if(!FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue().equals(documentType)){//付款单凭证设置得时候需要显示全部得逻辑映射
            queryWrapper.eq(AvLogicHeadLineDic.DOCUMENT_MODULE,documentType);
        }
        dicList = avLogicHeadLineDicService.list(queryWrapper);
        List<AvLogicHeadLineDicVo> voList=new BeanUtils<AvLogicHeadLineDicVo>().copyObjs(dicList,AvLogicHeadLineDicVo.class);
        return  Result.success(voList);
    }

}



