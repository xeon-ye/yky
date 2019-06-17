package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherApplyForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;


/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  Teacher控制器接口
 * @Modified :
 */
public interface TeacherClient {

    public static final String path="/hr/teacher";


    /**
     *  POST---新增
     * @param fileUrl
     * @return
     */
    @PostMapping(value = path+"/add")
    Result addTeacherByFileUrl(@RequestParam String fileUrl);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, teacherForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody TeacherForm teacherForm);


    /**
     *  POST----列表查询
     * @param   teacherQueryForm
     * @param   type
     * @return
     */
    @PostMapping(value = path+"/list/conditionsList")
    Result<IPage<TeacherVo>> list(@Valid @RequestBody TeacherQueryForm teacherQueryForm,@RequestParam Integer type);

    /**
     *  GET----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/{id}")
    Result<TeacherVo> get(@PathVariable(value="id") long id);


    /**
     *
     *  条件导出数据列表
     *
     * @param teacherExportForm  条件
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path+"/list/exportTeacherList")
    void exportTeacherList(@Valid @ModelAttribute TeacherExportForm teacherExportForm, HttpServletRequest request, HttpServletResponse response,@RequestParam Integer type);


    /**
     *  根据条件获取教师资格审核Teacher信息列表
     *
     * @param teacherApplyForm
     * @return
     */
    @PostMapping(value = path+"/list/teacherApplyList")
    Result<IPage<TeacherApplyVo>> teacherApplyList(@Valid @RequestBody TeacherApplyForm teacherApplyForm);

    /**
     *  POST---上传教师资格证书
     * @param teacherApplyInfoForm  证书信息
     * @param isSelfHelp  是否自助
     * @return
     */
    @PostMapping(value = path+"/uploadCert")
    Result uploadCert(@Valid @RequestBody TeacherApplyInfoForm teacherApplyInfoForm,@RequestParam Integer isSelfHelp);



    /**
     *   获取教师资格证书信息
     *
     * @param tid  教师资格证书申请信息ID
     * @return
     */
    @GetMapping(value = path+"/getUploadCert")
    Result getUploadCert(@RequestParam long tid);

    /**
     *  POST---二院审核教师资格证书信息
     * @param tid  教师资格证书申请ID
     * @param isAdopt  是否通过
     * @return
     */
    @PostMapping(value = path+"/checkApplyInfo")
    Result checkApplyInfo(long tid,@RequestParam(defaultValue = "1",required = true) Integer isAdopt);

    /**
     *   POST---汇总提交-审核教师资格信息
     *
     * @param tidList  教师资格申请信息ID数组
     * @param subType  汇总提交类型
     * @return
     */
    @PostMapping(value = path+"/checkAndSubApply")
    Result checkAndSubApply(String tidList, @RequestParam Integer subType);

    /**
     * POST---批量导入证书信息
     *
     * @param file
     * @return
     */
    @PostMapping(value = path + "/importFile")
    Result importFile(MultipartFile file);


    /**
     *
     *  导出师资证书信息列表
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path+"/list/exportTeacherCert")
    void exportTeacherCert(HttpServletRequest request, HttpServletResponse response);


    /**
     *  条件搜索下拉框选项初始化
     *
     * @return
     */
    @GetMapping(value = path+"/getTeacherInit")
    Result<HashMap<String,Object>> getTeacherInit();

    /**
     * 批量删除Teacher信息
     *
     * @param  ids 教师ID[1111,22222]
     * @return
     */
    @PostMapping(value = path+"/delTeacherByIds")
    Result delTeacherByIds(@RequestParam String[] ids);
}
