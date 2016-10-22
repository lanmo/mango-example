package com.yn.mango.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by yangnan on 16/10/22.
 */
public class L {

    private static SerializerFeature[] SER = {SerializerFeature.DisableCircularReferenceDetect,
    SerializerFeature.PrettyFormat, SerializerFeature.WriteNullListAsEmpty};

    public static void info(Object obj) {
        System.out.println(JSONObject.toJSONString(obj, SER));
    }

    public static void info(String key, Object obj) {
        System.out.println(key + ":" + JSONObject.toJSONString(obj, SER));
    }
}
