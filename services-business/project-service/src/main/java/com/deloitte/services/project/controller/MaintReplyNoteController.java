package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.MaintReplyNoteQueryForm;
import com.deloitte.platform.api.project.vo.MaintReplyNoteForm;
import com.deloitte.platform.api.project.vo.MaintReplyNoteVo;
import com.deloitte.platform.api.project.client.MaintReplyNoteClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IMaintReplyNoteService;
import com.deloitte.services.project.entity.MaintReplyNote;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :   MaintReplyNote控制器实现类
 * @Modified :
 */
@Api(tags = "MaintReplyNote操作接口")
@Slf4j
@RestController
public class MaintReplyNoteController implements MaintReplyNoteClient {

    @Autowired
    public IMaintReplyNoteService  maintReplyNoteService;


    @Override
    @ApiOperation(value = "新增MaintReplyNote", notes = "新增一个MaintReplyNote")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="maintReplyNoteForm",value="新增MaintReplyNote的form表单",required=true)  MaintReplyNoteForm maintReplyNoteForm) {
        log.info("form:",  maintReplyNoteForm.toString());
        MaintReplyNote maintReplyNote =new BeanUtils<MaintReplyNote>().copyObj(maintReplyNoteForm,MaintReplyNote.class);
        return Result.success( maintReplyNoteService.save(maintReplyNote));
    }


    @Override
    @ApiOperation(value = "删除MaintReplyNote", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "MaintReplyNoteID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        maintReplyNoteService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改MaintReplyNote", notes = "修改指定MaintReplyNote信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "MaintReplyNote的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="maintReplyNoteForm",value="修改MaintReplyNote的form表单",required=true)  MaintReplyNoteForm maintReplyNoteForm) {
        MaintReplyNote maintReplyNote =new BeanUtils<MaintReplyNote>().copyObj(maintReplyNoteForm,MaintReplyNote.class);
        maintReplyNote.setId(id);
        maintReplyNoteService.saveOrUpdate(maintReplyNote);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取MaintReplyNote", notes = "获取指定ID的MaintReplyNote信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "MaintReplyNote的ID", required = true, dataType = "long")
    public Result<MaintReplyNoteVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        MaintReplyNote maintReplyNote=maintReplyNoteService.getById(id);
        MaintReplyNoteVo maintReplyNoteVo=new BeanUtils<MaintReplyNoteVo>().copyObj(maintReplyNote,MaintReplyNoteVo.class);
        return new Result<MaintReplyNoteVo>().sucess(maintReplyNoteVo);
    }

    @Override
    @ApiOperation(value = "列表查询MaintReplyNote", notes = "根据条件查询MaintReplyNote列表数据")
    public Result<List<MaintReplyNoteVo>> list(@Valid @RequestBody @ApiParam(name="maintReplyNoteQueryForm",value="MaintReplyNote查询参数",required=true) MaintReplyNoteQueryForm maintReplyNoteQueryForm) {
        log.info("search with maintReplyNoteQueryForm:", maintReplyNoteQueryForm.toString());
        List<MaintReplyNote> maintReplyNoteList=maintReplyNoteService.selectList(maintReplyNoteQueryForm);
        List<MaintReplyNoteVo> maintReplyNoteVoList=new BeanUtils<MaintReplyNoteVo>().copyObjs(maintReplyNoteList,MaintReplyNoteVo.class);
        return new Result<List<MaintReplyNoteVo>>().sucess(maintReplyNoteVoList);
    }


    @Override
    @ApiOperation(value = "分页查询MaintReplyNote", notes = "根据条件查询MaintReplyNote分页数据")
    public Result<IPage<MaintReplyNoteVo>> search(@Valid @RequestBody @ApiParam(name="maintReplyNoteQueryForm",value="MaintReplyNote查询参数",required=true) MaintReplyNoteQueryForm maintReplyNoteQueryForm) {
        log.info("search with maintReplyNoteQueryForm:", maintReplyNoteQueryForm.toString());
        IPage<MaintReplyNote> maintReplyNotePage=maintReplyNoteService.selectPage(maintReplyNoteQueryForm);
        IPage<MaintReplyNoteVo> maintReplyNoteVoPage=new BeanUtils<MaintReplyNoteVo>().copyPageObjs(maintReplyNotePage,MaintReplyNoteVo.class);
        return new Result<IPage<MaintReplyNoteVo>>().sucess(maintReplyNoteVoPage);
    }

}



