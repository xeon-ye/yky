package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.MaintReplyNoteQueryForm;
import com.deloitte.platform.api.project.vo.MaintReplyNoteForm;
import com.deloitte.platform.api.project.vo.MaintReplyNoteVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  MaintReplyNote控制器接口
 * @Modified :
 */
public interface MaintReplyNoteClient {

    public static final String path="/project/maint-reply-note";


    /**
     *  POST---新增
     * @param maintReplyNoteForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute MaintReplyNoteForm maintReplyNoteForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, maintReplyNoteForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody MaintReplyNoteForm maintReplyNoteForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<MaintReplyNoteVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   maintReplyNoteQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<MaintReplyNoteVo>> list(@Valid @RequestBody MaintReplyNoteQueryForm maintReplyNoteQueryForm);


    /**
     *  POST----复杂查询
     * @param  maintReplyNoteQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<MaintReplyNoteVo>> search(@Valid @RequestBody MaintReplyNoteQueryForm maintReplyNoteQueryForm);
}
