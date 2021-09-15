package com.github.wpyuan.common.invoke.util;

import com.github.wpyuan.common.invoke.annotation.ResponseParse;
import com.github.wpyuan.common.invoke.parse.IParse;
import com.github.defaultcore.helper.ApplicationContextHelper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *     解析工具类
 * </p>
 *
 * @author wangpeiyuan
 * @date 2021/9/13 8:50
 */
@UtilityClass
@Slf4j
public class ParseUtil {

    /**
     * 扫描解析器，返回对应系统代码的解析器
     * @param system 系统代码
     * @return 解析器列表
     */
    public <T> List<IParse> scanParseHandler(String system) {
        List<IParse> parseList = new ArrayList<>();
        Map<String, Object> responseParseMap = ApplicationContextHelper.getApplicationContext().getBeansWithAnnotation(ResponseParse.class);
        responseParseMap.forEach((beanName, bean) -> {
            if (bean instanceof IParse) {
                Map<String, Object> annotationValue = ReflectionUtil.getAnnotationValueForType(ResponseParse.class, bean);
                if (system.equals(annotationValue.get("system"))) {
                    parseList.add((IParse) bean);
                }
            }
        });

        return parseList;
    }
}
