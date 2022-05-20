package com.renjamio.challenge_mendel.transaction.application.find_by_id;

import com.renjamio.challenge_mendel.transaction.domain.Transaction;
import com.renjamio.challenge_mendel.transaction.domain.TransactionNotFound;
import com.renjamio.challenge_mendel.transaction.infraestructure.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FindById {

    private final TransactionRepository transactionRepository;

    public FindById(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction execute(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFound(id));
    }
}
