package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpNoticeStationsQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpStationVo;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpNoticeStationsForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpNoticeStationsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-18
 * @Description :  ZpcpNoticeStations控制器接口
 * @Modified :
 */
public interface ZpcpNoticeStationsClient {

    public static final String path="/hr/zpcp-notice-stations";


    /**
     *  POST---新增
     * @param zpcpNoticeStationsForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpNoticeStationsForm zpcpNoticeStationsForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpNoticeStationsForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpNoticeStationsForm zpcpNoticeStationsForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpNoticeStationsVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpNoticeStationsQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrZpcpStationVo>> list(@Valid @RequestBody ZpcpNoticeStationsQueryForm zpcpNoticeStationsQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpNoticeStationsQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpNoticeStationsVo>> search(@Valid @RequestBody ZpcpNoticeStationsQueryForm zpcpNoticeStationsQueryForm);
}
