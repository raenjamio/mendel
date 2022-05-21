package com.renjamio.challenge_mendel.transaction.shared;

import com.renjamio.challenge_mendel.transaction.domain.Transaction;
import com.renjamio.challenge_mendel.transaction.domain.TransactionType;

public class MockTransaction {

    public  static Transaction mock(Long id, Double amount, TransactionType type) {
        return new Transaction(id, amount, type, null, null);
    }
}
