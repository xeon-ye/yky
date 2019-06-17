package com.deloitte.platform.api.hr.staffAllotment.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.staffAllotment.param.HrInternalTransferQueryForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrExternalTransferForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrInternalTransferForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrInternalTransferVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-03
 * @Description :  HrInternalTransfer控制器接口
 * @Modified :
 */
public interface HrInternalTransferClient {

    public static final String path="/hr/staffAllotment/hr-internal-transfer";


    /**
     *  POST---新增
     * @param hrInternalTransferForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrInternalTransferForm hrInternalTransferForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrInternalTransferForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrInternalTransferForm hrInternalTransferForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrInternalTransferVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrInternalTransferQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrInternalTransferVo>> list(@Valid @RequestBody HrInternalTransferQueryForm hrInternalTransferQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrInternalTransferQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrInternalTransferVo>> search(@Valid @RequestBody HrInternalTransferQueryForm hrInternalTransferQueryForm);


    /**
     *  POST---保存
     * @param hrInternalTransferForm
     * @return
     */
    @PostMapping(value = path+"/save")
    Result save(@Valid @ModelAttribute HrInternalTransferForm hrInternalTransferForm);

    /**
     *  POST---提交
     * @param hrInternalTransferForm
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute HrInternalTransferForm hrInternalTransferForm);

    /**
     * POST---根据编号查询信息
     * @param hrInternalTransferVo
     * @return
     */
    @PostMapping(value = path+"/selectByCode")
    Result<HrInternalTransferVo> selectByCode(@Valid @RequestBody HrInternalTransferVo hrInternalTransferVo);

    /**
     * POST---根据编号查询信息（全中文）
     * @param hrInternalTransferVo
     * @return
     */
    @PostMapping(value = path+"/getByCode")
    Result<HrInternalTransferVo> getByCode(@Valid @RequestBody  HrInternalTransferVo hrInternalTransferVo);

    /**
     * POST---审批结束对数据进行更新
     * @param hrInternalTransferForm
     * @return
     */
    @PostMapping(value = path+"/updateDate")
    Result updateDate(@Valid @ModelAttribute HrInternalTransferForm hrInternalTransferForm);
}
