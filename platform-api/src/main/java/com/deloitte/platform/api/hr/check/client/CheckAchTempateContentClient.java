package com.deloitte.platform.api.hr.check.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckAchTempateContentQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchTempateContentForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchTempateContentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckAchTempateContent控制器接口
 * @Modified :
 */
public interface CheckAchTempateContentClient {

    public static final String path="/hr/check-ach-tempate-content";


    /**
     *  POST---新增
     * @param checkAchTempateContentForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckAchTempateContentForm checkAchTempateContentForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkAchTempateContentForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckAchTempateContentForm checkAchTempateContentForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckAchTempateContentVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkAchTempateContentQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckAchTempateContentVo>> list(@Valid @RequestBody CheckAchTempateContentQueryForm checkAchTempateContentQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkAchTempateContentQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckAchTempateContentVo>> search(@Valid @RequestBody CheckAchTempateContentQueryForm checkAchTempateContentQueryForm);
}
