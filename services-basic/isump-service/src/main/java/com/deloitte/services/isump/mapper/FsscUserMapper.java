package com.deloitte.services.isump.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.FsscUserVo;
import com.deloitte.services.isump.entity.FsscUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangdi
 * @since 2019-04-15
 */
public interface FsscUserMapper extends BaseMapper<FsscUser> {

    /**
     * 列表查询（不分页）
     * @param userCode 员工号
     * @param userName 员工名称
     * @return
     */
    List<FsscUserVo> queryFsscUserInfo(@Param("userCode") String userCode, @Param("userName") String userName);


    /**
     * 列表查询（分页）
     * @param userCode 员工编码
     * @param userName 员工名称
     * @param country 国籍
     * @param payeeType 收款人类型
     * @param page 分页
     * @return
     */
    List<FsscUserVo> queryFsscUserInfoPage(@Param("userCode") String userCode,
                                           @Param("userName") String userName,
                                           @Param("country") String country,
                                           @Param("idCard") String idCard,
                                           @Param("payeeType") List<String> payeeType,
                                           @Param("page") IPage page);
}
