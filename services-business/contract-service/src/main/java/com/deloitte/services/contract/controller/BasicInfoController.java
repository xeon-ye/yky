package com.deloitte.services.contract.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.BasicInfoQueryForm;
import com.deloitte.platform.api.contract.vo.BasicInfoForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.contract.client.BasicInfoClient;
import com.deloitte.platform.api.contract.vo.FinanceInfoVo;
import com.deloitte.platform.api.contract.vo.FinanceInfoVoToFssc;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.service.IFinanceInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.IBasicInfoService;
import com.deloitte.services.contract.entity.BasicInfo;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   BasicInfo控制器实现类
 * @Modified :
 */
@Api(tags = "合同基本信息操作接口")
@Slf4j
@RestController
public class BasicInfoController implements BasicInfoClient {

    @Autowired
    public IBasicInfoService  basicInfoService;
    @Autowired
    public IFinanceInfoService iFinanceInfoService;

    @Override
    @ApiOperation(value = "新增BasicInfo", notes = "新增一个BasicInfo")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="basicInfoForm",value="新增BasicInfo的form表单",required=true)  BasicInfoForm basicInfoForm) {
        log.info("form:",  basicInfoForm.toString());
        BasicInfo basicInfo =new BeanUtils<BasicInfo>().copyObj(basicInfoForm,BasicInfo.class);
        return Result.success( basicInfoService.save(basicInfo));
    }


    @Override
    @ApiOperation(value = "删除BasicInfo", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BasicInfoID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        basicInfoService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改BasicInfo", notes = "修改指定BasicInfo信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "BasicInfo的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="basicInfoForm",value="修改BasicInfo的form表单",required=true)  BasicInfoForm basicInfoForm) {
        BasicInfo basicInfo =new BeanUtils<BasicInfo>().copyObj(basicInfoForm,BasicInfo.class);
        basicInfo.setId(id);
        basicInfoService.saveOrUpdate(basicInfo);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取BasicInfo", notes = "获取指定ID的BasicInfo信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BasicInfo的ID", required = true, dataType = "long")
    public Result<BasicInfoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        BasicInfo basicInfo=basicInfoService.getById(id);
        BasicInfoVo basicInfoVo=new BeanUtils<BasicInfoVo>().copyObj(basicInfo,BasicInfoVo.class);
        return new Result<BasicInfoVo>().sucess(basicInfoVo);
    }

    @Override
    @ApiOperation(value = "列表查询BasicInfo", notes = "根据条件查询BasicInfo列表数据")
    public Result<List<BasicInfoVo>> list(@Valid @RequestBody @ApiParam(name="basicInfoQueryForm",value="BasicInfo查询参数",required=true) BasicInfoQueryForm basicInfoQueryForm) {
        log.info("search with basicInfoQueryForm:", basicInfoQueryForm.toString());
        List<BasicInfo> basicInfoList=basicInfoService.selectList(basicInfoQueryForm);
        List<BasicInfoVo> basicInfoVoList=new BeanUtils<BasicInfoVo>().copyObjs(basicInfoList,BasicInfoVo.class);
        return new Result<List<BasicInfoVo>>().sucess(basicInfoVoList);
    }

    @Override
    public Result<IPage<BasicInfoVo>> search(BasicInfoQueryForm basicInfoQueryForm) {
        return null;
    }


//    @Override
//    @ApiOperation(value = "分页查询BasicInfo", notes = "根据条件查询BasicInfo分页数据")
//    public Result<IPage<BasicInfoVo>> search(@Valid @RequestBody @ApiParam(name="basicInfoQueryForm",value="BasicInfo查询参数",required=true) BasicInfoQueryForm basicInfoQueryForm) {
//        log.info("search with basicInfoQueryForm:", basicInfoQueryForm.toString());
//        IPage<BasicInfo> basicInfoPage=basicInfoService.selectPage(basicInfoQueryForm);
//        IPage<BasicInfoVo> basicInfoVoPage=new BeanUtils<BasicInfoVo>().copyPageObjs(basicInfoPage,BasicInfoVo.class);
//        return new Result<IPage<BasicInfoVo>>().sucess(basicInfoVoPage);
//    }

    /**
     * 保存印花税信息
     * @param basicInfoForm
     * @return
     */
    @Override
    @ApiOperation(value = "保存印花税信息", notes = "保存印花税信息")
    public Result saveImprint(@RequestBody BasicInfoForm basicInfoForm){
        log.info("保存印花税信息:{}", JSON.toJSONString(basicInfoForm));
        basicInfoService.saveImprint(basicInfoForm);
        return Result.success();
    }

    /**
     * 保存财务系统返回财务信息
     * @param listFinanceInfoVo
     * @return
     */
    @Override
    @ApiOperation(value = "保存财务系统返回财务信息", notes = "保存财务系统返回财务信息")
    public Result saveFinanceInfo(@RequestBody List<FinanceInfoVoToFssc> listFinanceInfoVo){
        log.info("保存财务系统返回财务信息:{}", JSON.toJSONString(listFinanceInfoVo));
        iFinanceInfoService.saveFinanceInfo(listFinanceInfoVo);
        return Result.success();
    }
}



