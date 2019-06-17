package com.deloitte.platform.api.hr.employ.client;

import com.deloitte.platform.api.hr.employ.param.OutrecruitQueryForm;
import com.deloitte.platform.api.hr.employ.vo.OutrecruitForm;
import com.deloitte.platform.api.hr.employ.vo.OutrecruitVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-10
 * @Description :  Outrecruit控制器接口
 * @Modified :
 */
public interface OutrecruitClient {

    public static final String path="/hr/outrecruit";


    /**
     *  POST---新增
     * @param outrecruitForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OutrecruitForm outrecruitForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, outrecruitForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OutrecruitForm outrecruitForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OutrecruitVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   outrecruitQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OutrecruitVo>> list(@Valid @RequestBody OutrecruitQueryForm outrecruitQueryForm);


    /**
     *  POST----复杂查询
     * @param  outrecruitQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OutrecruitVo>> search(@Valid @RequestBody OutrecruitQueryForm outrecruitQueryForm);
}
