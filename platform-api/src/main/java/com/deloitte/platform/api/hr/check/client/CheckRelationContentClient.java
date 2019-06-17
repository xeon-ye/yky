package com.deloitte.platform.api.hr.check.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckRelationContentQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckRelationContentForm;
import com.deloitte.platform.api.hr.check.vo.CheckRelationContentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-08
 * @Description :  CheckRelationContentForm控制器接口
 * @Modified :
 */
public interface CheckRelationContentClient {

    public static final String path="/hr/check-relation-content";


    /**
     *  POST---新增
     * @param CheckRelationContentForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckRelationContentForm CheckRelationContentForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, CheckRelationContentForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckRelationContentForm CheckRelationContentForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckRelationContentVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkRelationContentQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckRelationContentVo>> list(@Valid @RequestBody CheckRelationContentQueryForm checkRelationContentQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkRelationContentQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckRelationContentVo>> search(@Valid @RequestBody CheckRelationContentQueryForm checkRelationContentQueryForm);

    /**
     *  POST----根据考核关系id删除具体内容
     * @param  ids
     * @return
     */
    @PostMapping(value = path+"/page/batchDeleteByRelationId")
    Result batchDeleteByRelationId(@Valid @RequestBody List<String> ids);

}
