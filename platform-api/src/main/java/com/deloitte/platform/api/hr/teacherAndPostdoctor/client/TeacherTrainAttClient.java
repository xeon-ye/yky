package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.baomidou.mybatisplus.extension.api.R;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainAttExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainAttQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.TeacherTrainAttBaseVo;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.TeacherTrainAttForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.TeacherTrainAttVo;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.TeacherTrainVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-20
 * @Description :  TeacherTrainAtt控制器接口
 * @Modified :
 */
public interface TeacherTrainAttClient {

    public static final String path="/hr/teacher-train-att";


    /**
     *  POST---新增
     * @param teacherTrainAttForm
     * @return
     */
    @PostMapping(value = path+"/add")
    Result add(@Valid @ModelAttribute TeacherTrainAttForm teacherTrainAttForm);

    /**
     *  导出教师岗前人员信息
     *
     * @param teacherTrainAttExportForm  条件搜索
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportTeacherTrainAttList")
    void exportTeacherTrainAttList(@Valid @ModelAttribute TeacherTrainAttExportForm teacherTrainAttExportForm, HttpServletRequest request, HttpServletResponse response);

    /**
     *  POST----复杂查询
     *
     * @param  teacherTrainAttQueryForm
     * @return
     */
    @PostMapping(value = path+"/getTeacherTrainAttList")
    Result<IPage<TeacherTrainVo>> getTeacherTrainAttList(@Valid @RequestBody TeacherTrainAttQueryForm teacherTrainAttQueryForm);

    /**
     *  根据教师人员id获取基本考勤信息
     *
     * @param tid  教师人员id
     * @return
     */
    @GetMapping(value = path+"/getTeacherTrain")
    Result<TeacherTrainAttBaseVo> getTeacherTrain(@RequestParam long tid);

    /**
     *  根据教师人员id发送考勤通知
     *
     * @param ids 教师人员id数组[111,222]
     * @return
     */
    @PostMapping(value = path+"/sendTrainAtt")
    Result sendTrainAtt(@RequestParam String ids);

    /**
     * 导入考勤信息
     *
     * @param  file  文件
     * @return
     */
    @PostMapping(value = path+"/importAtt")
    Result importAtt(MultipartFile file);
}
