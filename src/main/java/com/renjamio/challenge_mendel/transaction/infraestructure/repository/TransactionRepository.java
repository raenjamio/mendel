package com.renjamio.challenge_mendel.transaction.infraestructure.repository;

import com.renjamio.challenge_mendel.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
