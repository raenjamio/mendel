package com.renjamio.challenge_mendel.transaction.application.unit;

import com.renjamio.challenge_mendel.transaction.application.create.CreateTransaction;
import com.renjamio.challenge_mendel.transaction.domain.Transaction;
import com.renjamio.challenge_mendel.transaction.domain.TransactionType;
import com.renjamio.challenge_mendel.transaction.infraestructure.repository.TransactionRepository;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.create.mapper.TransactionDtoToEntity;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.shared.dto.TransactionDTO;
import com.renjamio.challenge_mendel.transaction.shared.MockTransaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTransactionTest {

    @InjectMocks
    CreateTransaction createTransaction;

    @Mock
    TransactionRepository transactionRepository;
    @Mock
    TransactionDtoToEntity transactionDtoToEntity;


    @Test
    @DisplayName("Test create transaction success")
    void Should_Create_Transaction_When_Amount_Type_Valid() {
        long idTransaction = 150L;
        double amountTransaction1 = 5000.0;
        var transactionDTO = TransactionDTO.builder()
                .amount(amountTransaction1)
                .type(TransactionType.CARS)
                .build();
        var transactionEntity = MockTransaction.mock(idTransaction, amountTransaction1, TransactionType.CARS);

        when(transactionRepository.findById(idTransaction)).thenReturn(Optional.empty());
        when(transactionRepository.save(transactionEntity)).thenReturn(transactionEntity);
        when(transactionDtoToEntity.toEntity(idTransaction, transactionDTO)).thenReturn(transactionEntity);

        var transactionCreated = createTransaction.execute(idTransaction, transactionDTO);

        assertThat(transactionCreated.getId()).isEqualTo(idTransaction);
        assertThat(transactionCreated.getType()).isEqualTo(TransactionType.CARS);
        assertThat(transactionCreated.getParent()).isNull();
    }
}
