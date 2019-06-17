package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptForm;
import com.deloitte.platform.api.isump.vo.UserForm;
import com.deloitte.platform.api.project.client.ReplyClient;
import com.deloitte.platform.api.project.param.ReplyQueryForm;
import com.deloitte.platform.api.project.vo.ProjectReplyVo;
import com.deloitte.platform.api.project.vo.ProjectsVo;
import com.deloitte.platform.api.project.vo.ReplyForm;
import com.deloitte.platform.api.project.vo.ReplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.entity.Projects;
import com.deloitte.services.project.entity.Reply;
import com.deloitte.services.project.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   Reply控制器实现类
 * @Modified :
 */
@Api(tags = "项目评审5-1")
@Slf4j
@RestController
public class ReplyController implements ReplyClient {

    @Autowired
    public IReplyService replyService;

    @Autowired
    public IApplicationService applicationService;

    @Autowired
    public IProjectsService projectsService;

    @Autowired
    public IAllActService allActService;

    @Autowired
    public IActService actService;

    @Autowired
    public ISubactService subactService;

    @Autowired
    public IBudgetService budgetService;

    @Autowired
    public IPerformanceService performanceService;

    @Autowired
    public IExpenseService expenseService;

    @Autowired
    private IEconomicService economicService;

    @Autowired
    private IPersonService personService;

