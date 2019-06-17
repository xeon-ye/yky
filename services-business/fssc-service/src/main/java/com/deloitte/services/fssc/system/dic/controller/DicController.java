package com.deloitte.services.fssc.system.dic.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.vo.ModifyMainTypeStatusForm;
import com.deloitte.platform.api.fssc.dic.param.DicQueryForm;
import com.deloitte.platform.api.fssc.dic.param.DicValueQueryForm;
import com.deloitte.platform.api.fssc.dic.vo.DicForm;
import com.deloitte.platform.api.fssc.dic.vo.DicValueForm;
import com.deloitte.platform.api.fssc.dic.vo.DicValueVo;
import com.deloitte.platform.api.fssc.dic.vo.DicVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.dic.entity.Dic;
import com.deloitte.services.fssc.system.dic.entity.DicValue;
import com.deloitte.services.fssc.system.dic.service.IDicService;
import com.deloitte.services.fssc.system.dic.service.IDicValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description :   Dic控制器实现类
 * @Modified :
 */
@Api(tags = "数据字典操作接口")
@Slf4j
@RestController
public class DicController {

    private Logger logger = LoggerFactory.getLogger(DicController.class);

    @Autowired
    public IDicService dicService;

    @Autowired
    public IDicValueService dicValueService;

    @Autowired
    private FsscCommonServices fsscCommonServices;

    @Autowired
    private RedisTemplate redisTemplate;

//    @ApiOperation(value = "页面跳转", notes = "页面跳转")
//    @ApiResponses(
//            @ApiResponse(code = 200, message = "处理成功", response = ModelAndView.class)
//    )
//    // @GetMapping(value = path+"/index")
//    @RequestMapping(value = "fssc/dic/index")
//    public ModelAndView forward(ModelAndView modelAndView) {
//        modelAndView.setViewName("dic/dic_index");
//        return modelAndView;
//    }



