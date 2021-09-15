package com.github.wpyuan.common.invoke.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.TargetClassAware;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author PeiYuan
 */
@Slf4j
@UtilityClass
public class ReflectionUtil extends ReflectionUtils {

    /**
     * 获取对象类上的指定注解的值
     * @param annotationClazz 注解class
     * @param target 目标对象
     * @return 指定注解的值
     */
    public Map<String, Object> getAnnotationValueForType(Class<? extends Annotation> annotationClazz, Object target) {
        Map<String, Object> valueMap = new HashMap<>(2);
        Class clazz = target.getClass();
        if (target instanceof TargetClassAware) {
            clazz = AopUtils.getTargetClass(target);
        }
        Annotation anno = clazz.getAnnotation(annotationClazz);
        String value = anno.toString();
        value = value.substring(value.indexOf("(") + 1, value.length() - 1);
        String[] keyValues = value.split(",");
        for (String keyValue: keyValues) {
            String[] keyFistValueSecond = keyValue.split("=");
            valueMap.put(keyFistValueSecond[0].trim(), keyFistValueSecond.length == 2 ? keyFistValueSecond[1] : null);
        }

        return valueMap;
    }
}
