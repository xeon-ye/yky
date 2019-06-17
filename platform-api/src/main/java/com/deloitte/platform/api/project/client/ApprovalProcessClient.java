package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.ApprovalProcessQueryForm;
import com.deloitte.platform.api.project.vo.ApprovalProcessForm;
import com.deloitte.platform.api.project.vo.ApprovalProcessVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-22
 * @Description :  ApprovalProcess控制器接口
 * @Modified :
 */
public interface ApprovalProcessClient {

    public static final String path="/project/approval-process";


    /**
     *  POST---新增
     * @param approvalProcessForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ApprovalProcessForm approvalProcessForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, approvalProcessForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ApprovalProcessForm approvalProcessForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ApprovalProcessVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   approvalProcessQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ApprovalProcessVo>> list(@Valid @RequestBody ApprovalProcessQueryForm approvalProcessQueryForm);


    /**
     *  POST----复杂查询
     * @param  approvalProcessQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ApprovalProcessVo>> search(@Valid @RequestBody ApprovalProcessQueryForm approvalProcessQueryForm);
}
