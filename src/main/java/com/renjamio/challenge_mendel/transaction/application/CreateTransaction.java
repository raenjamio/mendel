package com.renjamio.challenge_mendel.transaction.application;

import com.renjamio.challenge_mendel.shared.domain.BadRequestAlertException;
import com.renjamio.challenge_mendel.transaction.infraestructure.repository.TransactionRepository;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.dto.TransactionDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class CreateTransaction {

    private final TransactionRepository transactionRepository;

    public CreateTransaction(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void execute(Long idTransaction, TransactionDTO transactionDTO) {
        var transactionFound = transactionRepository.findById(idTransaction);
        if (transactionFound.isPresent()) {
            throw new BadRequestAlertException(
                    "Error creating transaction transactionId: " + idTransaction +  " exist ", "transaction", "id");
        }
        transactionRepository.save(transactionDTO.toEntity(idTransaction));
    }
}
