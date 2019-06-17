package com.deloitte.platform.api.hr.common.client;

import com.deloitte.platform.api.hr.common.param.HrAttachmentQueryForm;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
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

    public static final String path="/hr/attachment";


    /**
     *  POST---新增
     * @param hrAttachmentForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrAttachmentForm hrAttachmentForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrAttachmentForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrAttachmentForm hrAttachmentForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrAttachmentVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrAttachmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrAttachmentVo>> list(@Valid @RequestBody HrAttachmentQueryForm hrAttachmentQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrAttachmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrAttachmentVo>> search(@Valid @RequestBody HrAttachmentQueryForm hrAttachmentQueryForm);
}