    @ApiOperation(value = "分页列表查询Dic", notes = "根据条件查询Dic列表数据")
    @RequestMapping(value = "fssc/dic/list" ,method = RequestMethod.GET)
    @ResponseBody
    public Result<IPage<DicVo>> list(@Valid @ApiParam(name = "dicQueryForm", value = "Dic查询参数", required = true) DicQueryForm dicQueryForm) {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(dicQueryForm));
        IPage<Dic> unitInfoPage = dicService.selectPage(dicQueryForm);
        IPage<DicVo> unitInfoPageVo=new BeanUtils<DicVo>().copyPageObjs(unitInfoPage,DicVo.class);
        return Result.success(unitInfoPageVo);

    }



    /**
     * 删除数据字典值
     */
    @DeleteMapping(value = "fssc/dic/delete")
    @ApiImplicitParam(paramType = "path", name = "id", value = "DicID", required = true, dataType = "long" )
    @ApiOperation(value = "删除Dic", notes = "根据url的id来指定删除对象")
    @ResponseBody
    public Result removeType(@RequestBody List<Long> ids) {
        log.info("ids：{}", ids);
        //后台待验证是否为新建的
        //todo判断其他是否使用过这条记录 使用过的不能删除
        dicService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 数据字典类型-新增
     */
    @RequestMapping(value = "fssc/dic/add", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Result addOrUpdateDic(@Valid @RequestBody @ApiParam(name = "dicForm", value = "新增dicValue的form表单", required = true) DicForm dicForm) {
        log.info("form:{}", JSON.toJSONString(dicForm));
        Dic dic = new BeanUtils<Dic>().copyObj(dicForm, Dic.class);
        dic.setCreateTime(LocalDateTime.now());
        dic.setUpdateTime(LocalDateTime.now());
        logger.info("系统参数信息:" + JSON.toJSONString(dic));
        return Result.success(dicService.saveOrUpdate(dic));
    }




    /**
     * 分页获取系统参数
     */
    @RequestMapping(value = "fssc/dicvalue/list" ,method = RequestMethod.GET)
    @ResponseBody
        public Result<IPage<DicValueVo>> getValueList(@Valid @ApiParam(name = "dicValueForm", value = "新增dicValue的form表单", required = true) DicValueQueryForm dicValueForm) {
       log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(dicValueForm));
        //IPage<DicVo> unitInfoPage = dicService.findTypeByPage(dicQueryForm);
        IPage<DicValue> unitInfoPage = dicValueService.selectPage(dicValueForm);
        IPage<DicValueVo>  unitInfoPageVo=new BeanUtils<DicValueVo>().copyPageObjs(unitInfoPage,DicValueVo.class);
        return Result.success(unitInfoPageVo);

    }


    /**
     * 新增
     */
    @RequestMapping(value = {"fssc/dicvalue/add"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result addOrUpdateEnumValue(@Valid @RequestBody @ApiParam(name = "dicValueForm", value = "新增dicValue的form表单", required = true) DicValueForm dicValueForm
                               ) {
        log.info("form:{}", JSON.toJSONString(dicValueForm));
        String dValue=dicValueForm.getDicValue();
        String dicCode=dicValueForm.getDicCode();
        QueryWrapper<DicValue> dicQueryWrapper=new QueryWrapper<>();
        dicQueryWrapper.eq("DIC_CODE",dicCode);
        List<DicValue> dicValueList = dicValueService.list(dicQueryWrapper);
        if(dicValueForm.getId()==null){
            for(DicValue dv:dicValueList){
                if(dValue.equals(dv.getDicValue())){
                    throw new FSSCException(FsscErrorType.ON_SUBMIT_REGIN);
                }
            }
        }
        DicValue dicValue = new BeanUtils<DicValue>().copyObj(dicValueForm, DicValue.class);
        dicValue.setCreateTime(LocalDateTime.now());
        dicValue.setUpdateTime(LocalDateTime.now());
        logger.info("系统参数信息:" + JSON.toJSONString(dicValue));
        if(dicValueService.saveOrUpdate(dicValue)) {
            List<DicValueVo> dicValueVoList = new BeanUtils<DicValueVo>()
                    .copyObjs(dicValueService.list(dicQueryWrapper), DicValueVo.class);
            redisTemplate.opsForValue().set("FSSC_DIC_" + dicValueVoList, dicValueList, 1, TimeUnit.HOURS);
            return Result.success(true);
        }else{
            return Result.success(false);
        }
    }


    /**
     * 删除数据字典值
     */
    @DeleteMapping(value = "fssc/dicvalue/delete")
    @ResponseBody
    @ApiImplicitParam(paramType = "path", name = "id", value = "DicValueID", required = true, dataType = "long")
    @ApiOperation(value = "删除DicValue", notes = "根据url的id来指定删除对象")
    public Result delete(@RequestBody List<Long> ids) {
        log.info("ids：{}", ids);
        //后台待验证是否为新建的
        //todo判断其他是否使用过这条记录 使用过的不能删除
        dicValueService.removeByIds(ids);
        return Result.success();

    }

    @ApiOperation(value = "修改生失效状态", notes = "支持批量")
    @PostMapping(value = "fssc/dic/modifyMainTypeStatus")
    public Result modifyMainTypeStatus(@RequestBody @Valid ModifyMainTypeStatusForm form) {
        log.info("search with ModifyMainTypeStatusForm:{}", JSON.toJSONString(form));
        // TODO 如果原有状态与新状态相同,需要判断不让更新
        List<Long> ids = form.getIds();
        String status = form.getStatus();

        dicService.modifyValidFlag(ids, status);
        return Result.success();
    }

    @ApiOperation(value = "列表查询tDic", notes = "查询数据字典表信息")
    @GetMapping(value = "fssc/dic/queryDicValueList")
    public Result<List<DicValueVo>> list(@RequestParam("code")String code) {
        List<DicValueVo> list = fsscCommonServices.findDicValueList(code);
        return Result.success(list);
    }

    @ApiOperation(value = "列表查询城市tDic", notes = "查询数据字典表城市信息")
    @GetMapping(value = "fssc/dic/listDicValueCity")
    public Result<List<DicValue>> listCity(@RequestParam("dicDesciption")String dicDesciption) {
        QueryWrapper<DicValue>  queryWrapper=new QueryWrapper<>();
        queryWrapper.like("DIC_DESCIPTION",dicDesciption);
        queryWrapper.eq("DIC_CODE","TRAVEL_SITE");
        List<DicValue> list = dicValueService.list(queryWrapper);
        return Result.success(list);
    }

    @ApiOperation(value = "列表所有查询tDic", notes = "查询数据字典表所有信息")
    @GetMapping(value = "fssc/dic/queryAllDicList")
    public Result<List<DicValueVo>> findAllDiList(@RequestParam("code")String code) {
        List<DicValueVo> list = fsscCommonServices.findAllDiList(code);
        return Result.success(list);
    }

    @ApiOperation(value = "获取功能模块", notes = "获取功能模块")
    @GetMapping(value = "fssc/dic/getFunctionModule")
    public Result<List<DicValueVo>> getFunctionModule() {
        List<DicValueVo> list = fsscCommonServices.findDicValueList(FsscEums.DOCUMENT_FUNCTION_MODULE.getValue());
        return Result.success(list);
    }

    @ApiOperation(value = "获取预算类型", notes = "获取预算类型")
    @GetMapping(value = "fssc/dic/getBudgetType")
    public Result<List<DicValueVo>> getBudgetType() {
        List<DicValueVo> list = fsscCommonServices.findDicValueList(FsscEums.BUDGET_TYPE.getValue());
        return Result.success(list);
    }
}




