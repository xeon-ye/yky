package com.deloitte.platform.api.hr.gcc.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccResultQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccResultForm;
import com.deloitte.platform.api.hr.gcc.vo.GccResultVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


/**
 * @Author : LJH
 * @Date : Create in 2019-05-13
 * @Description :  GccResult控制器接口
 * @Modified :
 */
public interface GccResultClient {

    public static final String path="/hr/gcc-result";


    /**
     *  POST---新增
     * @param gccResultForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccResultForm gccResultForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccResultForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccResultForm gccResultForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccResultVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccResultQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccResultVo>> list(@Valid @RequestBody GccResultQueryForm gccResultQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccResultQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccResultVo>> search(@Valid @RequestBody GccResultQueryForm gccResultQueryForm);

    /**
     *  POST----导入模版
     * @param  file,type
     * @return
     */
    @PostMapping(value = path+"/import")
    Result importExcel(@Valid @ModelAttribute MultipartFile file) throws IOException;

    /**
     *  POST----导出模版
     * @param  gccResultQueryForm
     * @return
     */
    @GetMapping(value = path+"/export")
    Result exportExcel(@Valid @ModelAttribute GccResultQueryForm gccResultQueryForm, HttpServletRequest request, HttpServletResponse response);



}
