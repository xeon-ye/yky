package com.deloitte.platform.common.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @Author : jackliu
 * @Date : Create in 11:13 15/02/2019
 * @Description :
 * @Modified :
 */
@Slf4j
public class GdcTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        ClassMetadata classMetadata=metadataReader.getClassMetadata();
        String className = classMetadata.getClassName();
        if(className.contains("demomybatiesauto.feign")){
           log.info("====> exclude java file is "+className);
           return true;
        }
        return false;
    }
}
