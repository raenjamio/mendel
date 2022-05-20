package com.renjamio.challenge_mendel.transaction.domain;

import com.renjamio.challenge_mendel.transaction.infraestructure.rest.dto.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", nullable = true)
    private Transaction parent;

    @OneToMany(mappedBy = "parent")
    private Set<Transaction> children = new HashSet<>();

    public Double getTotalAmount() {
        var totalChildren = children.stream()
                .mapToDouble(Transaction::getTotalAmount).sum();
        return totalChildren + amount;
    }
}
