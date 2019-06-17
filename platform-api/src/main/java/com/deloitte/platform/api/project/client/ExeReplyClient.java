package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.ExeReplyQueryForm;
import com.deloitte.platform.api.project.vo.ExeReplyForm;
import com.deloitte.platform.api.project.vo.ExeReplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description :  ExeReply控制器接口
 * @Modified :
 */
public interface ExeReplyClient {

    public static final String path="/project/exe-reply";


    /**
     *  POST---新增
     * @param exeReplyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ExeReplyForm exeReplyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, exeReplyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ExeReplyForm exeReplyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ExeReplyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   exeReplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ExeReplyVo>> list(@Valid @RequestBody ExeReplyQueryForm exeReplyQueryForm);


    /**
     *  POST----复杂查询
     * @param  exeReplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ExeReplyVo>> search(@Valid @RequestBody ExeReplyQueryForm exeReplyQueryForm);
}
