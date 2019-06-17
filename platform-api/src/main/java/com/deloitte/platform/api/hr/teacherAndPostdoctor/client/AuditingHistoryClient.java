package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.AuditingHistoryQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.AuditingHistoryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.AuditingHistoryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  AuditingHistory控制器接口
 * @Modified :
 */
public interface AuditingHistoryClient {

    public static final String path="/hr/auditing-history";


    /**
     *  POST---新增
     * @param auditingHistoryForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute AuditingHistoryForm auditingHistoryForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, auditingHistoryForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody AuditingHistoryForm auditingHistoryForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<AuditingHistoryVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   auditingHistoryQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<AuditingHistoryVo>> list(@Valid @RequestBody AuditingHistoryQueryForm auditingHistoryQueryForm);


    /**
     *  POST----复杂查询
     * @param  auditingHistoryQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<AuditingHistoryVo>> search(@Valid @RequestBody AuditingHistoryQueryForm auditingHistoryQueryForm);
}
