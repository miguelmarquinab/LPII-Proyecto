package com.lp2.sisinventario.sistemainventario.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class VarcharBooleanConverter implements AttributeConverter<Boolean, String>{

    @Override
    public String convertToDatabaseColumn(Boolean value) { return value==null?null:(value?"1":"0"); }
    @Override
    public Boolean convertToEntityAttribute(String value) {
        if (value == null) return null;
        var v = value.trim().toLowerCase();
        return v.equals("1") || v.equals("si") || v.equals("true") || v.equals("activo");
    }
}
