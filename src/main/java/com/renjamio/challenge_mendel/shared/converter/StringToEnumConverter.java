package com.renjamio.challenge_mendel.shared.converter;

import com.renjamio.challenge_mendel.transaction.domain.TransactionType;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, TransactionType> {
    @Override
    public TransactionType convert(String source) {
        return TransactionType.valueOf(source.toUpperCase());
    }
}
