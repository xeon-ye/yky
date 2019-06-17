package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.SignInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SignInfoForm;
import com.deloitte.platform.api.contract.vo.SignInfoVo;
import com.deloitte.platform.api.contract.client.SignInfoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.ISignInfoService;
import com.deloitte.services.contract.entity.SignInfo;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   SignInfo控制器实现类
 * @Modified :
 */
@Api(tags = "签署信息操作接口")
@Slf4j
@RestController
public class SignInfoController implements SignInfoClient {

    @Autowired
    public ISignInfoService  signInfoService;


    @Override
    @ApiOperation(value = "新增SignInfo", notes = "新增一个SignInfo")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="signInfoForm",value="新增SignInfo的form表单",required=true)  SignInfoForm signInfoForm) {
        log.info("form:",  signInfoForm.toString());
        SignInfo signInfo =new BeanUtils<SignInfo>().copyObj(signInfoForm,SignInfo.class);
        return Result.success( signInfoService.save(signInfo));
    }


    @Override
    @ApiOperation(value = "删除SignInfo", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SignInfoID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        signInfoService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改SignInfo", notes = "修改指定SignInfo信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "SignInfo的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="signInfoForm",value="修改SignInfo的form表单",required=true)  SignInfoForm signInfoForm) {
        SignInfo signInfo =new BeanUtils<SignInfo>().copyObj(signInfoForm,SignInfo.class);
        signInfo.setId(id);
        signInfoService.saveOrUpdate(signInfo);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取SignInfo", notes = "获取指定ID的SignInfo信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SignInfo的ID", required = true, dataType = "long")
    public Result<SignInfoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        SignInfo signInfo=signInfoService.getById(id);
        SignInfoVo signInfoVo=new BeanUtils<SignInfoVo>().copyObj(signInfo,SignInfoVo.class);
        return new Result<SignInfoVo>().sucess(signInfoVo);
    }

    @Override
    @ApiOperation(value = "列表查询SignInfo", notes = "根据条件查询SignInfo列表数据")
    public Result<List<SignInfoVo>> list(@Valid @RequestBody @ApiParam(name="signInfoQueryForm",value="SignInfo查询参数",required=true) SignInfoQueryForm signInfoQueryForm) {
        log.info("search with signInfoQueryForm:", signInfoQueryForm.toString());
        List<SignInfo> signInfoList=signInfoService.selectList(signInfoQueryForm);
        List<SignInfoVo> signInfoVoList=new BeanUtils<SignInfoVo>().copyObjs(signInfoList,SignInfoVo.class);
        return new Result<List<SignInfoVo>>().sucess(signInfoVoList);
    }


    @Override
    @ApiOperation(value = "分页查询SignInfo", notes = "根据条件查询SignInfo分页数据")
    public Result<IPage<SignInfoVo>> search(@Valid @RequestBody @ApiParam(name="signInfoQueryForm",value="SignInfo查询参数",required=true) SignInfoQueryForm signInfoQueryForm) {
        log.info("search with signInfoQueryForm:", signInfoQueryForm.toString());
        IPage<SignInfo> signInfoPage=signInfoService.selectPage(signInfoQueryForm);
        IPage<SignInfoVo> signInfoVoPage=new BeanUtils<SignInfoVo>().copyPageObjs(signInfoPage,SignInfoVo.class);
        return new Result<IPage<SignInfoVo>>().sucess(signInfoVoPage);
    }

}



