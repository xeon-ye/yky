package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ExpertQueryForm;
import com.deloitte.platform.api.project.vo.ExpertForm;
import com.deloitte.platform.api.project.vo.ExpertVo;
import com.deloitte.platform.api.project.client.ExpertClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IExpertService;
import com.deloitte.services.project.entity.Expert;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-16
 * @Description :   Expert控制器实现类
 * @Modified :
 */
@Api(tags = "Expert操作接口")
@Slf4j
@RestController
public class ExpertController implements ExpertClient {

    @Autowired
    public IExpertService  expertService;


    @Override
    @ApiOperation(value = "新增Expert", notes = "新增一个Expert")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="expertForm",value="新增Expert的form表单",required=true)  ExpertForm expertForm) {
        log.info("form:",  expertForm.toString());
        Expert expert =new BeanUtils<Expert>().copyObj(expertForm,Expert.class);
        return Result.success( expertService.save(expert));
    }


    @Override
    @ApiOperation(value = "删除Expert", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExpertID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        expertService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Expert", notes = "修改指定Expert信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Expert的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="expertForm",value="修改Expert的form表单",required=true)  ExpertForm expertForm) {
        Expert expert =new BeanUtils<Expert>().copyObj(expertForm,Expert.class);
        expert.setId(id);
        expertService.saveOrUpdate(expert);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Expert", notes = "获取指定ID的Expert信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Expert的ID", required = true, dataType = "long")
    public Result<ExpertVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Expert expert=expertService.getById(id);
        ExpertVo expertVo=new BeanUtils<ExpertVo>().copyObj(expert,ExpertVo.class);
        return new Result<ExpertVo>().sucess(expertVo);
    }

    @Override
    @ApiOperation(value = "列表查询Expert", notes = "根据条件查询Expert列表数据")
    public Result<List<ExpertVo>> list(@Valid @RequestBody @ApiParam(name="expertQueryForm",value="Expert查询参数",required=true) ExpertQueryForm expertQueryForm) {
        log.info("search with expertQueryForm:", expertQueryForm.toString());
        List<Expert> expertList=expertService.selectList(expertQueryForm);
        List<ExpertVo> expertVoList=new BeanUtils<ExpertVo>().copyObjs(expertList,ExpertVo.class);
        return new Result<List<ExpertVo>>().sucess(expertVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Expert", notes = "根据条件查询Expert分页数据")
    public Result<IPage<ExpertVo>> search(@Valid @RequestBody @ApiParam(name="expertQueryForm",value="Expert查询参数",required=true) ExpertQueryForm expertQueryForm) {
        log.info("search with expertQueryForm:", expertQueryForm.toString());
        IPage<Expert> expertPage=expertService.selectPage(expertQueryForm);
        IPage<ExpertVo> expertVoPage=new BeanUtils<ExpertVo>().copyPageObjs(expertPage,ExpertVo.class);
        return new Result<IPage<ExpertVo>>().sucess(expertVoPage);
    }

}



