package com.yn.mango.xml;

import com.alibaba.fastjson.JSONObject;
import com.yn.mango.util.L;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * Created by yangnan on 16/10/24.
 */
public class XMlReader {
    public static void main(String[] args) throws FileNotFoundException {
        File f = ResourceUtils.getFile("classpath:config.xml");
        Resource r = new ClassPathResource("config.xml");
        Serializer serializer = new Persister();
        try {
            InputStreamReader is = new InputStreamReader(r.getInputStream(), "utf-8");
            Config config = serializer.read(Config.class, f);
            L.info(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
