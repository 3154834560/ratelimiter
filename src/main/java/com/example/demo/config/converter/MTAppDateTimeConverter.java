package com.example.demo.config.converter;

import com.example.demo.infrastructure.tool.DateTimeTool;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义DateTime转换器,目前仅提供 (Long/String->LocalDateTime)
 * 仅适用于PathVariable和RequestParam
 *
 * @author 王景阳
 * @date 2022/11/10 18:48
 */
public class MTAppDateTimeConverter implements GenericConverter {
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> convertiblePairs = new HashSet<>();
        convertiblePairs.add(new ConvertiblePair(Long.class, LocalDateTime.class));
        convertiblePairs.add(new ConvertiblePair(String.class, LocalDateTime.class));
        return convertiblePairs;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (targetType.hasAnnotation(PathVariable.class) && source == null) {
            throw new IllegalArgumentException("PathVariable variable can not be null!");
        }
        String str = source.toString();
        try {
            return DateTimeTool.toLocalDateTime(str);
        } catch (NumberFormatException e) {
            return LocalDateTime.parse(str, DateTimeFormatter.ISO_DATE_TIME);
        }
    }
}
