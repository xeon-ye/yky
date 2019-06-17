package com.deloitte.platform.api.hr.recruitment.client;

import com.deloitte.platform.api.hr.recruitment.param.ZpcpFirstLevelSubjectQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpFirstLevelSubjectForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpFirstLevelSubjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description :  ZpcpFirstLevelSubject控制器接口
 * @Modified :
 */
public interface ZpcpFirstLevelSubjectClient {

    public static final String path="/hr/zpcp-first-level-subject";


    /**
     *  POST---新增
     * @param zpcpFirstLevelSubjectForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpFirstLevelSubjectForm zpcpFirstLevelSubjectForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpFirstLevelSubjectForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpFirstLevelSubjectForm zpcpFirstLevelSubjectForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpFirstLevelSubjectVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpFirstLevelSubjectVo>> list();


    /**
     *  POST----复杂查询
     * @param  zpcpFirstLevelSubjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpFirstLevelSubjectVo>> search(@Valid @RequestBody ZpcpFirstLevelSubjectQueryForm zpcpFirstLevelSubjectQueryForm);
}
