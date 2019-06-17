package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpPaperQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpPaperForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpPaperVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :  ZpcpPaper控制器接口
 * @Modified :
 */
public interface ZpcpPaperClient {

    public static final String path="/hr/zpcp-paper";


    /**
     *  POST---新增
     * @param zpcpPaperForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpPaperForm zpcpPaperForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpPaperForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpPaperForm zpcpPaperForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpPaperVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpPaperQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpPaperVo>> list(@Valid @RequestBody ZpcpPaperQueryForm zpcpPaperQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpPaperQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpPaperVo>> search(@Valid @RequestBody ZpcpPaperQueryForm zpcpPaperQueryForm);


    /**
     *  POST---批量新增或更新
     * @param paperForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<ZpcpPaperForm> paperForms);


    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);
}
