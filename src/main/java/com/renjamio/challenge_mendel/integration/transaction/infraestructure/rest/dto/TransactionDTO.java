package com.renjamio.challenge_mendel.integration.transaction.infraestructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
