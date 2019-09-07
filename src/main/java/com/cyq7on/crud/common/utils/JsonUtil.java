package com.cyq7on.crud.common.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lianxin
 * @Date: Create in 2018/4/5 22:03
 * @Description: Json工具类
 */
@Slf4j
public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper useAnnotationMapper = new ObjectMapper();
    private static final ObjectMapper notUseAnnotationMapper = new ObjectMapper();
    private static final JsonFactory factory = new JsonFactory();

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        // 使用注解的mapper
        // 设置日期格式
        useAnnotationMapper.setDateFormat(simpleDateFormat);
        // 不把date转换成timestamps
        useAnnotationMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 忽略不存在的字段
        useAnnotationMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许没加引号的字段
        useAnnotationMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 是否使用注解
        useAnnotationMapper.configure(MapperFeature.USE_ANNOTATIONS, true);
        useAnnotationMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
        // Long转字符串
        useAnnotationMapper.registerModule(simpleModule);

        // 不使用注解的mapper
        // 设置日期格式
        notUseAnnotationMapper.setDateFormat(simpleDateFormat);
        // 不把date转换成timestamps
        notUseAnnotationMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 忽略不存在的字段
        notUseAnnotationMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许没加引号的字段
        notUseAnnotationMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 是否使用注解
        notUseAnnotationMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
        notUseAnnotationMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;

        // Long转字符串
        useAnnotationMapper.registerModule(simpleModule);
    }

    /**
     * 将Json对象转换成string
     * @param src   需要转换的对象
     * @return  json字符串
     */
    public static <T> String obj2String(T src) {
        if (src == null) {
            return null;
        }
        try {
            return useAnnotationMapper.writeValueAsString(src);
        } catch (Exception e) {
            log.warn("parse object to String exception, error:{}", e);
            return null;
        }
    }

    /**
     * 将json字符串转换为对象
     * @param src           json字符串
     * @param objClass      需要转换的对象class
     * @return              对象
     */
    public static <T> T string2Obj(String src, Class<T> objClass) {
        if (src == null || objClass == null) {
            return null;
        }
        try {
            return useAnnotationMapper.readValue(src, objClass);
        } catch (Exception e) {
            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", src, objClass.getName(), e);
            return null;
        }
    }

    /**
     * 将json字符串转换为对象 （多用于集合）
     * @param src               json字符串
     * @param typeReference     TypeReference包装的需要转换的对象，如：new TypeReference<List<UserEntity>>(){}
     * @return                  对象
     */
    public static <T> T string2Obj(String src, TypeReference<T> typeReference) {
        if (src == null || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? src : useAnnotationMapper.readValue(src, typeReference));
        } catch (Exception e) {
            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", src, typeReference.getType(), e);
            return null;
        }
    }


    public static Map<String, String> Object2Map(Object object) {
        Map<String, String> map = new HashMap<String, String>();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);//设置些属性是可以访问的
            try {
                if (field.get(object) instanceof Date) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    map.put(field.getName(), sdf.format(field.get(object)));
                } else {
                    map.put(field.getName(), field.get(object) != null ? field.get(object).toString() : "");
                }
            } catch (Exception e) {
                System.out.println("对象转map失败！！！");
            }
        }
        return map;
    }

    /**
     * 解析腾讯地图 返回的json数据
     * @param restTemplate
     * @param url
     * @return
     */
    public static JsonNode parseMapJson(RestTemplate restTemplate, String url) {
        String json = restTemplate.getForObject(url, String.class);
        JsonNode jsonNode = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonNode = mapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        JsonNode status = jsonNode.get("status");
        if(status.asInt() != 0) {
            return null;
        }
        JsonNode result = jsonNode.get("result");
        return result;
    }
}
