package com.deloitte.services.srpmp.project.budget.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.srpmp.project.budget.SrpmsProjectBudgetAccountClient;
import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetAccountQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetAccountForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetAccountVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetAccount;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetAccountService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;


/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-19
 * @Description :   SrpmsProjectBudgetAccount控制器实现类
 * @Modified :
 */
@Api(tags = "预算科目管理操作接口")
@Slf4j
@RestController
public class SrpmsProjectBudgetAccountController implements SrpmsProjectBudgetAccountClient {

    @Autowired
    public ISrpmsProjectBudgetAccountService srpmsProjectBudgetAccountService;


    @Override
    @ApiOperation(value = "新增或更新预算科目", notes = "新增或更新预算科目")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiParam(name="srpmsProjectBudgetAccountForm",value="新增或更新SrpmsProjectBudgetAccount的form表单",required=true)
    public Result<Long> saveOrUpdate(@Valid @RequestBody SrpmsProjectBudgetAccountForm srpmsProjectBudgetAccountForm) {
        log.info("SrpmsProjectBudgetAccountController saveOrUpdate form: {}", srpmsProjectBudgetAccountForm.toString());
        Long id = srpmsProjectBudgetAccountService.saveOrUpdateBudgetAccount(srpmsProjectBudgetAccountForm);
        return new Result<Long>().sucess(id);
    }


    @Override
    @ApiOperation(value = "删除预算科目", notes = "根据url的id来指定删除预算科目")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectBudgetAccountID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        srpmsProjectBudgetAccountService.removeById(id);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "获取预算科目", notes = "获取预算科目")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectBudgetAccount的ID", required = true, dataType = "long")
    public Result<SrpmsProjectBudgetAccountVo> get(@PathVariable long id) {
        log.info("SrpmsProjectBudgetAccountController get with id:{}", id);
        SrpmsProjectBudgetAccount srpmsProjectBudgetAccount=srpmsProjectBudgetAccountService.getById(id);
        SrpmsProjectBudgetAccountVo srpmsProjectBudgetAccountVo=new BeanUtils<SrpmsProjectBudgetAccountVo>().copyObj(srpmsProjectBudgetAccount,SrpmsProjectBudgetAccountVo.class);
        return new Result<SrpmsProjectBudgetAccountVo>().sucess(srpmsProjectBudgetAccountVo);
    }

    @Override
    @ApiOperation(value = "列表查询预算科目", notes = "列表查询预算科目")
    @ApiParam(name="srpmsProjectBudgetAccountQueryForm",value="SrpmsProjectBudgetAccount查询参数",required=true)
    public Result<List<SrpmsProjectBudgetAccountVo>> list(@Valid @RequestBody SrpmsProjectBudgetAccountQueryForm srpmsProjectBudgetAccountQueryForm) {
        log.info("SrpmsProjectBudgetAccountController search: {}", srpmsProjectBudgetAccountQueryForm.toString());
        List<SrpmsProjectBudgetAccount> srpmsProjectBudgetAccountList=srpmsProjectBudgetAccountService.selectList(srpmsProjectBudgetAccountQueryForm);
        List<SrpmsProjectBudgetAccountVo> srpmsProjectBudgetAccountVoList=new BeanUtils<SrpmsProjectBudgetAccountVo>().copyObjs(srpmsProjectBudgetAccountList,SrpmsProjectBudgetAccountVo.class);
        return new Result<List<SrpmsProjectBudgetAccountVo>>().sucess(srpmsProjectBudgetAccountVoList);
    }


    @Override
    @ApiOperation(value = "分页查询预算科目", notes = "分页查询预算科目")
    @ApiParam(name="srpmsProjectBudgetAccountQueryForm",value="SrpmsProjectBudgetAccount查询参数",required=true)
    public Result<IPage<SrpmsProjectBudgetAccountVo>> search(@Valid @RequestBody SrpmsProjectBudgetAccountQueryForm srpmsProjectBudgetAccountQueryForm) {
        log.info("SrpmsProjectBudgetAccountController search page: {}", srpmsProjectBudgetAccountQueryForm.toString());
        IPage<SrpmsProjectBudgetAccount> srpmsProjectBudgetAccountPage=srpmsProjectBudgetAccountService.selectPage(srpmsProjectBudgetAccountQueryForm);
        IPage<SrpmsProjectBudgetAccountVo> srpmsProjectBudgetAccountVoPage=new BeanUtils<SrpmsProjectBudgetAccountVo>().copyPageObjs(srpmsProjectBudgetAccountPage,SrpmsProjectBudgetAccountVo.class);
        return new Result<IPage<SrpmsProjectBudgetAccountVo>>().sucess(srpmsProjectBudgetAccountVoPage);
    }

}



