package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckAchUserContentQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserContentAppealForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserContentForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserContentVo;
import com.deloitte.platform.api.hr.check.vo.CommonProcessForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :  CheckAchUserContent控制器接口
 * @Modified :
 */
public interface CheckAchUserContentClient {

    public static final String path="/hr/check-ach-user-content";


    /**
     *  POST---新增
     * @param checkAchUserContentForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckAchUserContentForm checkAchUserContentForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkAchUserContentForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckAchUserContentForm checkAchUserContentForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckAchUserContentVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkAchUserContentQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckAchUserContentVo>> list(@Valid @RequestBody CheckAchUserContentQueryForm checkAchUserContentQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkAchUserContentQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckAchUserContentVo>> search(@Valid @RequestBody CheckAchUserContentQueryForm checkAchUserContentQueryForm);


    /**
     *  Patch----批量删除
     * @param   ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<String> ids);

    /**
     *  Patch----发起审批流程
     * @param   checkAchUserContentAppealForm
     * @return
     */
    @PostMapping(value = path+"/submitApprove")
    Result submitApprove(@Valid @RequestBody  CheckAchUserContentAppealForm checkAchUserContentAppealForm);

    /**
     * 处理审批
     * @param form
     * @return
     */
    @PostMapping(value = path+"/dealApprove")
    Result dealApprove(@Valid @RequestBody CommonProcessForm form);
}
