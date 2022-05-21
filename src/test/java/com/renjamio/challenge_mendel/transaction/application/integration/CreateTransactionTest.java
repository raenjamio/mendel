package com.renjamio.challenge_mendel.transaction.application.integration;

import com.renjamio.challenge_mendel.shared.domain.BadRequestAlertException;
import com.renjamio.challenge_mendel.transaction.application.create.CreateTransaction;
import com.renjamio.challenge_mendel.transaction.domain.Transaction;
import com.renjamio.challenge_mendel.transaction.domain.TransactionType;
import com.renjamio.challenge_mendel.transaction.infraestructure.repository.TransactionRepository;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.shared.dto.TransactionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CreateTransactionTest {

    @Autowired
    CreateTransaction createTransaction;
    @Autowired
    TransactionRepository transactionRepository;

    @BeforeEach
    public void initEach(){
       transactionRepository.deleteAll();
    }

    @Test
    @DisplayName("Test create transaction success")
    void Should_Create_Transaction_When_Amount_Type_Valid()
    {
        long idTransaction = 150L;
        double amountTransaction1 = 5000.0;
        createTransaction.execute(idTransaction, TransactionDTO.builder()
                .amount(amountTransaction1)
                .type(TransactionType.CARS)
                .build());

        var transactionFound = transactionRepository.findById(idTransaction);

        assertTrue(transactionFound.isPresent());
        Transaction transaction = transactionFound.get();

        assertThat(transaction.getAmount()).isEqualTo(amountTransaction1);
        assertThat(transaction.getParent()).isNull();
        assertThat(transaction.getType()).isEqualTo(TransactionType.CARS);
    }

    @Test
    @DisplayName("Test create transaction with parent_id success")
    void Should_Create_Transaction_When_ParentId()
    {
        long idTransaction = 150L;
        double amountTransaction1 = 5000.0;
        long idTransaction2 = 160L;
        double amountTransaction2 = 15000.0;

        createTransaction.execute(idTransaction, TransactionDTO.builder()
                .amount(amountTransaction1)
                .type(TransactionType.CARS)
                .build());
        createTransaction.execute(idTransaction2, TransactionDTO.builder()
                .amount(amountTransaction2)
                .type(TransactionType.SHOPPING)
                .parentId(idTransaction)
                .build());

        var transactionFound = transactionRepository.findById(idTransaction2);

        assertTrue(transactionFound.isPresent());
        Transaction transaction = transactionFound.get();

        assertThat(transaction.getAmount()).isEqualTo(amountTransaction2);
        assertThat(transaction.getParent().getId()).isEqualTo(idTransaction);
        assertThat(transaction.getType()).isEqualTo(TransactionType.SHOPPING);
    }

    @Test
    @DisplayName("Test should return parentId not found when parent_id not found")
    void Should_Return_Error_When_ParentId_NotFound()
    {
        long idTransaction = 150L;
        double amountTransaction1 = 5000.0;

        var thrown = Assertions.assertThrows(BadRequestAlertException.class, () -> {
            createTransaction.execute(idTransaction, TransactionDTO.builder()
                    .amount(amountTransaction1)
                    .type(TransactionType.CARS)
                    .parentId(10000L)
                    .build());
        });

        assertThat(thrown.getMessage()).isEqualTo("Error creating transaction parentId: 10000 not found");
    }
}
