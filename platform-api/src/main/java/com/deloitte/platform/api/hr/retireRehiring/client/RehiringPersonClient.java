package com.deloitte.platform.api.hr.retireRehiring.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.retireRehiring.param.RehiringPersonQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RehiringPersonForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RehiringPersonVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :  RetireRehiringPerson控制器接口
 * @Modified :
 */
public interface RehiringPersonClient {

    public static final String path="/hr/retire-rehiring-person";


    /**
     *  POST---新增
     * @param retireRehiringPersonForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute RehiringPersonForm retireRehiringPersonForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, retireRehiringPersonForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody RehiringPersonForm retireRehiringPersonForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<RehiringPersonVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   retireRehiringPersonQueryForm
     * @return
     */
    @PostMapping(value = path+"/list")
    Result<List<RehiringPersonVo>> list(@Valid @RequestBody RehiringPersonQueryForm retireRehiringPersonQueryForm);


    /**
     *  POST----复杂查询
     * @param  retireRehiringPersonQueryForm
     * @return
     */
    @PostMapping(value = path+"/page")
    Result<IPage<RehiringPersonVo>> search(@Valid @RequestBody RehiringPersonQueryForm retireRehiringPersonQueryForm);
}
