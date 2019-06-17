package com.deloitte.platform.api.project.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserForm;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.project.param.ReplyQueryForm;
import com.deloitte.platform.api.project.vo.ProjectReplyVo;
import com.deloitte.platform.api.project.vo.ReplyForm;
import com.deloitte.platform.api.project.vo.ReplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Reply控制器接口
 * @Modified :
 */
public interface ReplyClient {

    public static final String path="/project/reply";


    /**
     *  POST---新增
     * @param replyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ReplyForm replyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, replyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ReplyForm replyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ReplyVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   replyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ReplyVo>> list(@Valid @RequestBody ReplyQueryForm replyQueryForm);


    /**
     *  POST----复杂查询
     * @param  replyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ReplyVo>> search(@Valid @RequestBody ReplyQueryForm replyQueryForm);


    /**
     *
     * @param replyId
     * @return
     */
    @GetMapping(value = path+"/toRemove/{replyId}")
    Result toRemove(@PathVariable(value="replyId") String replyId);

    /**
     *
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/toFindReply/{replyId}")
    Result<ProjectReplyVo> toFindReply(@PathVariable(value="replyId") String replyId);


    /**
     *  GET----根据application_id获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/toGetReply/{projectId}")
    Result<ProjectReplyVo> getProRep(@PathVariable(value="projectId") String projectId);

    /**
     * baoc
     * @param projectReplyVo
     * @param userForm
     * @param deptForm
     * @return
     */
    @PostMapping(value = path+"/toSave")
    Result<ProjectReplyVo> toSave(@Valid @RequestBody ProjectReplyVo projectReplyVo);

    /**
     * 提交
     * @param projectReplyVo
     * @param userForm
     * @param deptForm
     * @return
     */
    @PostMapping(value = path+"/toSubmit")
    Result<ProjectReplyVo> toSubmit(@Valid @RequestBody ProjectReplyVo projectReplyVo);

    /**
     *
     * @param id
     * @param request
     * @param response
     * @param userForm
     * @param deptForm
     * @return
     */
    @GetMapping(value = path+"/export/{urlPath}")
    Result exportPDF(@PathVariable(value = "urlPath") String urlPath, HttpServletRequest request, HttpServletResponse response, UserForm userForm, DeptForm deptForm);

    /**
     * 导出word文档
     * @param applicationId
     * @param request
     * @param response
     * @param userForm
     * @param deptForm
     * @return
     */
    @GetMapping(value = path+"/export/{applicationId}")
    Result export(Long applicationId, HttpServletRequest request, HttpServletResponse response, UserForm userForm, DeptForm deptForm);
}
