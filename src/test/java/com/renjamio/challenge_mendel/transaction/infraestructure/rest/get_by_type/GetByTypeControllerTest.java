package com.renjamio.challenge_mendel.transaction.infraestructure.rest.get_by_type;

import com.renjamio.challenge_mendel.transaction.application.get_by_type.GetByType;
import com.renjamio.challenge_mendel.transaction.domain.TransactionType;
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

import java.util.List;

import static com.renjamio.challenge_mendel.transaction.infraestructure.rest.get_by_type.GetByTypeController.GET_BY_TYPE_URL;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GetByTypeControllerTest {

    @MockBean
    GetByType getByType;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("get list of Id transaction by type")
    void Should_ReturnId10_When_Filter_By_Type_Cars() throws Exception
    {
        when(getByType.execute(TransactionType.CARS)).thenReturn(List.of(100L, 200L));

        mvc.perform( MockMvcRequestBuilders
                .get(GET_BY_TYPE_URL + "/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1]").value(200));
    }

    @Test
    @DisplayName("get empty list of Id transaction when filter by type shopping")
    void Should_Return_Empty_List_When_Filter_By_Type_Shopping() throws Exception
    {
        when(getByType.execute(TransactionType.CARS)).thenReturn(List.of(100L, 200L));

        mvc.perform( MockMvcRequestBuilders
                .get(GET_BY_TYPE_URL + "/shopping")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

}