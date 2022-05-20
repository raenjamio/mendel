package com.renjamio.challenge_mendel.transaction.infraestructure.rest;

import com.renjamio.challenge_mendel.transaction.application.get_by_type.GetByType;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.dto.TransactionType;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.renjamio.challenge_mendel.transaction.infraestructure.rest.PutTransactionController.TRANSACTIONS_URL;

@RestController
@Log4j2
public class GetByTypeController {

    private final GetByType getByType;

    public GetByTypeController(GetByType getByType) {
        this.getByType = getByType;
    }

    @GetMapping(TRANSACTIONS_URL + "/types/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<Long> get(@PathVariable TransactionType type) {
        return getByType.execute(type);
    }
}
