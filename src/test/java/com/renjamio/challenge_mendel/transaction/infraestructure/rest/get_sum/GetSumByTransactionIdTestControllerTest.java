package com.renjamio.challenge_mendel.transaction.infraestructure.rest.get_sum;

import com.renjamio.challenge_mendel.transaction.application.get_sum.GetSumByTransactionId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.renjamio.challenge_mendel.transaction.infraestructure.rest.get_sum.GetSumByTransactionIdController.GET_SUM_URL;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GetSumByTransactionIdTestControllerTest {

    @MockBean
    GetSumByTransactionId getSumByTransactionId;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("get sum by transactionId")
    void Should_Return_Sum_When_Filter_By_TransactionId_With_Amount() throws Exception
    {
        long transactionId = 2000L;
        double sumResult = 10000.50;
        when(getSumByTransactionId.execute(transactionId)).thenReturn(sumResult);

        mvc.perform( MockMvcRequestBuilders
                .get(GET_SUM_URL + "/" + transactionId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sum").value(sumResult));
    }

    @Test
    @DisplayName("get zero when transaction id not found")
    void Should_Return_Zero_When_TransactionId_Not_Found() throws Exception
    {
        long transactionId = 2000L;
        double sumResult = 10000.50;
        long otherTransactionId = 1;
        when(getSumByTransactionId.execute(transactionId)).thenReturn(sumResult);

        mvc.perform( MockMvcRequestBuilders
                .get(GET_SUM_URL + "/" + otherTransactionId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sum").value(0));
    }

}