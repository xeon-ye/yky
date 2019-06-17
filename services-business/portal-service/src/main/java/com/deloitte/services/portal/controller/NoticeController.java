package com.deloitte.services.portal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.portal.param.NoticeQueryForm;
import com.deloitte.platform.api.portal.vo.ArticleVo;
import com.deloitte.platform.api.portal.vo.NoticeForm;
import com.deloitte.platform.api.portal.vo.NoticeVo;
import com.deloitte.platform.api.portal.client.NoticeClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.portal.entity.Article;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.portal.service.INoticeService;
import com.deloitte.services.portal.entity.Notice;


/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-03
 * @Description :   Notice控制器实现类
 * @Modified :
 */
@Api(tags = "Notice操作接口")
@Slf4j
@RestController
public class NoticeController implements NoticeClient {

    @Autowired
    public INoticeService noticeService;


    @Override
    @ApiOperation(value = "新增Notice", notes = "新增一个Notice")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name = "noticeForm", value = "新增Notice的form表单", required = true) NoticeForm noticeForm) {
        log.info("form:{}", noticeForm.toString());
        Notice notice = new BeanUtils<Notice>().copyObj(noticeForm, Notice.class);
        return Result.success(noticeService.save(notice));
    }


    @Override
    @ApiOperation(value = "删除Notice", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "NoticeID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        noticeService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Notice", notes = "修改指定Notice信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Notice的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name = "noticeForm", value = "修改Notice的form表单", required = true) NoticeForm noticeForm) {
        Notice notice = new BeanUtils<Notice>().copyObj(noticeForm, Notice.class);
        notice.setNoticeId(id);
        noticeService.saveOrUpdate(notice);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Notice", notes = "获取指定ID的Notice信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Notice的ID", required = true, dataType = "long")
    public Result<NoticeVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Notice notice = noticeService.getById(id);
        NoticeVo noticeVo = new BeanUtils<NoticeVo>().copyObj(notice, NoticeVo.class);
        return new Result<NoticeVo>().sucess(noticeVo);
    }

    @Override
    @ApiOperation(value = "列表查询Notice", notes = "根据条件查询Notice列表数据")
    public Result<List<NoticeVo>> list(@Valid @RequestBody @ApiParam(name = "noticeQueryForm", value = "Notice查询参数", required = true) NoticeQueryForm noticeQueryForm) {
        log.info("search with noticeQueryForm:{}", noticeQueryForm.toString());
        List<Notice> noticeList = noticeService.selectList(noticeQueryForm);
        List<NoticeVo> noticeVoList = new BeanUtils<NoticeVo>().copyObjs(noticeList, NoticeVo.class);
        return new Result<List<NoticeVo>>().sucess(noticeVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Notice", notes = "根据条件查询Notice分页数据")
    public Result<IPage<NoticeVo>> search(@Valid @RequestBody @ApiParam(name = "noticeQueryForm", value = "Notice查询参数", required = true) NoticeQueryForm noticeQueryForm) {
        log.info("search with noticeQueryForm:{}", noticeQueryForm.toString());
        IPage<Notice> noticePage = noticeService.selectPage(noticeQueryForm);
        IPage<NoticeVo> noticeVoPage = new BeanUtils<NoticeVo>().copyPageObjs(noticePage, NoticeVo.class);
        return new Result<IPage<NoticeVo>>().sucess(noticeVoPage);
    }

    /**
     * @param num 首页公告栏展示公告条数
     * @return 具体内容
     */
    @Override
    @ApiOperation(value = "首页查询Notice", notes = "根据条件查询Notice列表数据")
    public Result<List<NoticeVo>> getHomeList(@PathVariable int num) {
        log.info("home page news number:{}", num);
        List<Notice> noticeList = noticeService.getHomeList(num);
        List<NoticeVo> noticeVoList = new BeanUtils<NoticeVo>().copyObjs(noticeList, NoticeVo.class);
        return new Result<List<NoticeVo>>().sucess(noticeVoList);
    }

}



