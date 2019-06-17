package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.SysContractNoQueryForm;
import com.deloitte.platform.api.contract.vo.SysContractNoForm;
import com.deloitte.platform.api.contract.vo.SysContractNoVo;
import com.deloitte.platform.api.contract.client.SysContractNoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import com.deloitte.services.contract.service.ISysContractNoService;
import com.deloitte.services.contract.entity.SysContractNo;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   SysContractNo控制器实现类
 * @Modified :
 */
@Api(tags = "系统合同编号号操作接口")
@Slf4j
@RestController
@RequestMapping("/sysContractNo")
public class SysContractNoController implements SysContractNoClient {

    @Autowired
    public ISysContractNoService  sysContractNoService;


    @Override
    @ApiOperation(value = "新增SysContractNo", notes = "新增一个SysContractNo")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="sysContractNoForm",value="新增SysContractNo的form表单",required=true)  SysContractNoForm sysContractNoForm) {
        log.info("form:",  sysContractNoForm.toString());
        SysContractNo sysContractNo =new BeanUtils<SysContractNo>().copyObj(sysContractNoForm,SysContractNo.class);
        return Result.success( sysContractNoService.save(sysContractNo));
    }


    @Override
    @ApiOperation(value = "删除SysContractNo", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SysContractNoID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        sysContractNoService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改SysContractNo", notes = "修改指定SysContractNo信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "SysContractNo的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="sysContractNoForm",value="修改SysContractNo的form表单",required=true)  SysContractNoForm sysContractNoForm) {
        SysContractNo sysContractNo =new BeanUtils<SysContractNo>().copyObj(sysContractNoForm,SysContractNo.class);
        sysContractNo.setId(id);
        sysContractNoService.saveOrUpdate(sysContractNo);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取SysContractNo", notes = "获取指定ID的SysContractNo信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SysContractNo的ID", required = true, dataType = "long")
    public Result<SysContractNoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        SysContractNo sysContractNo=sysContractNoService.getById(id);
        SysContractNoVo sysContractNoVo=new BeanUtils<SysContractNoVo>().copyObj(sysContractNo,SysContractNoVo.class);
        return new Result<SysContractNoVo>().sucess(sysContractNoVo);
    }

    @Override
    @ApiOperation(value = "列表查询SysContractNo", notes = "根据条件查询SysContractNo列表数据")
    public Result<List<SysContractNoVo>> list(@Valid @RequestBody @ApiParam(name="sysContractNoQueryForm",value="SysContractNo查询参数",required=true) SysContractNoQueryForm sysContractNoQueryForm) {
        log.info("search with sysContractNoQueryForm:", sysContractNoQueryForm.toString());
        List<SysContractNo> sysContractNoList=sysContractNoService.selectList(sysContractNoQueryForm);
        List<SysContractNoVo> sysContractNoVoList=new BeanUtils<SysContractNoVo>().copyObjs(sysContractNoList,SysContractNoVo.class);
        return new Result<List<SysContractNoVo>>().sucess(sysContractNoVoList);
    }


    @Override
    @ApiOperation(value = "分页查询SysContractNo", notes = "根据条件查询SysContractNo分页数据")
    public Result<IPage<SysContractNoVo>> search(@Valid @RequestBody @ApiParam(name="sysContractNoQueryForm",value="SysContractNo查询参数",required=true) SysContractNoQueryForm sysContractNoQueryForm) {
        log.info("search with sysContractNoQueryForm:", sysContractNoQueryForm.toString());
        IPage<SysContractNo> sysContractNoPage=sysContractNoService.selectPage(sysContractNoQueryForm);
        IPage<SysContractNoVo> sysContractNoVoPage=new BeanUtils<SysContractNoVo>().copyPageObjs(sysContractNoPage,SysContractNoVo.class);
        return new Result<IPage<SysContractNoVo>>().sucess(sysContractNoVoPage);
    }

    @ApiOperation(value = "查询合同编号", notes = "查询合同编号，并将编号加1")
    @PostMapping("/getContractNo")
    public Result getContractNo(@Valid @RequestBody @ApiParam(name="sysContractNoQueryForm",value="SysContractNo查询参数",required=true) SysContractNoQueryForm sysContractNoQueryForm) {
        log.info("search with sysContractNoQueryForm:", sysContractNoQueryForm);
        String contractNo = sysContractNoService.getContractNo(sysContractNoQueryForm);
        return new Result<String>().sucess(contractNo);
    }
}



