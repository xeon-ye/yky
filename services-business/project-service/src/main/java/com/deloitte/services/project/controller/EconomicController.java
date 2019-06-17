package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.EconomicQueryForm;
import com.deloitte.platform.api.project.vo.EconomicForm;
import com.deloitte.platform.api.project.vo.EconomicVo;
import com.deloitte.platform.api.project.client.EconomicClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IEconomicService;
import com.deloitte.services.project.entity.Economic;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-15
 * @Description :   Economic控制器实现类
 * @Modified :
 */
@Api(tags = "Economic操作接口")
@Slf4j
@RestController
public class EconomicController implements EconomicClient {

    @Autowired
    public IEconomicService  economicService;


    @Override
    @ApiOperation(value = "新增Economic", notes = "新增一个Economic")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="economicForm",value="新增Economic的form表单",required=true)  EconomicForm economicForm) {
        log.info("form:",  economicForm.toString());
        Economic economic =new BeanUtils<Economic>().copyObj(economicForm,Economic.class);
        return Result.success( economicService.save(economic));
    }


    @Override
    @ApiOperation(value = "删除Economic", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "EconomicID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        economicService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Economic", notes = "修改指定Economic信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Economic的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="economicForm",value="修改Economic的form表单",required=true)  EconomicForm economicForm) {
        Economic economic =new BeanUtils<Economic>().copyObj(economicForm,Economic.class);
        economic.setId(id);
        economicService.saveOrUpdate(economic);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Economic", notes = "获取指定ID的Economic信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Economic的ID", required = true, dataType = "long")
    public Result<EconomicVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Economic economic=economicService.getById(id);
        EconomicVo economicVo=new BeanUtils<EconomicVo>().copyObj(economic,EconomicVo.class);
        return new Result<EconomicVo>().sucess(economicVo);
    }

    @Override
    @ApiOperation(value = "列表查询Economic", notes = "根据条件查询Economic列表数据")
    public Result<List<EconomicVo>> list(@Valid @RequestBody @ApiParam(name="economicQueryForm",value="Economic查询参数",required=true) EconomicQueryForm economicQueryForm) {
        log.info("search with economicQueryForm:", economicQueryForm.toString());
        List<Economic> economicList=economicService.selectList(economicQueryForm);
        List<EconomicVo> economicVoList=new BeanUtils<EconomicVo>().copyObjs(economicList,EconomicVo.class);
        return new Result<List<EconomicVo>>().sucess(economicVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Economic", notes = "根据条件查询Economic分页数据")
    public Result<IPage<EconomicVo>> search(@Valid @RequestBody @ApiParam(name="economicQueryForm",value="Economic查询参数",required=true) EconomicQueryForm economicQueryForm) {
        log.info("search with economicQueryForm:", economicQueryForm.toString());
        IPage<Economic> economicPage=economicService.selectPage(economicQueryForm);
        IPage<EconomicVo> economicVoPage=new BeanUtils<EconomicVo>().copyPageObjs(economicPage,EconomicVo.class);
        return new Result<IPage<EconomicVo>>().sucess(economicVoPage);
    }

}



