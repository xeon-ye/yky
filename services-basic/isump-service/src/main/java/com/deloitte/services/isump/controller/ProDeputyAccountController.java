package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.ProDeputyAccountQueryForm;
import com.deloitte.platform.api.isump.vo.ProDeputyAccountForm;
import com.deloitte.platform.api.isump.vo.ProDeputyAccountVo;
import com.deloitte.platform.api.isump.ProDeputyAccountClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.isump.service.IProDeputyAccountService;
import com.deloitte.services.isump.entity.ProDeputyAccount;


/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description :   ProDeputyAccount控制器实现类
 * @Modified :
 */
@Api(tags = "ProDeputyAccount操作接口")
@Slf4j
@RestController
public class ProDeputyAccountController implements ProDeputyAccountClient {

    @Autowired
    public IProDeputyAccountService  proDeputyAccountService;


    @Override
    @ApiOperation(value = "新增ProDeputyAccount", notes = "新增一个ProDeputyAccount")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="proDeputyAccountForm",value="新增ProDeputyAccount的form表单",required=true)  ProDeputyAccountForm proDeputyAccountForm) {
        log.info("form:",  proDeputyAccountForm.toString());
        ProDeputyAccount proDeputyAccount =new BeanUtils<ProDeputyAccount>().copyObj(proDeputyAccountForm,ProDeputyAccount.class);
        return Result.success( proDeputyAccountService.save(proDeputyAccount));
    }


    @Override
    @ApiOperation(value = "删除ProDeputyAccount", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProDeputyAccountID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        proDeputyAccountService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ProDeputyAccount", notes = "修改指定ProDeputyAccount信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ProDeputyAccount的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="proDeputyAccountForm",value="修改ProDeputyAccount的form表单",required=true)  ProDeputyAccountForm proDeputyAccountForm) {
        ProDeputyAccount proDeputyAccount =new BeanUtils<ProDeputyAccount>().copyObj(proDeputyAccountForm,ProDeputyAccount.class);
        proDeputyAccount.setId(id);
        proDeputyAccountService.saveOrUpdate(proDeputyAccount);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ProDeputyAccount", notes = "获取指定ID的ProDeputyAccount信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProDeputyAccount的ID", required = true, dataType = "long")
    public Result<ProDeputyAccountVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ProDeputyAccount proDeputyAccount=proDeputyAccountService.getById(id);
        ProDeputyAccountVo proDeputyAccountVo=new BeanUtils<ProDeputyAccountVo>().copyObj(proDeputyAccount,ProDeputyAccountVo.class);
        return new Result<ProDeputyAccountVo>().sucess(proDeputyAccountVo);
    }

    @Override
    @ApiOperation(value = "列表查询ProDeputyAccount", notes = "根据条件查询ProDeputyAccount列表数据")
    public Result<List<ProDeputyAccountVo>> list(@Valid @RequestBody @ApiParam(name="proDeputyAccountQueryForm",value="ProDeputyAccount查询参数",required=true) ProDeputyAccountQueryForm proDeputyAccountQueryForm) {
        log.info("search with proDeputyAccountQueryForm:", proDeputyAccountQueryForm.toString());
        List<ProDeputyAccount> proDeputyAccountList=proDeputyAccountService.selectList(proDeputyAccountQueryForm);
        List<ProDeputyAccountVo> proDeputyAccountVoList=new BeanUtils<ProDeputyAccountVo>().copyObjs(proDeputyAccountList,ProDeputyAccountVo.class);
        return new Result<List<ProDeputyAccountVo>>().sucess(proDeputyAccountVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ProDeputyAccount", notes = "根据条件查询ProDeputyAccount分页数据")
    public Result<IPage<ProDeputyAccountVo>> search(@Valid @RequestBody @ApiParam(name="proDeputyAccountQueryForm",value="ProDeputyAccount查询参数",required=true) ProDeputyAccountQueryForm proDeputyAccountQueryForm) {
        log.info("search with proDeputyAccountQueryForm:", proDeputyAccountQueryForm.toString());
        IPage<ProDeputyAccount> proDeputyAccountPage=proDeputyAccountService.selectPage(proDeputyAccountQueryForm);
        IPage<ProDeputyAccountVo> proDeputyAccountVoPage=new BeanUtils<ProDeputyAccountVo>().copyPageObjs(proDeputyAccountPage,ProDeputyAccountVo.class);
        return new Result<IPage<ProDeputyAccountVo>>().sucess(proDeputyAccountVoPage);
    }

}



