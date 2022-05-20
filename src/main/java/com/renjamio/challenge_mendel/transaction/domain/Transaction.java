package com.renjamio.challenge_mendel.transaction.domain;

import com.renjamio.challenge_mendel.transaction.infraestructure.rest.dto.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    private Long id;
    private Double amount;
    @Enumerated(value = EnumType.STRING)
    private TransactionType type;
    private Long parentId;
}
