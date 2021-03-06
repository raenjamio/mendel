package com.renjamio.challenge_mendel.transaction.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionType {

    CARS("cars"), SHOPPING("shopping");

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }
}
