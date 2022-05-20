package com.renjamio.challenge_mendel.transaction.domain;

import com.renjamio.challenge_mendel.shared.domain.DomainError;

public class TransactionNotFound extends DomainError {

    public TransactionNotFound(Long id) {
        super("transaction_not_exist", String.format("The transaction <%s> doesn't exist", id));
    }
}
