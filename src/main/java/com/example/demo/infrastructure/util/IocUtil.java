package com.example.demo.infrastructure.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * IocUtil
 *
 * @author 王景阳
 * @date 2023-04-05 10:10
 */
public class IocUtil implements ApplicationContextAware {
    private static ApplicationContext ctx;

    private IocUtil() {
    }

    public static Object get(String name) {
        return ctx.getBean(name);
    }

    public static <T> T get(Class<T> clz) {
        return ctx.getBean(clz);
    }


    //region inject

    public static <T> T get(String name, Class<T> clz) {
        return ctx.getBean(name, clz);
    }

    public static <T> Map<String, T> getMap(Class<T> clz) {
        return ctx.getBeansOfType(clz);
    }

    //endregion

    //region singleton

    public static IocUtil getInstance() {
        return IocUtilHolder.INSTANCE;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext ctx) throws BeansException {
        IocUtil.ctx = ctx;
    }

    private static class IocUtilHolder {
        private static final IocUtil INSTANCE = new IocUtil();
    }

    //endregion singleton
}
