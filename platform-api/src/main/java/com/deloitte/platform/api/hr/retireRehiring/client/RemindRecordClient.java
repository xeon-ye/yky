package com.deloitte.platform.api.hr.retireRehiring.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.retireRehiring.param.RemindRecordQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindRecordForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindRecordVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-15
 * @Description :  RetireRemindRecord控制器接口
 * @Modified :
 */
public interface RemindRecordClient {

    public static final String path="/hr/retire-remind-record";


    /**
     *  POST---新增
     * @param retireRemindRecordForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute RemindRecordForm retireRemindRecordForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, retireRemindRecordForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody RemindRecordForm retireRemindRecordForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<RemindRecordVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   retireRemindRecordQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<RemindRecordVo>> list(@Valid @RequestBody RemindRecordQueryForm retireRemindRecordQueryForm);


    /**
     *  POST----复杂查询
     * @param  retireRemindRecordQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<RemindRecordVo>> search(@Valid @RequestBody RemindRecordQueryForm retireRemindRecordQueryForm);
}
