package com.example.demo.infrastructure.tool;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author 王景阳
 * @date 2023-04-06 14:26
 */
public class JsonTool {

    private static ObjectMapper OBJECT_MAPPER;

    public static ObjectMapper getObjectMapper() {
        if (OBJECT_MAPPER == null) {
            OBJECT_MAPPER = objectMapper();
        }
        return OBJECT_MAPPER;
    }

    /**
     * 将对象转换为json字符串
     */
    public static String toJsonStr(Object o) {
        String jsonStr;
        try {
            ObjectMapper objectMapper = getObjectMapper();
            jsonStr = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new String(jsonStr.getBytes(), StandardCharsets.ISO_8859_1);
    }

    /**
     * 将对象转化为指定类型的对象
     */
    public static <T> T toBean(Object o, Class<T> c) {
        String jsonStr;
        try {
            ObjectMapper objectMapper = getObjectMapper();
            jsonStr = objectMapper.writeValueAsString(o);
            return objectMapper.readValue(jsonStr, c);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMapper objectMapper() {
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, localDateTimeJsonSerializer());
        timeModule.addDeserializer(LocalDateTime.class, localDateTimeJsonDeserializer());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(timeModule);
        return objectMapper;
    }

    /**
     * NOTICE: 注意使用 秒级 时间戳
     */
    public static JsonSerializer<LocalDateTime> localDateTimeJsonSerializer() {
        return new JsonSerializer<LocalDateTime>() {
            @Override
            public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider provider) throws IOException {
                if (dateTime != null) {
                    generator.writeNumber(DateTimeTool.toLong(dateTime));
                }
            }
        };
    }

    /**
     * NOTICE: 注意使用 秒级 时间戳
     */
    public static JsonDeserializer<LocalDateTime> localDateTimeJsonDeserializer() {
        return new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
                return DateTimeTool.toLocalDateTime(parser.getValueAsLong());
            }
        };
    }
}
