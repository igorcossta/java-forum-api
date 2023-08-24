package io.igorcossta.util;

import io.igorcossta.thread.ThreadType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ThreadTypeConverter implements AttributeConverter<ThreadType, String> {
    @Override
    public String convertToDatabaseColumn(ThreadType attribute) {
        return attribute.getType();
    }

    @Override
    public ThreadType convertToEntityAttribute(String dbData) {
        return ThreadType.valueOf(dbData);
    }
}
