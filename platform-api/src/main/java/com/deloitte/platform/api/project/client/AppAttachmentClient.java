package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.AppAttachmentQueryForm;
import com.deloitte.platform.api.project.vo.AppAttachmentForm;
import com.deloitte.platform.api.project.vo.AppAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  AppAttachment控制器接口
 * @Modified :
 */
public interface AppAttachmentClient {

    public static final String path="/project/app-attachment";


    /**
     *  POST---新增
     * @param appAttachmentForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute AppAttachmentForm appAttachmentForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, appAttachmentForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody AppAttachmentForm appAttachmentForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<AppAttachmentVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   appAttachmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<AppAttachmentVo>> list(@Valid @RequestBody AppAttachmentQueryForm appAttachmentQueryForm);


    /**
     *  POST----复杂查询
     * @param  appAttachmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<AppAttachmentVo>> search(@Valid @RequestBody AppAttachmentQueryForm appAttachmentQueryForm);
}
