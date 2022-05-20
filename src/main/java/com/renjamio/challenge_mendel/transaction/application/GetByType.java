package com.renjamio.challenge_mendel.transaction.application;

import com.renjamio.challenge_mendel.transaction.domain.Transaction;
import com.renjamio.challenge_mendel.transaction.infraestructure.repository.TransactionRepository;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.dto.TransactionType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetByType {

    private final TransactionRepository transactionRepository;

    public GetByType(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Long> execute(TransactionType transactionType) {
        return transactionRepository.findAllByType(transactionType).stream()
                .map(Transaction::getId)
                .collect(Collectors.toList());
    }
}
