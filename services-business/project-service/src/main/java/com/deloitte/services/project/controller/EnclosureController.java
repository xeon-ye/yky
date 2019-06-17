package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.client.EnclosureClient;
import com.deloitte.platform.api.project.param.EnclosureQueryForm;
import com.deloitte.platform.api.project.vo.CancelProjectFrom;
import com.deloitte.platform.api.project.vo.EnclosureForm;
import com.deloitte.platform.api.project.vo.EnclosureVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.entity.Enclosure;
import com.deloitte.services.project.entity.ProjectEvaluation;
import com.deloitte.services.project.service.ICommonService;
import com.deloitte.services.project.service.IEnclosureService;
import com.deloitte.services.project.service.IProjectEvaluationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   Enclosure控制器实现类
 * @Modified :
 */
@Api(tags = "Enclosure操作接口")
@Slf4j
@RestController
@RequestMapping("/project/enclosure")
public class EnclosureController implements EnclosureClient {

    @Autowired
    public IEnclosureService  enclosureService;
    @Autowired
    private ICommonService commonService;
    @Autowired
    private IProjectEvaluationService projectEvaluationService;


    @Override
    @ApiOperation(value = "新增Enclosure", notes = "新增一个Enclosure")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="enclosureForm",value="新增Enclosure的form表单",required=true)  EnclosureForm enclosureForm) {
        log.info("form:",  enclosureForm.toString());
        Enclosure enclosure =new BeanUtils<Enclosure>().copyObj(enclosureForm,Enclosure.class);
        return Result.success( enclosureService.save(enclosure));
    }


    @Override
    @ApiOperation(value = "删除Enclosure", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "EnclosureID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        enclosureService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Enclosure", notes = "修改指定Enclosure信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Enclosure的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="enclosureForm",value="修改Enclosure的form表单",required=true)  EnclosureForm enclosureForm) {
        Enclosure enclosure =new BeanUtils<Enclosure>().copyObj(enclosureForm,Enclosure.class);
        enclosure.setId(id);
        enclosureService.saveOrUpdate(enclosure);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Enclosure", notes = "获取指定ID的Enclosure信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Enclosure的ID", required = true, dataType = "long")
    public Result<EnclosureVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Enclosure enclosure=enclosureService.getById(id);
        EnclosureVo enclosureVo=new BeanUtils<EnclosureVo>().copyObj(enclosure,EnclosureVo.class);
        return new Result<EnclosureVo>().sucess(enclosureVo);
    }

    @Override
    @ApiOperation(value = "列表查询Enclosure", notes = "根据条件查询Enclosure列表数据")
    public Result<List<EnclosureVo>> list(@Valid @RequestBody @ApiParam(name="enclosureQueryForm",value="Enclosure查询参数",required=true) EnclosureQueryForm enclosureQueryForm) {
        log.info("search with enclosureQueryForm:", enclosureQueryForm.toString());
        List<Enclosure> enclosureList=enclosureService.selectList(enclosureQueryForm);
        List<EnclosureVo> enclosureVoList=new BeanUtils<EnclosureVo>().copyObjs(enclosureList,EnclosureVo.class);
        return new Result<List<EnclosureVo>>().sucess(enclosureVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Enclosure", notes = "根据条件查询Enclosure分页数据")
    public Result<IPage<EnclosureVo>> search(@Valid @RequestBody @ApiParam(name="enclosureQueryForm",value="Enclosure查询参数",required=true) EnclosureQueryForm enclosureQueryForm) {
        log.info("search with enclosureQueryForm:", enclosureQueryForm.toString());
        IPage<Enclosure> enclosurePage=enclosureService.selectPage(enclosureQueryForm);
        IPage<EnclosureVo> enclosureVoPage=new BeanUtils<EnclosureVo>().copyPageObjs(enclosurePage,EnclosureVo.class);
        return new Result<IPage<EnclosureVo>>().sucess(enclosureVoPage);
    }

    @ApiOperation(value = "保存附件", notes = "保存附件，多类型")
    @PostMapping(value = "/saveEnclosureFile")
    public Result saveEnclosureFile(@Valid @RequestBody CancelProjectFrom cancelProjectFrom) {
        log.info("cancelProjectFrom: {}", cancelProjectFrom.toString());
        enclosureService.saveEnclosureFile(cancelProjectFrom);
        /*Page<ProjectEvaluation> page = new Page<>(cancelProjectFrom.getCurrentPage(), cancelProjectFrom.getPageSize());
        List<ProjectEvaluation> list = projectEvaluationService.selectEnclosureList(page, cancelProjectFrom.getProjectsForm().getProjectId());
        IPage<ProjectEvaluation> iPage = page.setRecords(list); @Valid @RequestBody CancelProjectFrom cancelProjectFrom*/
        //return new Result<IPage<ProjectEvaluation>>().success(iPage);
        return Result.success();
    }

    @ApiOperation(value = "【项目评价】--获取附件", notes = "【项目评价】--获取附件")
    @PostMapping(value = "/pages/getEnclosureFileList/{currentPage}/{pageSize}/{projectId}")
    public Result<IPage<ProjectEvaluation>> getEnclosureList(@PathVariable Long currentPage, @PathVariable Long pageSize, @PathVariable String projectId) {
        Page<ProjectEvaluation> page = new Page<>(currentPage, pageSize);
        List<ProjectEvaluation> list = projectEvaluationService.selectEnclosureList(page, projectId);
        IPage<ProjectEvaluation> iPage = page.setRecords(list);
        return new Result<IPage<ProjectEvaluation>>().success(iPage);
    }

    @ApiOperation(value = "pdf文件转换", notes = "pdf文件上传")
    public Result transToPDF(@PathVariable(value = "fileUrl") String fileUrl) {

        return Result.success();
    }

}



