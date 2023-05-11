package com.venly.task.entity.converter;

import com.venly.task.common.enumeration.RelationType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Locale;

@Converter(autoApply = true)
public class RelationTypeConverter implements AttributeConverter<RelationType, String> {

    @Override
    public String convertToDatabaseColumn(RelationType relationType) {
        return relationType.name().toLowerCase();
    }

    @Override
    public RelationType convertToEntityAttribute(String s) {
        return RelationType.valueOf(s.toUpperCase(Locale.ROOT));
    }
}
