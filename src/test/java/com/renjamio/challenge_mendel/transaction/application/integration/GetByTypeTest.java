package com.renjamio.challenge_mendel.transaction.application.integration;

import com.renjamio.challenge_mendel.transaction.application.get_by_type.GetByType;
import com.renjamio.challenge_mendel.transaction.domain.TransactionType;
import com.renjamio.challenge_mendel.transaction.infraestructure.repository.TransactionRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@Sql({"/import_transactions.sql"})
public class GetByTypeTest {

    @Autowired
    GetByType getByType;
    @Autowired
    TransactionRepository transactionRepository;

    @AfterEach
    public void initEach(){
        transactionRepository.deleteAll();
    }

    @Test
    @DisplayName("should return the list of ids of transactions of type car")
    void Should_Return_One_TransactionId_When_Type_Is_Car() {
        var listOfTypeCars = getByType.execute(TransactionType.CARS);

        assertThat(listOfTypeCars, hasItem(10L));
        assertThat(listOfTypeCars, hasSize(1));
    }

    @Test
    @DisplayName("should return the list of ids of transactions of type shopping")
    void Should_Return_Two_TransactionId_When_Type_Is_Car() {
        var listOfTypeCars = getByType.execute(TransactionType.SHOPPING);

        assertThat(listOfTypeCars, hasItems(11L, 12L));
        assertThat(listOfTypeCars, hasSize(2));
    }
}
