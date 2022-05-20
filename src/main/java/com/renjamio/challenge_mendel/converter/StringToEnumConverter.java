package com.renjamio.challenge_mendel.converter;

import com.renjamio.challenge_mendel.transaction.infraestructure.rest.dto.TransactionType;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, TransactionType> {
    @Override
    public TransactionType convert(String source) {
        return TransactionType.valueOf(source.toUpperCase());
    }
}
