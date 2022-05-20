package com.renjamio.challenge_mendel.transaction.application.create;

import com.renjamio.challenge_mendel.shared.domain.BadRequestAlertException;
import com.renjamio.challenge_mendel.transaction.infraestructure.repository.TransactionRepository;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.shared.dto.TransactionDTO;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.create.mapper.TransactionDtoToEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateTransaction {

    private final TransactionRepository transactionRepository;
    private final TransactionDtoToEntity transactionDtoToEntity;

    public CreateTransaction(TransactionRepository transactionRepository, TransactionDtoToEntity transactionDtoToEntity) {
        this.transactionRepository = transactionRepository;
        this.transactionDtoToEntity = transactionDtoToEntity;
    }

    public void execute(Long idTransaction, TransactionDTO transactionDTO) {
        var transactionFound = transactionRepository.findById(idTransaction);
        if (transactionFound.isPresent()) {
            throw new BadRequestAlertException(
                    "Error creating transaction transactionId: " + idTransaction +  " exist ", "transaction", "id");
        }
        transactionRepository.save(transactionDtoToEntity.toEntity(idTransaction, transactionDTO));
    }
}
