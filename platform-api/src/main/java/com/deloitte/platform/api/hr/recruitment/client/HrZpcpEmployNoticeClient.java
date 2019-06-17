package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpEmployNoticeQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.BatchObj;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpEmployNoticeForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpEmployNoticeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :  HrZpcpEmployNotice控制器接口
 * @Modified :
 */
public interface HrZpcpEmployNoticeClient {

    public static final String path="/hr/recruitment/hr-zpcp-employ-notice";


    /**
     *  POST---新增
     * @param hrZpcpEmployNoticeForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrZpcpEmployNoticeForm hrZpcpEmployNoticeForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrZpcpEmployNoticeForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrZpcpEmployNoticeForm hrZpcpEmployNoticeForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrZpcpEmployNoticeVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrZpcpEmployNoticeQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrZpcpEmployNoticeVo>> list(@Valid @RequestBody HrZpcpEmployNoticeQueryForm hrZpcpEmployNoticeQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrZpcpEmployNoticeQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrZpcpEmployNoticeVo>> search(@Valid @RequestBody HrZpcpEmployNoticeQueryForm hrZpcpEmployNoticeQueryForm);

    /**
     * 批量发布
     * @param batchObj
     * @return
     */
    @PostMapping(value = path+"/releaseList")
    public Result releaseList(@Valid @RequestBody BatchObj batchObj);

    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);
}
