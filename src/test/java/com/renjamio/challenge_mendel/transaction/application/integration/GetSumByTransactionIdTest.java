package com.renjamio.challenge_mendel.transaction.application.integration;

import com.renjamio.challenge_mendel.transaction.application.get_sum.GetSumByTransactionId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql({"/import_transactions.sql"})
@Transactional
class GetSumByTransactionIdTest {

    @Autowired
    GetSumByTransactionId getSumByTransactionId;

    @Test
    @DisplayName("should return the sum of related records by parent_id (ID_10: 5000 + ID_11: 10000 + ID_12: 5000) = 20000")
    void Should_Return_20000_When_TransactionId_Is_10() {
        var totalSum = getSumByTransactionId.execute(10L);

        assertThat(totalSum).isEqualTo(20000);
    }

    @Test
    @DisplayName("should return 15000 the sum of related records (ID_11: 10000 + ID_10: 5000) = 15000")
    void Should_Return_15000_When_TransactionId_Is_11() {
        var totalSum = getSumByTransactionId.execute(11L);

        assertThat(totalSum).isEqualTo(15000);
    }

    @Test
    @DisplayName("should return the amount of ID_12: 5000")
    void Should_Return_5000_When_TransactionId_Is_12()
    {
        var totalSum = getSumByTransactionId.execute(12L);

        assertThat(totalSum).isEqualTo(5000);
    }

}
