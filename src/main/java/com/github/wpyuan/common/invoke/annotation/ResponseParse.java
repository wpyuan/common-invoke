package com.github.wpyuan.common.invoke.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author PeiYuan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface ResponseParse {
    /**
     * 系统代码
     * @return
     */
    String system() default "";
}
