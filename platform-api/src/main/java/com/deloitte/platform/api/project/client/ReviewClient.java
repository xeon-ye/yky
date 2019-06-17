package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.isump.vo.DeptForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserForm;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.project.param.ReviewQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Review控制器接口
 * @Modified :
 */
public interface ReviewClient {

    public static final String path="/project/review";


    /**
     *  POST---新增
     * @param reviewForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ReviewForm reviewForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, reviewForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ReviewForm reviewForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ReviewVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   reviewQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ReviewVo>> list(@Valid @RequestBody ReviewQueryForm reviewQueryForm);


    /**
     *  POST----复杂查询
     * @param  reviewQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ReviewVo>> search(@Valid @RequestBody ReviewQueryForm reviewQueryForm);


    /**
     * 查询
     * @param applicationId
     * @return
     */
    @GetMapping(value = path + "/getOneApp/{applicationId}")
    Result<ProjectReviewVo> getOneApp(@PathVariable(value = "applicationId") String applicationId);

    /**
     * 提交
     * @param projectReviewVo
     * @return
     */
    @PostMapping(value = path+"/toSubmit")
    Result<ProjectReviewVo> toSubmit(@Valid @RequestBody ProjectReviewVo projectReviewVo, SendProcessTaskForm sendProcessTaskForm);

    /**
     * 调整
     * @param projectReviewVo
     * @return
     */
    @PostMapping(value = path+"/toAdjust")
    Result toAdjust(@Valid @RequestBody ProjectReviewVo projectReviewVo);

    /**
     * 拒绝
     * @param projectReviewVo
     * @return
     */
    @PostMapping(value = path+"/toRefused")
    Result toRefused(@Valid @RequestBody ProjectReviewVo projectReviewVo);

    /**
     * 拒绝
     * @param projectReviewVo
     * @return
     */
    @PostMapping(value = path+"/toSave")
    Result toSave(@Valid @RequestBody ProjectReviewVo projectReviewVo);
}
