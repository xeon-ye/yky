package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.DeputyAccountQueryForm;
import com.deloitte.platform.api.isump.vo.DeputyAccountForm;
import com.deloitte.platform.api.isump.vo.DeputyAccountVo;
import com.deloitte.platform.api.isump.DeputyAccountClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.isump.service.IDeputyAccountService;
import com.deloitte.services.isump.entity.DeputyAccount;


/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   DeputyAccount控制器实现类
 * @Modified :
 */
@Api(tags = "DeputyAccount操作接口")
@Slf4j
@RestController
public class DeputyAccountController implements DeputyAccountClient {

    @Autowired
    public IDeputyAccountService  deputyAccountService;


    @Override
    @ApiOperation(value = "新增DeputyAccount", notes = "新增一个DeputyAccount")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="deputyAccountForm",value="新增DeputyAccount的form表单",required=true)  DeputyAccountForm deputyAccountForm) {
        log.info("form:",  deputyAccountForm.toString());
        DeputyAccount deputyAccount =new BeanUtils<DeputyAccount>().copyObj(deputyAccountForm,DeputyAccount.class);
        return Result.success( deputyAccountService.save(deputyAccount));
    }


    @Override
    @ApiOperation(value = "删除DeputyAccount", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "DeputyAccountID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        deputyAccountService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改DeputyAccount", notes = "修改指定DeputyAccount信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "DeputyAccount的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="deputyAccountForm",value="修改DeputyAccount的form表单",required=true)  DeputyAccountForm deputyAccountForm) {
        DeputyAccount deputyAccount =new BeanUtils<DeputyAccount>().copyObj(deputyAccountForm,DeputyAccount.class);
        deputyAccount.setId(id);
        deputyAccountService.saveOrUpdate(deputyAccount);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取DeputyAccount", notes = "获取指定ID的DeputyAccount信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "DeputyAccount的ID", required = true, dataType = "long")
    public Result<DeputyAccountVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        DeputyAccount deputyAccount=deputyAccountService.getById(id);
        DeputyAccountVo deputyAccountVo=new BeanUtils<DeputyAccountVo>().copyObj(deputyAccount,DeputyAccountVo.class);
        return new Result<DeputyAccountVo>().sucess(deputyAccountVo);
    }

    @Override
    @ApiOperation(value = "列表查询DeputyAccount", notes = "根据条件查询DeputyAccount列表数据")
    public Result<List<DeputyAccountVo>> list(@Valid @RequestBody @ApiParam(name="deputyAccountQueryForm",value="DeputyAccount查询参数",required=true) DeputyAccountQueryForm deputyAccountQueryForm) {
        log.info("search with deputyAccountQueryForm:", deputyAccountQueryForm.toString());
        List<DeputyAccount> deputyAccountList=deputyAccountService.selectList(deputyAccountQueryForm);
        List<DeputyAccountVo> deputyAccountVoList=new BeanUtils<DeputyAccountVo>().copyObjs(deputyAccountList,DeputyAccountVo.class);
        return new Result<List<DeputyAccountVo>>().sucess(deputyAccountVoList);
    }


    @Override
    @ApiOperation(value = "分页查询DeputyAccount", notes = "根据条件查询DeputyAccount分页数据")
    public Result<IPage<DeputyAccountVo>> search(@Valid @RequestBody @ApiParam(name="deputyAccountQueryForm",value="DeputyAccount查询参数",required=true) DeputyAccountQueryForm deputyAccountQueryForm) {
        log.info("search with deputyAccountQueryForm:", deputyAccountQueryForm.toString());
        IPage<DeputyAccount> deputyAccountPage=deputyAccountService.selectPage(deputyAccountQueryForm);
        IPage<DeputyAccountVo> deputyAccountVoPage=new BeanUtils<DeputyAccountVo>().copyPageObjs(deputyAccountPage,DeputyAccountVo.class);
        return new Result<IPage<DeputyAccountVo>>().sucess(deputyAccountVoPage);
    }

}



