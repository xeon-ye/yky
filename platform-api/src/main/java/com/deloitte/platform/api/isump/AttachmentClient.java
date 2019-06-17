package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.param.AttachmentQueryForm;
import com.deloitte.platform.api.isump.vo.AttachmentForm;
import com.deloitte.platform.api.isump.vo.AttachmentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-04
 * @Description :  Attachment控制器接口
 * @Modified :
 */
public interface AttachmentClient {

    public static final String path="/isump/attachment";


    /**
     *  POST---新增
     * @param attachmentForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute AttachmentForm attachmentForm);

    /**
     * POST----批量增加
     * @param attachmentForms
     * @return
     */
    @PostMapping(value = path + "/batch")
    Result batchAdd(@Valid @ModelAttribute List<AttachmentForm> attachmentForms);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, attachmentForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody AttachmentForm attachmentForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<AttachmentVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   attachmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<AttachmentVo>> list(@Valid @RequestBody AttachmentQueryForm attachmentQueryForm);


    /**
     *  POST----复杂查询
     * @param  attachmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<AttachmentVo>> search(@Valid @RequestBody AttachmentQueryForm attachmentQueryForm);
}
