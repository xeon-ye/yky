package com.deloitte.platform.api.hr.technology.client;

import com.deloitte.platform.api.hr.technology.param.RecruitTechnologyQueryForm;
import com.deloitte.platform.api.hr.technology.vo.RecruitTechnologyForm;
import com.deloitte.platform.api.hr.technology.vo.RecruitTechnologyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-15
 * @Description :  RecruitTechnology控制器接口
 * @Modified :
 */
public interface RecruitTechnologyClient {

    public static final String path="/hr/recruit-technology";


    /**
     *  POST---新增
     * @param recruitTechnologyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute RecruitTechnologyForm recruitTechnologyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, recruitTechnologyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody RecruitTechnologyForm recruitTechnologyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<RecruitTechnologyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   recruitTechnologyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<RecruitTechnologyVo>> list(@Valid @RequestBody RecruitTechnologyQueryForm recruitTechnologyQueryForm);


    /**
     *  POST----复杂查询
     * @param  recruitTechnologyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<RecruitTechnologyVo>> search(@Valid @RequestBody RecruitTechnologyQueryForm recruitTechnologyQueryForm);
}
