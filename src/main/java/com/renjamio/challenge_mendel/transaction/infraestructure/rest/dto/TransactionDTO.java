package com.renjamio.challenge_mendel.transaction.infraestructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.renjamio.challenge_mendel.transaction.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {

    @NotNull(message = "transaction.amount.not-null")
    private Double amount;
    @NotNull(message = "transaction.type.not-null")
    private TransactionType type;
    @JsonProperty("parent_id")
    private Long parentId;
}
