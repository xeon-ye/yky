package com.deloitte.platform.api.hr.retireRehiring.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.retireRehiring.param.RetirePersonQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RetirePersonForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RetirePersonVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :  RetirePerson控制器接口
 * @Modified :
 */
public interface RetirePersonClient {

    public static final String path="/hr/retire-person";


    /**
     *  POST---新增
     * @param retirePersonForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute RetirePersonForm retirePersonForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, retirePersonForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody RetirePersonForm retirePersonForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<RetirePersonVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   retirePersonQueryForm
     * @return
     */
    @PostMapping(value = path+"/list")
    Result<List<RetirePersonVo>> list(@Valid @RequestBody RetirePersonQueryForm retirePersonQueryForm);


    /**
     *  POST----复杂查询
     * @param  retirePersonQueryForm
     * @return
     */
    @PostMapping(value = path+"/page")
    Result<IPage<RetirePersonVo>> search(@Valid @RequestBody RetirePersonQueryForm retirePersonQueryForm);
}
