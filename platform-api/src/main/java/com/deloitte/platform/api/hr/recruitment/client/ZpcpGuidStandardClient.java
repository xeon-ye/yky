package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpGuidStandardQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpGuidStandardForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpGuidStandardVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :  ZpcpGuidStandard控制器接口
 * @Modified :
 */
public interface ZpcpGuidStandardClient {

    public static final String path="/hr/zpcp-guid-standard";


    /**
     *  POST---新增
     * @param zpcpGuidStandardForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpGuidStandardForm zpcpGuidStandardForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpGuidStandardForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpGuidStandardForm zpcpGuidStandardForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpGuidStandardVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpGuidStandardQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpGuidStandardVo>> list(@Valid @RequestBody ZpcpGuidStandardQueryForm zpcpGuidStandardQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpGuidStandardQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpGuidStandardVo>> search(@Valid @RequestBody ZpcpGuidStandardQueryForm zpcpGuidStandardQueryForm);

    /**
     *  POST---批量新增或更新
     * @param
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<ZpcpGuidStandardForm> guidStandardForms);


    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);
}
