package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.DictQueryForm;
import com.deloitte.platform.api.isump.vo.DictForm;
import com.deloitte.platform.api.isump.vo.DictVo;
import com.deloitte.platform.api.isump.DictClient;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.isump.util.DictUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import com.deloitte.services.isump.service.IDictService;
import com.deloitte.services.isump.entity.Dict;


/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   Dict控制器实现类
 * @Modified :
 */
@Api(tags = "Dict操作接口")
@Slf4j
@RestController
public class DictController implements DictClient {

    @Autowired
    public IDictService  dictService;


    @Override
    @ApiOperation(value = "新增Dict", notes = "新增一个Dict")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="dictForm",value="新增Dict的form表单",required=true)  DictForm dictForm) {
        log.info("form:",  dictForm.toString());
        Dict dict =new BeanUtils<Dict>().copyObj(dictForm,Dict.class);
        return Result.success( dictService.save(dict));
    }


    @Override
    @ApiOperation(value = "删除Dict", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "DictID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        dictService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Dict", notes = "修改指定Dict信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Dict的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="dictForm",value="修改Dict的form表单",required=true)  DictForm dictForm) {
        Dict dict =new BeanUtils<Dict>().copyObj(dictForm,Dict.class);
        dict.setId(id);
        dictService.saveOrUpdate(dict);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Dict", notes = "获取指定ID的Dict信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Dict的ID", required = true, dataType = "long")
    public Result<DictVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Dict dict=dictService.getById(id);
        DictVo dictVo=new BeanUtils<DictVo>().copyObj(dict,DictVo.class);
        return new Result<DictVo>().sucess(dictVo);
    }

    @Override
    @ApiOperation(value = "列表查询Dict", notes = "根据条件查询Dict列表数据")
    public Result<List<DictVo>> list(@Valid @RequestBody @ApiParam(name="dictQueryForm",value="Dict查询参数",required=true) DictQueryForm dictQueryForm) {
        log.info("search with dictQueryForm:", dictQueryForm.toString());
        List<Dict> dictList=dictService.selectList(dictQueryForm);
        List<DictVo> dictVoList=new BeanUtils<DictVo>().copyObjs(dictList,DictVo.class);
        return new Result<List<DictVo>>().sucess(dictVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Dict", notes = "根据条件查询Dict分页数据")
    public Result<IPage<DictVo>> search(@Valid @RequestBody @ApiParam(name="dictQueryForm",value="Dict查询参数",required=true) DictQueryForm dictQueryForm) {
        log.info("search with dictQueryForm:", dictQueryForm.toString());
        IPage<Dict> dictPage=dictService.selectPage(dictQueryForm);
        IPage<DictVo> dictVoPage=new BeanUtils<DictVo>().copyPageObjs(dictPage,DictVo.class);
        return new Result<IPage<DictVo>>().sucess(dictVoPage);
    }

    @Override
    @ApiOperation(value = "分页查询Dict,微服务调用", notes = "根据条件查询Dict分页数据")
    public Result<GdcPage<DictVo>> searchForFeign(@Valid @RequestBody @ApiParam(name="dictQueryForm",value="Dict查询参数",required=true)
                                                              DictQueryForm dictQueryForm) {
        log.info("search with dictQueryForm:", dictQueryForm.toString());
        IPage<Dict> dictPage = dictService.selectPage(dictQueryForm);
        IPage<DictVo> dictVoPage = new BeanUtils<DictVo>().copyPageObjs(dictPage,DictVo.class);
        return new Result<GdcPage<DictVo>>().sucess(new GdcPage<OrganizationVo>(dictVoPage));
    }

    @Override
    @ApiOperation(value = "获取DictMap", notes = "获取指定类型的Dictmap")
    @ApiImplicitParam(paramType = "path", name = "code", value = "Dict的parentCode", required = true, dataType = "String")
    public Result getDictMap(@PathVariable String code){
        Map<String,String> map = DictUtil.getDict(code);
        return Result.success(map);
    }

}



