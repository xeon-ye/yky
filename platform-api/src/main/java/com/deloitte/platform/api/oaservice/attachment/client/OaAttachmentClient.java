package com.deloitte.platform.api.oaservice.attachment.client;

import com.deloitte.platform.api.oaservice.attachment.param.OaAttachmentQueryForm;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentForm;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-15
 * @Description :  OaAttachment控制器接口
 * @Modified :
 */
public interface OaAttachmentClient {

    public static final String path="/oaservice/oa-attachment";


    /**
     *  POST---新增
     * @param oaAttachmentForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaAttachmentForm oaAttachmentForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, oaAttachmentForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaAttachmentForm oaAttachmentForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaAttachmentVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaAttachmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaAttachmentVo>> list(@Valid @RequestBody OaAttachmentQueryForm oaAttachmentQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaAttachmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaAttachmentVo>> search(@Valid @RequestBody OaAttachmentQueryForm oaAttachmentQueryForm);
}
