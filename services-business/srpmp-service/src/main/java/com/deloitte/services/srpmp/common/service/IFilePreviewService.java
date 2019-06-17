package com.deloitte.services.srpmp.common.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.srpmp.common.param.SysDictQueryParam;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.services.srpmp.common.entity.SysDict;
import com.deloitte.services.srpmp.common.enums.SysDictEnums;

import java.util.List;
import java.util.Map;

/**
 * 文件转化预览
 */
public interface IFilePreviewService{

    String preview(String fileUrl);
}
