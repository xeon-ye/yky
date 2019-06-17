package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpDrugDeviceQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpDrugDeviceForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpDrugDeviceVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :  ZpcpDrugDevice控制器接口
 * @Modified :
 */
public interface ZpcpDrugDeviceClient {

    public static final String path="/hr/zpcp-drug-device";


    /**
     *  POST---新增
     * @param zpcpDrugDeviceForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpDrugDeviceForm zpcpDrugDeviceForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpDrugDeviceForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpDrugDeviceForm zpcpDrugDeviceForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpDrugDeviceVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpDrugDeviceQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpDrugDeviceVo>> list(@Valid @RequestBody ZpcpDrugDeviceQueryForm zpcpDrugDeviceQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpDrugDeviceQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpDrugDeviceVo>> search(@Valid @RequestBody ZpcpDrugDeviceQueryForm zpcpDrugDeviceQueryForm);


    /**
     *  POST---批量新增或更新
     * @param drugDeviceForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<ZpcpDrugDeviceForm> drugDeviceForms);

    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);
}
