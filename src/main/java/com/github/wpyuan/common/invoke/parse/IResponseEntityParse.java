package com.github.wpyuan.common.invoke.parse;

import org.springframework.http.ResponseEntity;

/**
 * ResponseEntity通用返回解析
 * @author PeiYuan
 */
public interface IResponseEntityParse<T, E> extends IParse<T, ResponseEntity<E>>{

}