    @Override
    @ApiOperation(value = "新增Reply", notes = "新增一个Reply")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name = "replyForm", value = "新增Reply的form表单", required = true) ReplyForm replyForm) {
        log.info("form:", replyForm.toString());
        Reply reply = new BeanUtils<Reply>().copyObj(replyForm, Reply.class);
        return Result.success(replyService.save(reply));
    }


    @Override
    @ApiOperation(value = "删除Reply", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ReplyID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        replyService.removeById(id);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "修改Reply", notes = "修改指定Reply信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Reply的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name = "replyForm", value = "修改Reply的form表单", required = true) ReplyForm replyForm) {
        Reply reply = new BeanUtils<Reply>().copyObj(replyForm, Reply.class);
        reply.setId(id);
        replyService.saveOrUpdate(reply);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "获取Reply", notes = "获取指定ID的Reply信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Reply的ID", required = true, dataType = "long")
    public Result<ReplyVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Reply reply = replyService.getById(id);
        ReplyVo replyVo = new BeanUtils<ReplyVo>().copyObj(reply, ReplyVo.class);
        return new Result<ReplyVo>().sucess(replyVo);
    }

    @Override
    @ApiOperation(value = "列表查询Reply", notes = "根据条件查询Reply列表数据")
    public Result<List<ReplyVo>> list(@Valid @RequestBody @ApiParam(name = "replyQueryForm", value = "Reply查询参数", required = true) ReplyQueryForm replyQueryForm) {
        log.info("search with replyQueryForm:", replyQueryForm.toString());
        List<Reply> replyList = replyService.selectList(replyQueryForm);
        List<ReplyVo> replyVoList = new BeanUtils<ReplyVo>().copyObjs(replyList, ReplyVo.class);
        return new Result<List<ReplyVo>>().sucess(replyVoList);
    }

    @Override
    @ApiOperation(value = "分页查询Reply", notes = "根据条件查询Reply分页数据")
    public Result<IPage<ReplyVo>> search(@Valid @RequestBody @ApiParam(name = "replyQueryForm", value = "Reply查询参数", required = true) ReplyQueryForm replyQueryForm) {
        log.info("search with replyQueryForm:", replyQueryForm.toString());
        IPage<Reply> replyPage = replyService.selectPage(replyQueryForm);
        IPage<ReplyVo> replyVoPage = new BeanUtils<ReplyVo>().copyPageObjs(replyPage, ReplyVo.class);
        return new Result<IPage<ReplyVo>>().sucess(replyVoPage);
    }

    @Override
    @ApiOperation(value = "删除批复书", notes = "删除批复书")
    @ApiImplicitParam(paramType = "path", name = "replyId", value = "项目ID", required = true, dataType = "string")
    public Result toRemove(@PathVariable String replyId) {
        ProjectReplyVo vo = replyService.toRemove(replyId);
        return new Result().sucess(vo);
    }

    @Override
    @ApiOperation(value = "查看修改批复书", notes = "查看修改批复书")
    @ApiImplicitParam(paramType = "path", name = "replyId", value = "项目ID", required = true, dataType = "string")
    public Result<ProjectReplyVo> toFindReply(@PathVariable String replyId) {
        ProjectReplyVo vo = replyService.toFindReply(replyId);
        return new Result<ProjectReplyVo>().sucess(vo);
    }

    @Override
    @ApiOperation(value = "关联查询获取Reply", notes = "关联查询获取Reply")
    @ApiImplicitParam(paramType = "path", name = "projectId", value = "项目ID", required = true, dataType = "string")
    public Result<ProjectReplyVo> getProRep(@PathVariable String projectId) {
        ProjectReplyVo vo = null;
        Projects projects = projectsService.getById(projectId);
        String replyMark = projects.getReplyNewMark();
        //新建批复项目 80001 非空
        if("80001".equals(replyMark)) {
            vo = replyService.getOneRep(projectId);
        }else {
            vo = replyService.getOneApp(projectId);
        }
        return new Result<ProjectReplyVo>().sucess(vo);
    }

    @Override
    @ApiOperation(value = "保存", notes = "保存")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<ProjectReplyVo> toSave(@Valid @RequestBody @ApiParam(name="ProjectReplyVo",value="传入后端的值",required=true) ProjectReplyVo projectReplyVo) {
        projectReplyVo.setReplyCode("7003");
        //新建
        String newMark = projectReplyVo.getNewMark();
        //前导查询
        String replyId = projectReplyVo.getReplyId();
        //关联查询
        String projectId = projectReplyVo.getProjectId();

        if(StringUtils.isNotEmpty(newMark)) {
            projectReplyVo = replyService.newReply(projectReplyVo);
        }else if(StringUtils.isNotEmpty(replyId)) {
            projectReplyVo = replyService.repToUpdate(projectReplyVo);
        }else if(StringUtils.isNotEmpty(projectId)) {
            projectReplyVo = replyService.proToUpdate(projectReplyVo);
        }
        return new Result<ProjectReplyVo>().sucess(projectReplyVo);
    }

    @Override
    @ApiOperation(value = "提交", notes = "提交")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<ProjectReplyVo> toSubmit(@Valid @RequestBody @ApiParam(name="ProjectReplyVo",value="传入后端的值",required=true) ProjectReplyVo projectReplyVo) {
        projectReplyVo.setReplyCode("7004");
        //新建
        String newMark = projectReplyVo.getNewMark();
        //前导查询
        String replyId = projectReplyVo.getReplyId();
        //关联查询
        String projectId = projectReplyVo.getProjectId();
        if(StringUtils.isNotEmpty(newMark)) {
            projectReplyVo = replyService.newReply(projectReplyVo);
        }else if(StringUtils.isNotEmpty(replyId)) {
            projectReplyVo = replyService.repToUpdate(projectReplyVo);
        }else if(StringUtils.isNotEmpty(projectId)) {
            projectReplyVo = replyService.proToUpdate(projectReplyVo);
        }
        return new Result<ProjectReplyVo>().sucess(projectReplyVo);
    }

    @Override
    @ApiOperation(value = "导出PDF附件信息",notes = "导出PDF附件信息")
    @ApiImplicitParam(paramType = "path",name = "urlPath",value = "urlPath",required = true,dataType = "string")
    public Result exportPDF(String urlPath, HttpServletRequest request, HttpServletResponse response, UserForm userForm, DeptForm deptForm) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            //urlPath = ""; 地址地址
            String downName = "XX项目申报申请书" + DateFormatUtils.format(new Date(), "yyyyMMdd") + ".pdf";
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(downName, "UTF-8"));
            is = new FileInputStream(urlPath);
            bis = new BufferedInputStream(is);
            os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error("文件读取异常！", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage(), e.getCause());
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(is);
        }
        return Result.success();
    }

    @Override
    @ApiOperation(value = "导出word附件信息",notes = "导出word附件信息")
    @ApiImplicitParam(paramType = "path",name = "applicationId",value = "applicationId",required = true, dataType = "long")
    public Result export(Long applicationId, HttpServletRequest request, HttpServletResponse response, UserForm userForm, DeptForm deptForm) {
        String fileUrl = "";
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;

        try {
            String downName = "文档_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(downName, "UTF-8"));
            is = new FileInputStream(fileUrl);
            bis = new BufferedInputStream(is);
            os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }

        } catch (Exception e) {
            log.error("文件读取异常！", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage(), e.getCause());
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(is);
        }
        return Result.success();
    }
}



