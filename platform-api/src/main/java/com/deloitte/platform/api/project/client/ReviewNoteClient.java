package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.ReviewNoteQueryForm;
import com.deloitte.platform.api.project.vo.ReviewNoteForm;
import com.deloitte.platform.api.project.vo.ReviewNoteVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-28
 * @Description :  ReviewNote控制器接口
 * @Modified :
 */
public interface ReviewNoteClient {

    public static final String path="/project/review-note";


    /**
     *  POST---新增
     * @param reviewNoteForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ReviewNoteForm reviewNoteForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, reviewNoteForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ReviewNoteForm reviewNoteForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ReviewNoteVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   reviewNoteQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ReviewNoteVo>> list(@Valid @RequestBody ReviewNoteQueryForm reviewNoteQueryForm);


    /**
     *  POST----复杂查询
     * @param  reviewNoteQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ReviewNoteVo>> search(@Valid @RequestBody ReviewNoteQueryForm reviewNoteQueryForm);
}
