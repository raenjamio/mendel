package com.renjamio.challenge_mendel.transaction.infraestructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.create.PutTransactionController;
import com.renjamio.challenge_mendel.transaction.infraestructure.rest.shared.dto.TransactionDTO;
import com.renjamio.challenge_mendel.transaction.domain.TransactionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PutTransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Test create transaction success")
    public void createTransaction() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .put(PutTransactionController.TRANSACTIONS_URL + "/{id}", 2)
                .content(asJsonString(TransactionDTO.builder()
                        .amount(5000.0)
                        .type(TransactionType.CARS)
                        .build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
 //               .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }

    @Test
    @DisplayName("Test return 404 when type is null")
    public void Should_Return404_When_Type_Null() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .put(PutTransactionController.TRANSACTIONS_URL + "/{id}", 2)
                .content(asJsonString(TransactionDTO.builder()
                        .amount(5000.0)
                        .build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
