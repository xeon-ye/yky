package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.EnclosureQueryForm;
import com.deloitte.platform.api.project.vo.EnclosureForm;
import com.deloitte.platform.api.project.vo.EnclosureVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Enclosure控制器接口
 * @Modified :
 */
public interface EnclosureClient {

    public static final String path="/project/enclosure";


    /**
     *  POST---新增
     * @param enclosureForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EnclosureForm enclosureForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, enclosureForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody EnclosureForm enclosureForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EnclosureVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   enclosureQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EnclosureVo>> list(@Valid @RequestBody EnclosureQueryForm enclosureQueryForm);


    /**
     *  POST----复杂查询
     * @param  enclosureQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EnclosureVo>> search(@Valid @RequestBody EnclosureQueryForm enclosureQueryForm);

}
