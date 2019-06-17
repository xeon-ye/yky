package com.deloitte.platform.common.core.monitor;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @Author : jackliu
 * @Date : Create in 10:30 11/03/2019
 * @Description :
 * @Modified :
 */

@Slf4j
public class StatusUtil {
    /**
     * @param ipAddress 可以为NULL，为NULL则会自动获取，但是如果主机有多个网卡，可能会取错
     * @param startCMD 启动进程的命令，当应用死掉后，会调用此命令来启动
     * @return
     */
    public static ClientStatus getClientStatus(String ipAddress,String startCMD){
        ClientStatus clientStatus = new ClientStatus();
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        Runtime runtime = Runtime.getRuntime();
        //空闲内存
        long freeMemory = runtime.freeMemory();
        clientStatus.setFreeMemory(byteToM(freeMemory));
        //内存总量
        long totalMemory = runtime.totalMemory();
        clientStatus.setTotalMemory(byteToM(totalMemory));
        //最大允许使用的内存
        long maxMemory = runtime.maxMemory();
        clientStatus.setMaxMemory(byteToM(maxMemory));
        //操作系统
        clientStatus.setOsName(System.getProperty("os.name"));
        InetAddress localHost;
        try {
            localHost = InetAddress.getLocalHost();
            String hostName = localHost.getHostName();
            clientStatus.setHost(hostName);
            if(ipAddress == null){
                ipAddress = localHost.getHostAddress();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            log.error("无法获取当前主机的主机名与Ip地址");
            clientStatus.setHost("未知");
        }
        //ip
        clientStatus.setIpAddress(ipAddress);
        //程序启动时间
        long startTime = runtimeMXBean.getStartTime();
        Date startDate = new Date(startTime);
        clientStatus.setStartTime(startDate);
        //类所在路径
        clientStatus.setClassPath(runtimeMXBean.getBootClassPath());
        //程序运行时间
        clientStatus.setRuntime(runtimeMXBean.getUptime());
        //线程总数
        clientStatus.setThreadCount(ManagementFactory.getThreadMXBean().getThreadCount());
        clientStatus.setProjectPath(new File("").getAbsolutePath());
        clientStatus.setPid(getPid());
        return clientStatus;
    }

    /**
     * 把byte转换成M
     * @param bytes
     * @return
     */
    public static long byteToM(long bytes){
        long kb =  (bytes / 1024 / 1024);
        return kb;
    }

    /**
     * 创建一个客户端ID
     * @param ipAddress
     * @return
     */
    public static String makeClientId(String ipAddress){
        String t = "deloittePlatform" + ipAddress + new File("").getAbsolutePath();
        int client_id = t.hashCode();
        client_id = Math.abs(client_id);
        return String.valueOf(client_id);
    }

    /**
     * 获取进程号，适用于windows与linux
     * @return
     */
    public static long getPid(){
        try {
            String name = ManagementFactory.getRuntimeMXBean().getName();
            String pid = name.split("@")[0];
            return Long.parseLong(pid);
        } catch (NumberFormatException e) {
            log.warn("无法获取进程Id");
            return 0;
        }
    }
}
