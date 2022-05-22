package com.renjamio.challenge_mendel.transaction.infraestructure.rest.create.mapper;

import com.renjamio.challenge_mendel.shared.domain.BadRequestAlertException;
import com.renjamio.challenge_mendel.transaction.application.find_by_id.FindById;
import com.renjamio.challenge_mendel.transaction.domain.Transaction;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.shared.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionDtoToEntity {

    private final FindById findById;

    public TransactionDtoToEntity(FindById findById) {
        this.findById = findById;
    }

    public Transaction toEntity(Long transactionId, TransactionDTO transactionDTO) {
        if (Objects.nonNull(transactionDTO.getParentId())) {
            var parent = findById.execute(transactionDTO.getParentId());
            return new Transaction(transactionId, transactionDTO.getAmount(), transactionDTO.getType(), parent, null);
        }
        return new Transaction(transactionId, transactionDTO.getAmount(), transactionDTO.getType(), null, null);
    }
}
