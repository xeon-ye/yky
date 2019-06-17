package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.SysWatermarkQueryForm;
import com.deloitte.platform.api.contract.vo.SysWatermarkForm;
import com.deloitte.platform.api.contract.vo.SysWatermarkVo;
import com.deloitte.platform.api.contract.client.SysWatermarkClient;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.service.ICommonService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import com.deloitte.services.contract.service.ISysWatermarkService;
import com.deloitte.services.contract.entity.SysWatermark;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   SysWatermark控制器实现类
 * @Modified :
 */
@Api(tags = "系统水印操作接口")
@Slf4j
@RestController
@RequestMapping("/sysWatermark")
public class SysWatermarkController implements SysWatermarkClient {

    @Autowired
    public ISysWatermarkService  sysWatermarkService;

    @Autowired
    public ICommonService commonService;

    @Override
    @ApiOperation(value = "新增SysWatermark", notes = "新增一个SysWatermark")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="sysWatermarkForm",value="新增SysWatermark的form表单",required=true)  SysWatermarkForm sysWatermarkForm) {
        log.info("form:",  sysWatermarkForm.toString());
        SysWatermark sysWatermark =new BeanUtils<SysWatermark>().copyObj(sysWatermarkForm,SysWatermark.class);
        return Result.success( sysWatermarkService.save(sysWatermark));
    }


    @Override
    @ApiOperation(value = "删除SysWatermark", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SysWatermarkID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        sysWatermarkService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改SysWatermark", notes = "修改指定SysWatermark信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "SysWatermark的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="sysWatermarkForm",value="修改SysWatermark的form表单",required=true)  SysWatermarkForm sysWatermarkForm) {
        SysWatermark sysWatermark =new BeanUtils<SysWatermark>().copyObj(sysWatermarkForm,SysWatermark.class);
        sysWatermark.setId(id);
        sysWatermarkService.saveOrUpdate(sysWatermark);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取SysWatermark", notes = "获取指定ID的SysWatermark信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SysWatermark的ID", required = true, dataType = "long")
    public Result<SysWatermarkVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        SysWatermark sysWatermark=sysWatermarkService.getById(id);
        SysWatermarkVo sysWatermarkVo=new BeanUtils<SysWatermarkVo>().copyObj(sysWatermark,SysWatermarkVo.class);
        return new Result<SysWatermarkVo>().sucess(sysWatermarkVo);
    }

    @Override
    @ApiOperation(value = "列表查询SysWatermark", notes = "根据条件查询SysWatermark列表数据")
    public Result<List<SysWatermarkVo>> list(@Valid @RequestBody @ApiParam(name="sysWatermarkQueryForm",value="SysWatermark查询参数",required=true) SysWatermarkQueryForm sysWatermarkQueryForm) {
        log.info("search with sysWatermarkQueryForm:", sysWatermarkQueryForm.toString());
        List<SysWatermark> sysWatermarkList=sysWatermarkService.selectList(sysWatermarkQueryForm);
        List<SysWatermarkVo> sysWatermarkVoList=new BeanUtils<SysWatermarkVo>().copyObjs(sysWatermarkList,SysWatermarkVo.class);
        return new Result<List<SysWatermarkVo>>().sucess(sysWatermarkVoList);
    }


    @Override
    @ApiOperation(value = "分页查询SysWatermark", notes = "根据条件查询SysWatermark分页数据")
    public Result<IPage<SysWatermarkVo>> search(@Valid @RequestBody @ApiParam(name="sysWatermarkQueryForm",value="SysWatermark查询参数",required=true) SysWatermarkQueryForm sysWatermarkQueryForm) {
        log.info("search with sysWatermarkQueryForm:", sysWatermarkQueryForm.toString());
        IPage<SysWatermark> sysWatermarkPage=sysWatermarkService.selectPage(sysWatermarkQueryForm);
        IPage<SysWatermarkVo> sysWatermarkVoPage=new BeanUtils<SysWatermarkVo>().copyPageObjs(sysWatermarkPage,SysWatermarkVo.class);
        return new Result<IPage<SysWatermarkVo>>().sucess(sysWatermarkVoPage);
    }

    @ApiOperation(value = "获取水印信息", notes = "获取水印信息")
    @PostMapping("/getWatermark")
    public Result<String> getWatermark() {
        OrganizationVo organizationVo = commonService.getOrganization();
        String departmentCode = organizationVo.getCode();
        String watermarkMap=sysWatermarkService.getWatermark(departmentCode);
        return new Result<String>().sucess(watermarkMap);
    }

    /**
     * yangyuanqing
     * @param sysWatermarkForm
     * @return
     */
    @ApiOperation(value = "修改水印信息", notes = "修改水印信息")
    @PostMapping("/updateByid")
    public Result<SysWatermarkVo> updateByid(@Valid @RequestBody SysWatermarkForm sysWatermarkForm){
        SysWatermark sysWatermark=new BeanUtils<SysWatermark>().copyObj(sysWatermarkForm,SysWatermark.class);
        sysWatermarkService.updateById(sysWatermark);
        return Result.success();
    }
}



