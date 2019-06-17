package com.deloitte.platform.api.srpmp.project.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeVO;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author : lixin
 * @Date : Create in 2019-03-11
 * @Description :  项目审核相关接口控制器
 * @Modified :
 */
public interface SrpmsProjectAuditClient {

    String path="/srpmp/project/audit";


    /**
     * 查询待办列表
     * @return
     */
    @GetMapping(value = path+"/backlog")
    Result<IPage<TaskNodeVO>> backLogPage(UserVo userVo);




}
