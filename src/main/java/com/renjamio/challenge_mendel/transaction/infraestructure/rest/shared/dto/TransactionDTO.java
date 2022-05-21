package com.renjamio.challenge_mendel.transaction.infraestructure.rest.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.renjamio.challenge_mendel.transaction.domain.TransactionType;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class TransactionDTO {

    @NotNull(message = "transaction.amount.not-null")
    private Double amount;
    @NotNull(message = "transaction.type.not-null")
    private TransactionType type;
    @JsonProperty("parent_id")
    private Long parentId;
}
