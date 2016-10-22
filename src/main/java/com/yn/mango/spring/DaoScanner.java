package com.yn.mango.spring;

import com.yn.mango.annotation.DAO;
import com.yn.mango.util.L;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yangnan on 16/10/22.
 * DAO扫描类
 * BeanFactoryPostProcessor 后置通知
 */
public class DaoScanner implements BeanFactoryPostProcessor {

    /* 扫描的包 */
    private List<String> packages;

    private List<String> locations;

    private String[] DAO_ENDS = {"DAO", "Dao"};


    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        List<Class<?>> daoClasses = findDaoClasses();
        System.out.println(Arrays.toString(daoClasses.toArray()));
    }

    private List<Class<?>> findDaoClasses() {
        List<Class<?>> daoClasses = new ArrayList<Class<?>>(locations.size());
        try {
            /**根据配置路径自动加载符合路径规则的xml文件、类文件等等；
             查找范围：当前工程，当前工程依赖的jar包；**/
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (String location : locations) {
                L.info(location);
                Resource[] resources = resourcePatternResolver.getResources(location);
                for (Resource r : resources) {
                    MetadataReader reader = metadataReaderFactory.getMetadataReader(r);
                    AnnotationMetadata metadata = reader.getAnnotationMetadata();
                    if (metadata.hasAnnotation(DAO.class.getName())) {
                        ClassMetadata m = reader.getClassMetadata();
                        L.info(m.getClassName());
                        daoClasses.add(Class.forName(m.getClassName()));
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        return daoClasses;
    }

    public void setPackages(List<String> packages) {
        locations = new ArrayList<String>(packages.size());
        for (String p : packages) {
            for (String dao : DAO_ENDS) {
                String location = "classpath*:" + p.replaceAll("\\.", "/") + "/**/*" + dao + ".class";
                locations.add(location);
            }
        }
    }
}
