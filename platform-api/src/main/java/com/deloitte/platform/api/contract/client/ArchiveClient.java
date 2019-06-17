package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.ArchiveQueryForm;
import com.deloitte.platform.api.contract.vo.ArchiveForm;
import com.deloitte.platform.api.contract.vo.ArchiveVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : yangyq
 * @Date : Create in 2019-06-10
 * @Description :  Archive控制器接口
 * @Modified :
 */
public interface ArchiveClient {

    public static final String path="/contract/archive";


    /**
     *  POST---新增
     * @param archiveForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ArchiveForm archiveForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, archiveForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ArchiveForm archiveForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ArchiveVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   archiveQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ArchiveVo>> list(@Valid @RequestBody ArchiveQueryForm archiveQueryForm);


    /**
     *  POST----复杂查询
     * @param  archiveQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ArchiveVo>> search(@Valid @RequestBody ArchiveQueryForm archiveQueryForm);
}
