package com.example.demo.infrastructure.tool;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * id生成工具
 *
 * @author 王景阳
 * @date 2023-04-01 9:40
 */
public class IdTool {
    private final static Snowflake SNOWFLAKE = IdUtil.getSnowflake();

    public static long generateIdOfLong() {
        return SNOWFLAKE.nextId();
    }

    public static String generateIdOfString() {
        return SNOWFLAKE.nextIdStr();
    }
}
