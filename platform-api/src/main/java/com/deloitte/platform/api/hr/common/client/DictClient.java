package com.deloitte.platform.api.hr.common.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.common.param.DictQueryForm;
import com.deloitte.platform.api.hr.common.vo.DictForm;
import com.deloitte.platform.api.hr.common.vo.DictVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  Dict控制器接口
 * @Modified :
 */
public interface DictClient {

    public static final String path="/hr/dict";


    /**
     *  POST---新增
     * @param dictForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute DictForm dictForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, dictForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody DictForm dictForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<DictVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   dictQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<DictVo>> list(@Valid @RequestBody DictQueryForm dictQueryForm);

    /**
     * 查询字典表
     * @param
     * @return
     */
    @PostMapping(value = path+"/list/getCodes")
    Result<List<DictVo>> getList(@Valid @RequestBody DictQueryForm dictQueryForm);

    /**
     *  POST----复杂查询
     * @param  dictQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<DictVo>> search(@Valid @RequestBody DictQueryForm dictQueryForm);

    /**
     *  POST----通过ParentCode 获取列表一级列表
     * @param  parentCode
     * @return
     */
    @GetMapping(value = path+"/getByParentCode/{parentCode}")
    Result<List<DictVo>> getByParentCode(@PathVariable(value = "parentCode") String  parentCode);


    /**
     *  POST----通过id 获取列表下一级列表
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/getListById/{id}")
    Result<List<DictVo>> getListById(@PathVariable(value = "id") String  id);

    /**
     *  POST----通过ParentCode 获取列表二级列表
     * @param  parentCode
     * @return
     */
    @GetMapping(value = path+"/getChildByParentCode/{parentCode}")
    Result<List<DictVo>> getChildByParentCode(@PathVariable(value = "parentCode") String  parentCode);
}
