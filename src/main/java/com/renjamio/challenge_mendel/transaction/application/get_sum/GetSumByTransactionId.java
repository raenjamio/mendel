package com.renjamio.challenge_mendel.transaction.application.get_sum;

import com.renjamio.challenge_mendel.transaction.domain.Transaction;
import com.renjamio.challenge_mendel.transaction.infraestructure.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class GetSumByTransactionId {

    private final TransactionRepository transactionRepository;

    public GetSumByTransactionId(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Double execute(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .map(Transaction::getTotalAmount)
                .orElse(0.0);
    }
}
