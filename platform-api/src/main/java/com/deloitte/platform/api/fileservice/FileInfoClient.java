package com.deloitte.platform.api.fileservice;

import com.deloitte.platform.api.fileservice.param.FileInfoQueryForm;
import com.deloitte.platform.api.fileservice.vo.FileInfoForm;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-19
 * @Description :  FileInfo控制器接口
 * @Modified :
 */
public interface FileInfoClient {

    public static final String path="/fileservice/file-info";


    /**
     *  POST---新增
     * @param fileInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute FileInfoForm fileInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, fileInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody FileInfoForm fileInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<FileInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   fileInfoForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<FileInfoVo>> list(@Valid @RequestBody FileInfoQueryForm fileInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  fileInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<FileInfoVo>> search(@Valid @RequestBody FileInfoQueryForm fileInfoQueryForm);
}
