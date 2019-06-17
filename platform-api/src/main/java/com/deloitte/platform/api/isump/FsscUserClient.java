package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.vo.FsscUserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhangdi
 * @Date 15/04/2019
 */
public interface FsscUserClient {
    public static final String path="/isump/fssc";

    /**
     * 列表查询（不分页）
     * @param empNo 员工名称
     * @param userName 员工号
     * @return
     */
    @PostMapping(value = path+"/list/fsscuserinfo")
    Result<List<FsscUserVo>> queryFsscUserInfo(@RequestParam(value = "empNo",required = false) String empNo,
                                               @RequestParam(value  ="userName",required = false) String userName);

    /**
     *  列表查询（分页）
     * @param empNo  员工号
     * @param userName 员工名称
     * @param country 国籍
     * @param payeeType 收款人类型
     * @param currentPage 页码
     * @param pageSize 每而显示条数
     * @return
     */
    @PostMapping(value = path+"/list/fsscuserinfopage")
    Result<GdcPage<FsscUserVo>> queryFsscUserInfoPage(@RequestParam(value = "empNo",required = false) String empNo,
                                                      @RequestParam(value = "userName",required = false) String userName,
                                                      @RequestParam(value = "country",required = false) String country,
                                                      @RequestParam(value = "idCard",required = false) String idCard,
                                                      @RequestParam(value = "payeeType",required = false) List<String> payeeType,
                                                      @RequestParam(value = "currentPage",defaultValue = "0") int currentPage,
                                                      @RequestParam(value = "pageSize",defaultValue = "15") int pageSize);
}
