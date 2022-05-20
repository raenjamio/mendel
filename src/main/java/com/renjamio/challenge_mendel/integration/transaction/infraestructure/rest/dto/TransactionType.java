package com.renjamio.challenge_mendel.integration.transaction.infraestructure.rest.dto;

public enum TransactionType {

    CARS("cars"), SHOPPING("shopping");

    private final String name;

    TransactionType(String name) {
        this.name = name;
    }
}
