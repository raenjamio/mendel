package com.renjamio.challenge_mendel.transaction.infraestructure.rest;

import com.renjamio.challenge_mendel.transaction.application.get_sum.GetSumByTransactionId;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.dto.SumTransactionDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.renjamio.challenge_mendel.transaction.infraestructure.rest.PutTransactionController.TRANSACTIONS_URL;

@RestController
@Log4j2
public class GetSumByTransactionIdController {

    private final GetSumByTransactionId getSumByTransactionId;

    public GetSumByTransactionIdController(GetSumByTransactionId getSumByTransactionId) {
        this.getSumByTransactionId = getSumByTransactionId;
    }


    @GetMapping(TRANSACTIONS_URL + "/sum/{transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public SumTransactionDTO get(@PathVariable Long transactionId) {
        return new SumTransactionDTO(getSumByTransactionId.execute(transactionId));
    }
}
