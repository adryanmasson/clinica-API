package com.example.clinica.models;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<Sexo, String> {

    @Override
    public String convertToDatabaseColumn(Sexo attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public Sexo convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Sexo.valueOf(dbData);
    }
}
