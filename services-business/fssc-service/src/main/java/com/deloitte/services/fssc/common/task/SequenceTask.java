package com.deloitte.services.fssc.common.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.ExceptionUtil;
import com.deloitte.services.fssc.common.service.FsscRedisService;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
import com.deloitte.services.fssc.system.unit.mapper.UnitInfoMapper;
import com.deloitte.services.fssc.util.DocumentNumberUtil;
import com.deloitte.services.fssc.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
@Slf4j
public class SequenceTask {
    @Autowired
    private  UnitInfoMapper unitInfoMapper;

    @Autowired
    private IFileService fileService;

    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    @Autowired
    private FsscRedisService redisService;

    public static final String sequenceKey="FSSC_SEQUENCE_KEY";
    /**
     * 每个月1号00：00：00执行 重置序列
     */
    @Scheduled(cron = "0 0 0 1 * ?")
//    @Scheduled(cron = "0 59 10 ? * *")
    @Transactional
    public void resetSequence(){
        try {
            String string = redisService.getString(sequenceKey);
            if("Y".equals(string)){
                log.info("已经有其他机器执行!");
                return;
            }
            redisService.set(sequenceKey,"Y");
        }catch (Exception e){
            e.printStackTrace();
            log.error("redis调用出错{}", ExceptionUtil.getStackTraceAsString(e));
        }
        log.info("重置序列开始!");
        Map<String, String> keys = DocumentNumberUtil.KEYS;
        for (Map.Entry<String,String> entry:keys.entrySet()){
            String sequenceName = entry.getKey()+DocumentNumberUtil.NUMBER;
            log.info("重置序列start:{}",sequenceName);
            try {
                Long sequence = unitInfoMapper.getSequence(sequenceName)-1;
                log.info("sequence 值 start:{}",sequence);
                sequence=sequence-(sequence+sequence);
                unitInfoMapper.resetSequenceName(sequenceName,sequence);
                log.info("重置序列end:{}",sequenceName);
            }catch (BadSqlGrammarException e){
                String message = e.getSQLException().getMessage();
                if(StringUtil.isNotEmpty(message)&&message.contains("ORA-02289")){
                    unitInfoMapper.createSequenceName(sequenceName);
                    log.info("创建序列:{}",sequenceName);
                }

                log.error("序列重置失败，重新创建序列 NAME:{}",sequenceName);
            }
        }
        redisService.set(sequenceKey,"N");
        log.info("重置序列结束!");
    }

    /**
     * 每天晚上11点执行 删除垃圾文件
     */
    @Scheduled(cron = "0 00 23 * * ?")
    @Transactional
    public void deleteRubbishFiles(){
        log.info("删除垃圾文件开始!");
        QueryWrapper<File> fileQueryWrapper=new QueryWrapper<>();
        fileQueryWrapper.isNull(File.DOCUMENT_ID);
        List<File> fileList = fileService.list(fileQueryWrapper);
        if(CollectionUtils.isNotEmpty(fileList)){
            log.info("垃圾文件:{}",fileList);
            for (File file:fileList){
                Long remoteFileId = file.getRemoteFileId();
                if(remoteFileId!=null){
                    Result result = fileOperatorFeignService.deleteFile(remoteFileId);
                    if(result.isSuccess()){
                        log.info("删除成功:{}",file.getId());
                        fileService.removeById(file.getId());
                    }
                }
            }
        }
        log.info("删除垃圾文件结束!");
    }


}
