package com.github.wpyuan.common.invoke.parse.handler;

import com.github.wpyuan.common.invoke.parse.IParse;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 解析处理器
 *
 * @author PeiYuan
 */
@Slf4j
public abstract class AbstractParseHandler<R> {

    private Set<IParse> parseList = new HashSet<>();

    public <T> T parse(Map<String, Object> contextParam, R response) {

        for (IParse parseHandler : this.parseList) {
            if (type().isInstance(parseHandler)) {
                if (!match(contextParam, parseHandler)) {
                    continue;
                }
                return (T) parseHandler.parse(contextParam, response);
            }
        }

        // 无对应解析器，解析返回null
        log.warn("无对应解析器，解析返回null");
        return null;
    }

    /**
     * 解析器类型
     *
     * @return
     */
    public abstract Class type();

    /**
     * 匹配解析器
     *
     * @param contextParam 上下文
     * @param parseHandler 解析器
     * @return 是否匹配，返回true则匹配解析，反之不匹配
     */
    public abstract boolean match(Map<String, Object> contextParam, IParse parseHandler);

    public void setParseList(List<IParse> parseList) {
        this.parseList.addAll(parseList);
    }

    public void setParse(IParse... parse) {
        this.parseList.addAll(Arrays.asList(parse));
    }

    public void setParse(IParse parse) {
        this.parseList.add(parse);
    }

    public List<IParse> getParseList() {
        return Collections.unmodifiableList(new ArrayList<>(this.parseList));
    }
}
