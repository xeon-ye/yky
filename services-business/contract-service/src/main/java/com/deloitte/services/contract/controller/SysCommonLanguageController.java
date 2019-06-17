package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.SysCommonLanguageQueryForm;
import com.deloitte.platform.api.contract.vo.SysCommonLanguageForm;
import com.deloitte.platform.api.contract.vo.SysCommonLanguageVo;
import com.deloitte.platform.api.contract.client.SysCommonLanguageClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.ISysCommonLanguageService;
import com.deloitte.services.contract.entity.SysCommonLanguage;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   SysCommonLanguage控制器实现类
 * @Modified :
 */
@Api(tags = "系统常用语操作接口")
@Slf4j
@RestController
public class SysCommonLanguageController implements SysCommonLanguageClient {

    @Autowired
    public ISysCommonLanguageService  sysCommonLanguageService;


    @Override
    @ApiOperation(value = "新增SysCommonLanguage", notes = "新增一个SysCommonLanguage")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="sysCommonLanguageForm",value="新增SysCommonLanguage的form表单",required=true)  SysCommonLanguageForm sysCommonLanguageForm) {
        log.info("form:",  sysCommonLanguageForm.toString());
        SysCommonLanguage sysCommonLanguage =new BeanUtils<SysCommonLanguage>().copyObj(sysCommonLanguageForm,SysCommonLanguage.class);
        return Result.success( sysCommonLanguageService.save(sysCommonLanguage));
    }


    @Override
    @ApiOperation(value = "删除SysCommonLanguage", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SysCommonLanguageID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        sysCommonLanguageService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改SysCommonLanguage", notes = "修改指定SysCommonLanguage信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "SysCommonLanguage的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="sysCommonLanguageForm",value="修改SysCommonLanguage的form表单",required=true)  SysCommonLanguageForm sysCommonLanguageForm) {
        SysCommonLanguage sysCommonLanguage =new BeanUtils<SysCommonLanguage>().copyObj(sysCommonLanguageForm,SysCommonLanguage.class);
        sysCommonLanguage.setId(id);
        sysCommonLanguageService.saveOrUpdate(sysCommonLanguage);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取SysCommonLanguage", notes = "获取指定ID的SysCommonLanguage信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SysCommonLanguage的ID", required = true, dataType = "long")
    public Result<SysCommonLanguageVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        SysCommonLanguage sysCommonLanguage=sysCommonLanguageService.getById(id);
        SysCommonLanguageVo sysCommonLanguageVo=new BeanUtils<SysCommonLanguageVo>().copyObj(sysCommonLanguage,SysCommonLanguageVo.class);
        return new Result<SysCommonLanguageVo>().sucess(sysCommonLanguageVo);
    }

    @Override
    @ApiOperation(value = "列表查询SysCommonLanguage", notes = "根据条件查询SysCommonLanguage列表数据")
    public Result<List<SysCommonLanguageVo>> list(@Valid @RequestBody @ApiParam(name="sysCommonLanguageQueryForm",value="SysCommonLanguage查询参数",required=true) SysCommonLanguageQueryForm sysCommonLanguageQueryForm) {
        log.info("search with sysCommonLanguageQueryForm:", sysCommonLanguageQueryForm.toString());
        List<SysCommonLanguage> sysCommonLanguageList=sysCommonLanguageService.selectList(sysCommonLanguageQueryForm);
        List<SysCommonLanguageVo> sysCommonLanguageVoList=new BeanUtils<SysCommonLanguageVo>().copyObjs(sysCommonLanguageList,SysCommonLanguageVo.class);
        return new Result<List<SysCommonLanguageVo>>().sucess(sysCommonLanguageVoList);
    }


    @Override
    @ApiOperation(value = "分页查询SysCommonLanguage", notes = "根据条件查询SysCommonLanguage分页数据")
    public Result<IPage<SysCommonLanguageVo>> search(@Valid @RequestBody @ApiParam(name="sysCommonLanguageQueryForm",value="SysCommonLanguage查询参数",required=true) SysCommonLanguageQueryForm sysCommonLanguageQueryForm) {
        log.info("search with sysCommonLanguageQueryForm:", sysCommonLanguageQueryForm.toString());
        IPage<SysCommonLanguage> sysCommonLanguagePage=sysCommonLanguageService.selectPage(sysCommonLanguageQueryForm);
        IPage<SysCommonLanguageVo> sysCommonLanguageVoPage=new BeanUtils<SysCommonLanguageVo>().copyPageObjs(sysCommonLanguagePage,SysCommonLanguageVo.class);
        return new Result<IPage<SysCommonLanguageVo>>().sucess(sysCommonLanguageVoPage);
    }

}



