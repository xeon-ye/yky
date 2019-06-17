package com.deloitte.services.fssc.business.carryforward.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.carryforward.param.DssScientificReciveQueryForm;
import com.deloitte.platform.api.fssc.carryforward.vo.DssScientificReciveForm;
import com.deloitte.platform.api.fssc.carryforward.vo.DssScientificReciveVo;
import com.deloitte.platform.api.fssc.carryforward.client.DssScientificReciveClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.fssc.business.carryforward.service.IDssScientificReciveService;
import com.deloitte.services.fssc.business.carryforward.entity.DssScientificRecive;


/**
 * @Author : chenx
 * @Date : Create in 2019-06-11
 * @Description :   DssScientificRecive控制器实现类
 * @Modified :
 */
@Api(tags = "DssScientificRecive操作接口")
@Slf4j
@RestController
public class DssScientificReciveController implements DssScientificReciveClient {

    @Autowired
    public IDssScientificReciveService  dssScientificReciveService;


    @Override
    @ApiOperation(value = "新增DssScientificRecive", notes = "新增一个DssScientificRecive")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="dssScientificReciveForm",value="新增DssScientificRecive的form表单",required=true)  DssScientificReciveForm dssScientificReciveForm) {
        log.info("form:",  dssScientificReciveForm.toString());
        DssScientificRecive dssScientificRecive =new BeanUtils<DssScientificRecive>().copyObj(dssScientificReciveForm,DssScientificRecive.class);
        return Result.success( dssScientificReciveService.save(dssScientificRecive));
    }


    @Override
    @ApiOperation(value = "删除DssScientificRecive", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "DssScientificReciveID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        dssScientificReciveService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改DssScientificRecive", notes = "修改指定DssScientificRecive信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "DssScientificRecive的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="dssScientificReciveForm",value="修改DssScientificRecive的form表单",required=true)  DssScientificReciveForm dssScientificReciveForm) {
        DssScientificRecive dssScientificRecive =new BeanUtils<DssScientificRecive>().copyObj(dssScientificReciveForm,DssScientificRecive.class);
        dssScientificRecive.setId(id);
        dssScientificReciveService.saveOrUpdate(dssScientificRecive);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取DssScientificRecive", notes = "获取指定ID的DssScientificRecive信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "DssScientificRecive的ID", required = true, dataType = "long")
    public Result<DssScientificReciveVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        DssScientificRecive dssScientificRecive=dssScientificReciveService.getById(id);
        DssScientificReciveVo dssScientificReciveVo=new BeanUtils<DssScientificReciveVo>().copyObj(dssScientificRecive,DssScientificReciveVo.class);
        return new Result<DssScientificReciveVo>().sucess(dssScientificReciveVo);
    }

    @Override
    @ApiOperation(value = "列表查询DssScientificRecive", notes = "根据条件查询DssScientificRecive列表数据")
    public Result<List<DssScientificReciveVo>> list(@Valid @RequestBody @ApiParam(name="dssScientificReciveQueryForm",value="DssScientificRecive查询参数",required=true) DssScientificReciveQueryForm dssScientificReciveQueryForm) {
        log.info("search with dssScientificReciveQueryForm:", dssScientificReciveQueryForm.toString());
        List<DssScientificRecive> dssScientificReciveList=dssScientificReciveService.selectList(dssScientificReciveQueryForm);
        List<DssScientificReciveVo> dssScientificReciveVoList=new BeanUtils<DssScientificReciveVo>().copyObjs(dssScientificReciveList,DssScientificReciveVo.class);
        return new Result<List<DssScientificReciveVo>>().sucess(dssScientificReciveVoList);
    }


    @Override
    @ApiOperation(value = "分页查询DssScientificRecive", notes = "根据条件查询DssScientificRecive分页数据")
    public Result<IPage<DssScientificReciveVo>> search(@Valid @RequestBody @ApiParam(name="dssScientificReciveQueryForm",value="DssScientificRecive查询参数",required=true) DssScientificReciveQueryForm dssScientificReciveQueryForm) {
        log.info("search with dssScientificReciveQueryForm:", dssScientificReciveQueryForm.toString());
        IPage<DssScientificRecive> dssScientificRecivePage=dssScientificReciveService.selectPage(dssScientificReciveQueryForm);
        IPage<DssScientificReciveVo> dssScientificReciveVoPage=new BeanUtils<DssScientificReciveVo>().copyPageObjs(dssScientificRecivePage,DssScientificReciveVo.class);
        return new Result<IPage<DssScientificReciveVo>>().sucess(dssScientificReciveVoPage);
    }

}



