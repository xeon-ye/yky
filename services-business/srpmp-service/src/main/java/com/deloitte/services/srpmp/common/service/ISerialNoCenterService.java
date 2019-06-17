package com.deloitte.services.srpmp.common.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.services.srpmp.common.entity.SerialNoCenter;

/**
 * @Author : lixin
 * @Date : Create in 2019-03-04
 * @Description : SerialNoCenter服务类接口
 * @Modified :
 */
public interface ISerialNoCenterService extends IService<SerialNoCenter> {

    public JSONObject getNextAPLNo(String header,  DeptVo deptVo);

    public String getNextPUDNo(String header);

    public String getNextAPDNo(String header);

    public String getNextBJNo(String header, String projectCode);

    public String getNextAcceptNo(String header, String projectCode);

    String getNextResultNo(String header);

    String getNextResultTransNo(String header);
}
