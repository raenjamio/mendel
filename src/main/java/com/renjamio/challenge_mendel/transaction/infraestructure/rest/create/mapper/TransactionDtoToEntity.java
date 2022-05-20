package com.renjamio.challenge_mendel.transaction.infraestructure.rest.create.mapper;

import com.renjamio.challenge_mendel.shared.domain.BadRequestAlertException;
import com.renjamio.challenge_mendel.transaction.domain.Transaction;
import com.renjamio.challenge_mendel.transaction.infraestructure.repository.TransactionRepository;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.shared.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionDtoToEntity {

    private final TransactionRepository transactionRepository;

    public TransactionDtoToEntity(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction toEntity(Long transactionId, TransactionDTO transactionDTO) {
        if (Objects.nonNull(transactionDTO.getParentId())) {
            var parent = transactionRepository.findById(transactionDTO.getParentId())
                    .orElseThrow(() -> new BadRequestAlertException(
                    "Error creating transaction parentId: " + transactionDTO.getParentId() +  "not found", "transaction", "id"));
            return new Transaction(transactionId, transactionDTO.getAmount(), transactionDTO.getType(), parent, null);
        }
        return new Transaction(transactionId, transactionDTO.getAmount(), transactionDTO.getType(), null, null);
    }
}
