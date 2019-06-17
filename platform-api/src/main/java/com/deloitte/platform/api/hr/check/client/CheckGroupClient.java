package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckGroupQueryForm;
import com.deloitte.platform.api.hr.check.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckGroup控制器接口
 * @Modified :
 */
public interface CheckGroupClient {

    public static final String path="/hr/check-group";


    /**
     *  POST---新增
     * @param checkGroupForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckGroupForm checkGroupForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkGroupForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckGroupForm checkGroupForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckGroupVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkGroupQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckGroupVo>> list(@Valid @RequestBody CheckGroupQueryForm checkGroupQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkGroupQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckGroupVo>> search(@Valid @RequestBody CheckGroupQueryForm checkGroupQueryForm);

    /**
     *  POST----查询分组详细信息
     * @param  checkGroupQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/getCheckGroupInfo")
    Result<IPage<CheckGroupInfoVo>> getCheckGroupInfo(@Valid @RequestBody CheckGroupQueryForm checkGroupQueryForm);

    /**
     *  Delete---删除
     * @param  ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<Long> ids);

    /**
     *  POST----列表查询分组和人员
     * @param   checkGroupQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/getCheckGroupAndUser")
    Result<List<CheckGroupAndUserVo>> getCheckGroupAndUser(@Valid @RequestBody CheckGroupQueryForm checkGroupQueryForm);

    /**
     *导出考核组列表
     * @param checkGroupExportForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportCheckGroupList")
    void exportCheckGroupList(@Valid @ModelAttribute CheckGroupExportForm checkGroupExportForm, HttpServletRequest request, HttpServletResponse response);

    /**
     * 输出名单
     * @param checkGroupQueryForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportRoster")
   void exportRoster(@ModelAttribute CheckGroupQueryForm checkGroupQueryForm, HttpServletRequest request, HttpServletResponse response);

    /**
     * 查看分组人数数量
     * @param checkGroupUserCountForm
     * @return
     */
    @PostMapping(value = path+"/list/getCheckGroupUserNumber")
    Result<List<CheckGroupUserCountVo>> getCheckGroupUserNumber(@Valid @ModelAttribute CheckGroupUserCountForm checkGroupUserCountForm);

    /**
     * 导入
     * @param file
     * @param checkGroupForm
     * @return
     */
    @PostMapping(value = path+"/importCheckGroupAndUser")
    Result importCheckGroupAndUser(@RequestPart(value = "file") MultipartFile file, @RequestBody  CheckGroupForm checkGroupForm);

    /**
     * 导出模板
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportCheckGroupTempate")
    void exportCheckGroupTempate(HttpServletRequest request, HttpServletResponse response);
}
