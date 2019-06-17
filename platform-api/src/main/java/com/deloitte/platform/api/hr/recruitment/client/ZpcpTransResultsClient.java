package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpTransResultsQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpTransResultsDTO;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpTransResultsForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpTransResultsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :  ZpcpTransResults控制器接口
 * @Modified :
 */
public interface ZpcpTransResultsClient {

    public static final String path="/hr/zpcp-trans-results";


    /**
     *  POST---新增
     * @param zpcpTransResultsForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpTransResultsForm zpcpTransResultsForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpTransResultsForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpTransResultsForm zpcpTransResultsForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpTransResultsVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpTransResultsQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<ZpcpTransResultsDTO> list(@Valid @RequestBody ZpcpTransResultsQueryForm zpcpTransResultsQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpTransResultsQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpTransResultsVo>> search(@Valid @RequestBody ZpcpTransResultsQueryForm zpcpTransResultsQueryForm);



    /**
     *  POST---批量新增或更新
     * @param
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody ZpcpTransResultsDTO zpcpTransResultsDTO);


    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);
}
