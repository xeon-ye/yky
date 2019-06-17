package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpAuditRecordQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpAuditRecordForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpAuditRecordVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-26
 * @Description :  ZpcpAuditRecord控制器接口
 * @Modified :
 */
public interface ZpcpAuditRecordClient {

    public static final String path="/hr/zpcp-audit-record";


    /**
     *  POST---新增
     * @param zpcpAuditRecordForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpAuditRecordForm zpcpAuditRecordForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpAuditRecordForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpAuditRecordForm zpcpAuditRecordForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpAuditRecordVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpAuditRecordQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpAuditRecordVo>> list(@Valid @RequestBody ZpcpAuditRecordQueryForm zpcpAuditRecordQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpAuditRecordQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpAuditRecordVo>> search(@Valid @RequestBody ZpcpAuditRecordQueryForm zpcpAuditRecordQueryForm);

    @PostMapping(value = path+"/checkDeclare")
    Result checkDeclare(@Valid @RequestBody @ApiParam(name = "zpcpAuditRecordForm", value = "审核申报详情", required = true) ZpcpAuditRecordForm zpcpAuditRecordForm);
}
