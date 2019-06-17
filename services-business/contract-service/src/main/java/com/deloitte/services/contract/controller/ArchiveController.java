package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.ArchiveQueryForm;
import com.deloitte.platform.api.contract.vo.ArchiveForm;
import com.deloitte.platform.api.contract.vo.ArchiveVo;
import com.deloitte.platform.api.contract.client.ArchiveClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.IArchiveService;
import com.deloitte.services.contract.entity.Archive;


/**
 * @Author : yangyq
 * @Date : Create in 2019-06-10
 * @Description :   Archive控制器实现类
 * @Modified :
 */
@Api(tags = "归档操作接口")
@Slf4j
@RestController
@RequestMapping("/Archive")
public class ArchiveController implements ArchiveClient {

    @Autowired
    public IArchiveService  archiveService;


    @Override
    @ApiOperation(value = "新增Archive", notes = "新增一个Archive")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="archiveForm",value="新增Archive的form表单",required=true)  ArchiveForm archiveForm) {
        log.info("form:",  archiveForm.toString());
        Archive archive =new BeanUtils<Archive>().copyObj(archiveForm,Archive.class);
        return Result.success( archiveService.save(archive));
    }


    @Override
    @ApiOperation(value = "删除Archive", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ArchiveID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        archiveService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Archive", notes = "修改指定Archive信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Archive的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="archiveForm",value="修改Archive的form表单",required=true)  ArchiveForm archiveForm) {
        Archive archive =new BeanUtils<Archive>().copyObj(archiveForm,Archive.class);
        archive.setId(id);
        archiveService.saveOrUpdate(archive);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Archive", notes = "获取指定ID的Archive信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Archive的ID", required = true, dataType = "long")
    public Result<ArchiveVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Archive archive=archiveService.getById(id);
        ArchiveVo archiveVo=new BeanUtils<ArchiveVo>().copyObj(archive,ArchiveVo.class);
        return new Result<ArchiveVo>().sucess(archiveVo);
    }

    @Override
    @ApiOperation(value = "列表查询Archive", notes = "根据条件查询Archive列表数据")
    public Result<List<ArchiveVo>> list(@Valid @RequestBody @ApiParam(name="archiveQueryForm",value="Archive查询参数",required=true) ArchiveQueryForm archiveQueryForm) {
        log.info("search with archiveQueryForm:", archiveQueryForm.toString());
        List<Archive> archiveList=archiveService.selectList(archiveQueryForm);
        List<ArchiveVo> archiveVoList=new BeanUtils<ArchiveVo>().copyObjs(archiveList,ArchiveVo.class);
        return new Result<List<ArchiveVo>>().sucess(archiveVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Archive", notes = "根据条件查询Archive分页数据")
    public Result<IPage<ArchiveVo>> search(@Valid @RequestBody @ApiParam(name="archiveQueryForm",value="Archive查询参数",required=true) ArchiveQueryForm archiveQueryForm) {
        log.info("search with archiveQueryForm:", archiveQueryForm.toString());
        IPage<Archive> archivePage=archiveService.selectPage(archiveQueryForm);
        IPage<ArchiveVo> archiveVoPage=new BeanUtils<ArchiveVo>().copyPageObjs(archivePage,ArchiveVo.class);
        return new Result<IPage<ArchiveVo>>().sucess(archiveVoPage);
    }

    @ApiOperation(value = "保持归档信息", notes = "保持归档信息")
    @PostMapping("/saveArchiveByContractId")
    public Result saveArchiveByContractId(@Valid @RequestBody ArchiveForm archiveForm){
        Archive archive =new BeanUtils<Archive>().copyObj(archiveForm,Archive.class);
        return archiveService.saveArchiveByContractId(archive);
    }
}


