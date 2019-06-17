package com.deloitte.platform.common.core.monitor;

import lombok.Data;

import java.util.Date;

/**
 * @Author : jackliu
 * @Date : Create in 10:34 11/03/2019
 * @Description :
 * @Modified :
 */
@Data
public class ClientStatus {

    /**
     * 当前进程运行的主机名
     */
    private String host;
    /**
     * 当前进程所在的IP地址
     */
    private String ipAddress;
    /**
     * 空闲内存
     */
    private long freeMemory;
    /**
     * 内存总量
     */
    private long totalMemory;
    /**
     * java虚拟机允许开启的最大的内存
     */
    private long maxMemory;

    /**
     * 操作系统名称
     */
    private String osName;
    /**
     * 进程号
     */
    private long pid;


    /**
     * 程序启动时间
     */
    private Date startTime;

    /**
     * 类所在路径
     */
    private String classPath;

    private String projectPath;

    /**
     * 程序运行时间，单位毫秒
     */
    private long runtime;
    /**
     * 线程总量
     */
    private int threadCount;


    @Override
    public String toString() {
        StringBuffer sb=new StringBuffer("");
        sb.append("=======================当前虚拟机状态==================").append("\r\n");
        sb.append("------主机信息------：").append("\r\n");
        sb.append("主机名：").append(host).append("\r\n");
        sb.append("IP地址：").append(ipAddress).append("\r\n");
        sb.append("操作系统").append(osName).append("\r\n");

        sb.append("------程序运行情况------：").append("\r\n");
        sb.append("程序启动时间").append(startTime).append("\r\n");
        sb.append("程序运行时间(毫秒)").append(runtime).append("\r\n");
        sb.append("类所在路径").append(classPath).append("\r\n");
        sb.append("内存总量").append(totalMemory).append("\r\n");
        sb.append("空闲内存").append(freeMemory).append("\r\n");
        sb.append("虚拟机允许开启的最大的内存").append(maxMemory).append("\r\n");
        sb.append("进程号").append(pid).append("\r\n");
        sb.append("线程总量").append(threadCount).append("\r\n");

        return sb.toString();
    }
}
