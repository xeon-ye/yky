package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ReviewNoteQueryForm;
import com.deloitte.platform.api.project.vo.ReviewNoteForm;
import com.deloitte.platform.api.project.vo.ReviewNoteVo;
import com.deloitte.platform.api.project.client.ReviewNoteClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IReviewNoteService;
import com.deloitte.services.project.entity.ReviewNote;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-28
 * @Description :   ReviewNote控制器实现类
 * @Modified :
 */
@Api(tags = "ReviewNote操作接口")
@Slf4j
@RestController
public class ReviewNoteController implements ReviewNoteClient {

    @Autowired
    public IReviewNoteService  reviewNoteService;


    @Override
    @ApiOperation(value = "新增ReviewNote", notes = "新增一个ReviewNote")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="reviewNoteForm",value="新增ReviewNote的form表单",required=true)  ReviewNoteForm reviewNoteForm) {
        log.info("form:",  reviewNoteForm.toString());
        ReviewNote reviewNote =new BeanUtils<ReviewNote>().copyObj(reviewNoteForm,ReviewNote.class);
        return Result.success( reviewNoteService.save(reviewNote));
    }


    @Override
    @ApiOperation(value = "删除ReviewNote", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ReviewNoteID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        reviewNoteService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ReviewNote", notes = "修改指定ReviewNote信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ReviewNote的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="reviewNoteForm",value="修改ReviewNote的form表单",required=true)  ReviewNoteForm reviewNoteForm) {
        ReviewNote reviewNote =new BeanUtils<ReviewNote>().copyObj(reviewNoteForm,ReviewNote.class);
        reviewNote.setId(id);
        reviewNoteService.saveOrUpdate(reviewNote);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ReviewNote", notes = "获取指定ID的ReviewNote信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ReviewNote的ID", required = true, dataType = "long")
    public Result<ReviewNoteVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ReviewNote reviewNote=reviewNoteService.getById(id);
        ReviewNoteVo reviewNoteVo=new BeanUtils<ReviewNoteVo>().copyObj(reviewNote,ReviewNoteVo.class);
        return new Result<ReviewNoteVo>().sucess(reviewNoteVo);
    }

    @Override
    @ApiOperation(value = "列表查询ReviewNote", notes = "根据条件查询ReviewNote列表数据")
    public Result<List<ReviewNoteVo>> list(@Valid @RequestBody @ApiParam(name="reviewNoteQueryForm",value="ReviewNote查询参数",required=true) ReviewNoteQueryForm reviewNoteQueryForm) {
        log.info("search with reviewNoteQueryForm:", reviewNoteQueryForm.toString());
        List<ReviewNote> reviewNoteList=reviewNoteService.selectList(reviewNoteQueryForm);
        List<ReviewNoteVo> reviewNoteVoList=new BeanUtils<ReviewNoteVo>().copyObjs(reviewNoteList,ReviewNoteVo.class);
        return new Result<List<ReviewNoteVo>>().sucess(reviewNoteVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ReviewNote", notes = "根据条件查询ReviewNote分页数据")
    public Result<IPage<ReviewNoteVo>> search(@Valid @RequestBody @ApiParam(name="reviewNoteQueryForm",value="ReviewNote查询参数",required=true) ReviewNoteQueryForm reviewNoteQueryForm) {
        log.info("search with reviewNoteQueryForm:", reviewNoteQueryForm.toString());
        IPage<ReviewNote> reviewNotePage=reviewNoteService.selectPage(reviewNoteQueryForm);
        IPage<ReviewNoteVo> reviewNoteVoPage=new BeanUtils<ReviewNoteVo>().copyPageObjs(reviewNotePage,ReviewNoteVo.class);
        return new Result<IPage<ReviewNoteVo>>().sucess(reviewNoteVoPage);
    }

}



