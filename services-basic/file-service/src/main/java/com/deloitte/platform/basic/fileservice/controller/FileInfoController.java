package com.deloitte.platform.basic.fileservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fileservice.param.FileInfoQueryForm;
import com.deloitte.platform.api.fileservice.vo.FileInfoForm;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.fileservice.FileInfoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.platform.basic.fileservice.service.IFileInfoService;
import com.deloitte.platform.basic.fileservice.entity.FileInfo;


/**
 * @Author : jackliu
 * @Date : Create in 2019-02-19
 * @Description :   FileInfo控制器实现类
 * @Modified :
 */
@Api("FileInfo")
@Slf4j
@RestController
public class FileInfoController implements FileInfoClient {

    @Autowired
    public IFileInfoService  fileInfoService;


    @Override
    @ApiOperation(value = "新增FileInfo", notes = "新增一个FileInfo")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="fileInfoForm",value="新增FileInfo的form表单",required=true)  FileInfoForm fileInfoForm) {
        log.info("form:",  fileInfoForm.toString());
        FileInfo fileInfo =new BeanUtils<FileInfo>().copyObj(fileInfoForm,FileInfo.class);
        return Result.success( fileInfoService.save(fileInfo));
    }


    @Override
    @ApiOperation(value = "删除FileInfo", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "FileInfoID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        fileInfoService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改FileInfo", notes = "修改指定FileInfo信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "FileInfo的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="fileInfoForm",value="修改FileInfo的form表单",required=true)  FileInfoForm fileInfoForm) {
        FileInfo fileInfo =new BeanUtils<FileInfo>().copyObj(fileInfoForm,FileInfo.class);
        fileInfo.setId(id);
        fileInfoService.saveOrUpdate(fileInfo);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取FileInfo", notes = "获取指定ID的FileInfo信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "FileInfo的ID", required = true, dataType = "long")
    public Result<FileInfoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        FileInfo fileInfo=fileInfoService.getById(id);
        FileInfoVo fileInfoVo=new BeanUtils<FileInfoVo>().copyObj(fileInfo,FileInfoVo.class);
        return new Result<FileInfoVo>().sucess(fileInfoVo);
    }

    @Override
    @ApiOperation(value = "列表查询FileInfo", notes = "根据条件查询FileInfo列表数据")
    public Result<List<FileInfoVo>> list(@Valid @RequestBody @ApiParam(name="fileInfoQueryForm",value="FileInfo查询参数",required=true) FileInfoQueryForm fileInfoQueryForm) {
        log.info("search with fileInfoQueryForm:", fileInfoQueryForm.toString());
        List<FileInfo> fileInfoList=fileInfoService.selectList(fileInfoQueryForm);
        List<FileInfoVo> fileInfoVoList=new BeanUtils<FileInfoVo>().copyObjs(fileInfoList,FileInfoVo.class);
        return new Result<List<FileInfoVo>>().sucess(fileInfoVoList);
    }


    @Override
    @ApiOperation(value = "分页查询FileInfo", notes = "根据条件查询FileInfo分页数据")
    public Result<IPage<FileInfoVo>> search(@Valid @RequestBody @ApiParam(name="fileInfoQueryForm",value="FileInfo查询参数",required=true) FileInfoQueryForm fileInfoQueryForm) {
        log.info("search with fileInfoQueryForm:", fileInfoQueryForm.toString());
        IPage<FileInfo> fileInfoPage=fileInfoService.selectPage(fileInfoQueryForm);
        IPage<FileInfoVo> fileInfoVoPage=new BeanUtils<FileInfoVo>().copyPageObjs(fileInfoPage,FileInfoVo.class);
        return new Result<IPage<FileInfoVo>>().sucess(fileInfoVoPage);
    }

}



