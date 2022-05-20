package com.renjamio.challenge_mendel.transaction.infraestructure.rest;

import com.renjamio.challenge_mendel.transaction.application.CreateTransaction;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.dto.TransactionDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Log4j2
public class PutTransactionController {

    public static final String TRANSACTIONS_URL = "/transactions";
    private final CreateTransaction createTransaction;

    public PutTransactionController(CreateTransaction createTransaction) {
        this.createTransaction = createTransaction;
    }

    /**
     *
     * @param idTransaction required
     * @return
     */
    @PutMapping(TRANSACTIONS_URL + "/{idTransaction}")
    @ResponseStatus(HttpStatus.OK)
    public void createTransaction(@PathVariable Long idTransaction, @Valid @RequestBody TransactionDTO transactionDTO) {

        log.debug("REST request to create transaction by idTransaction: {} ", idTransaction);

        createTransaction.execute(idTransaction, transactionDTO);
    }
}
