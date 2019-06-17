package com.deloitte.platform.api.hr.recruitment.client;

import com.deloitte.platform.api.hr.recruitment.param.ZpcpSecondLevelSubjectQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpSecondLevelSubjectForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpSecondLevelSubjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description :  ZpcpSecondLevelSubject控制器接口
 * @Modified :
 */
public interface ZpcpSecondLevelSubjectClient {

    public static final String path="/hr/zpcp-second-level-subject";


    /**
     *  POST---新增
     * @param zpcpSecondLevelSubjectForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpSecondLevelSubjectForm zpcpSecondLevelSubjectForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpSecondLevelSubjectForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpSecondLevelSubjectForm zpcpSecondLevelSubjectForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpSecondLevelSubjectVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpSecondLevelSubjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpSecondLevelSubjectVo>> list(@Valid @RequestBody ZpcpSecondLevelSubjectQueryForm zpcpSecondLevelSubjectQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpSecondLevelSubjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpSecondLevelSubjectVo>> search(@Valid @RequestBody ZpcpSecondLevelSubjectQueryForm zpcpSecondLevelSubjectQueryForm);
}
