package com.github.wpyuan.common.invoke.parse;

import java.util.Map;

/**
 * 解析通用返回解析
 * @author PeiYuan
 */
public interface IParse<T, R> {

    T parse(Map<String, Object> contextParam, R response);
}
