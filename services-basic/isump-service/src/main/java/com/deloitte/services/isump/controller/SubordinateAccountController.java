package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.SubordinateAccountQueryForm;
import com.deloitte.platform.api.isump.vo.SubordinateAccountForm;
import com.deloitte.platform.api.isump.vo.SubordinateAccountVo;
import com.deloitte.platform.api.isump.SubordinateAccountClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.isump.service.ISubordinateAccountService;
import com.deloitte.services.isump.entity.SubordinateAccount;


/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   SubordinateAccount控制器实现类
 * @Modified :
 */
@Api(tags = "SubordinateAccount操作接口")
@Slf4j
@RestController
public class SubordinateAccountController implements SubordinateAccountClient {

    @Autowired
    public ISubordinateAccountService  subordinateAccountService;


    @Override
    @ApiOperation(value = "新增SubordinateAccount", notes = "新增一个SubordinateAccount")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="subordinateAccountForm",value="新增SubordinateAccount的form表单",required=true)  SubordinateAccountForm subordinateAccountForm) {
        log.info("form:",  subordinateAccountForm.toString());
        SubordinateAccount subordinateAccount =new BeanUtils<SubordinateAccount>().copyObj(subordinateAccountForm,SubordinateAccount.class);
        return Result.success( subordinateAccountService.save(subordinateAccount));
    }


    @Override
    @ApiOperation(value = "删除SubordinateAccount", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SubordinateAccountID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        subordinateAccountService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改SubordinateAccount", notes = "修改指定SubordinateAccount信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "SubordinateAccount的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="subordinateAccountForm",value="修改SubordinateAccount的form表单",required=true)  SubordinateAccountForm subordinateAccountForm) {
        SubordinateAccount subordinateAccount =new BeanUtils<SubordinateAccount>().copyObj(subordinateAccountForm,SubordinateAccount.class);
        subordinateAccount.setId(id);
        subordinateAccountService.saveOrUpdate(subordinateAccount);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取SubordinateAccount", notes = "获取指定ID的SubordinateAccount信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SubordinateAccount的ID", required = true, dataType = "long")
    public Result<SubordinateAccountVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        SubordinateAccount subordinateAccount=subordinateAccountService.getById(id);
        SubordinateAccountVo subordinateAccountVo=new BeanUtils<SubordinateAccountVo>().copyObj(subordinateAccount,SubordinateAccountVo.class);
        return new Result<SubordinateAccountVo>().sucess(subordinateAccountVo);
    }

    @Override
    @ApiOperation(value = "列表查询SubordinateAccount", notes = "根据条件查询SubordinateAccount列表数据")
    public Result<List<SubordinateAccountVo>> list(@Valid @RequestBody @ApiParam(name="subordinateAccountQueryForm",value="SubordinateAccount查询参数",required=true) SubordinateAccountQueryForm subordinateAccountQueryForm) {
        log.info("search with subordinateAccountQueryForm:", subordinateAccountQueryForm.toString());
        List<SubordinateAccount> subordinateAccountList=subordinateAccountService.selectList(subordinateAccountQueryForm);
        List<SubordinateAccountVo> subordinateAccountVoList=new BeanUtils<SubordinateAccountVo>().copyObjs(subordinateAccountList,SubordinateAccountVo.class);
        return new Result<List<SubordinateAccountVo>>().sucess(subordinateAccountVoList);
    }


    @Override
    @ApiOperation(value = "分页查询SubordinateAccount", notes = "根据条件查询SubordinateAccount分页数据")
    public Result<IPage<SubordinateAccountVo>> search(@Valid @RequestBody @ApiParam(name="subordinateAccountQueryForm",value="SubordinateAccount查询参数",required=true) SubordinateAccountQueryForm subordinateAccountQueryForm) {
        log.info("search with subordinateAccountQueryForm:", subordinateAccountQueryForm.toString());
        IPage<SubordinateAccount> subordinateAccountPage=subordinateAccountService.selectPage(subordinateAccountQueryForm);
        IPage<SubordinateAccountVo> subordinateAccountVoPage=new BeanUtils<SubordinateAccountVo>().copyPageObjs(subordinateAccountPage,SubordinateAccountVo.class);
        return new Result<IPage<SubordinateAccountVo>>().sucess(subordinateAccountVoPage);
    }

}



