package com.deloitte.platform.common.core.common;

/**
 * @Author : jackliu
 * @Date : Create in 15:46 17/02/2019
 * @Description :  常量类，注意分组，类似的放在一组里面
 * @Modified :
 */
public class Const {

   /**
    *   权限相关的常量
    * */
    public interface Role {
        //超级管理员
        int ROLE_SUPER_ADMIN = 1;
        //管理员
        int ROLE_ADMIN = 1;
        //普通用户
        int ROLE_CUSTOMER = 0;

    }

    /**
     *   是否，好坏，1，0相关的常量
     * */
    public interface TrueOfFalse {
         int YES=1;
         int NO=0;
         static final String TRUE="YES";
         static final String FLASE="NO";

    }

    /**
     *   是否，好坏，1，0相关的常量
     * */
    public interface FILECONST{
         //块文件存在
        int BLOCK_FILE_EXIST = 1;
        // 块文件不存在
        int BLOCK_FILE_NOT_EXIST = 0;
    }



    private Const() {
    }


}
