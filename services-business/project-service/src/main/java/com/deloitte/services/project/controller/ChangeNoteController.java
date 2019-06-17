package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ChangeNoteQueryForm;
import com.deloitte.platform.api.project.vo.ChangeNoteForm;
import com.deloitte.platform.api.project.vo.ChangeNoteVo;
import com.deloitte.platform.api.project.client.ChangeNoteClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IChangeNoteService;
import com.deloitte.services.project.entity.ChangeNote;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :   ChangeNote控制器实现类
 * @Modified :
 */
@Api(tags = "ChangeNote操作接口")
@Slf4j
@RestController
public class ChangeNoteController implements ChangeNoteClient {

    @Autowired
    public IChangeNoteService  changeNoteService;


    @Override
    @ApiOperation(value = "新增ChangeNote", notes = "新增一个ChangeNote")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="changeNoteForm",value="新增ChangeNote的form表单",required=true)  ChangeNoteForm changeNoteForm) {
        log.info("form:",  changeNoteForm.toString());
        ChangeNote changeNote =new BeanUtils<ChangeNote>().copyObj(changeNoteForm,ChangeNote.class);
        return Result.success( changeNoteService.save(changeNote));
    }


    @Override
    @ApiOperation(value = "删除ChangeNote", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ChangeNoteID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        changeNoteService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ChangeNote", notes = "修改指定ChangeNote信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ChangeNote的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="changeNoteForm",value="修改ChangeNote的form表单",required=true)  ChangeNoteForm changeNoteForm) {
        ChangeNote changeNote =new BeanUtils<ChangeNote>().copyObj(changeNoteForm,ChangeNote.class);
        changeNote.setId(id);
        changeNoteService.saveOrUpdate(changeNote);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ChangeNote", notes = "获取指定ID的ChangeNote信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ChangeNote的ID", required = true, dataType = "long")
    public Result<ChangeNoteVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ChangeNote changeNote=changeNoteService.getById(id);
        ChangeNoteVo changeNoteVo=new BeanUtils<ChangeNoteVo>().copyObj(changeNote,ChangeNoteVo.class);
        return new Result<ChangeNoteVo>().sucess(changeNoteVo);
    }

    @Override
    @ApiOperation(value = "列表查询ChangeNote", notes = "根据条件查询ChangeNote列表数据")
    public Result<List<ChangeNoteVo>> list(@Valid @RequestBody @ApiParam(name="changeNoteQueryForm",value="ChangeNote查询参数",required=true) ChangeNoteQueryForm changeNoteQueryForm) {
        log.info("search with changeNoteQueryForm:", changeNoteQueryForm.toString());
        List<ChangeNote> changeNoteList=changeNoteService.selectList(changeNoteQueryForm);
        List<ChangeNoteVo> changeNoteVoList=new BeanUtils<ChangeNoteVo>().copyObjs(changeNoteList,ChangeNoteVo.class);
        return new Result<List<ChangeNoteVo>>().sucess(changeNoteVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ChangeNote", notes = "根据条件查询ChangeNote分页数据")
    public Result<IPage<ChangeNoteVo>> search(@Valid @RequestBody @ApiParam(name="changeNoteQueryForm",value="ChangeNote查询参数",required=true) ChangeNoteQueryForm changeNoteQueryForm) {
        log.info("search with changeNoteQueryForm:", changeNoteQueryForm.toString());
        IPage<ChangeNote> changeNotePage=changeNoteService.selectPage(changeNoteQueryForm);
        IPage<ChangeNoteVo> changeNoteVoPage=new BeanUtils<ChangeNoteVo>().copyPageObjs(changeNotePage,ChangeNoteVo.class);
        return new Result<IPage<ChangeNoteVo>>().sucess(changeNoteVoPage);
    }

}



