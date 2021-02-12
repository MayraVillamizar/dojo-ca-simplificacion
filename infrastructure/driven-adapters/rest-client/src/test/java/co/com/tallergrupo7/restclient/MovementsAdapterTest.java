package co.com.tallergrupo7.restclient;

import co.com.tallergrupo7.model.*;
import co.com.tallergrupo7.restclient.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MovementsAdapterTest {

    public static MockWebServer mockBackEnd;

    @Before
    public void initializer() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @After
    public void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    public void consultMovement() throws JsonProcessingException {
        // Dado que
        Account account = new Account();
        account.setType("CUENTA_CORRIENTE");
        account.setNumber("55131428291");

        TransactionFilter transactionFilter = new TransactionFilter();
        transactionFilter.setDescription("Pago Nomina");
        transactionFilter.setStartDate("2020-01-30");
        transactionFilter.setStartDate("2020-01-01");

        BalancesRequestData balancesRequestData = new BalancesRequestData();
        balancesRequestData.setAccount(account);
        balancesRequestData.setFilter(transactionFilter);

        //cuando

        Movement movement = new Movement();
        movement.setId("123");
        List<Movement> movementList = new ArrayList<>();
        movementList.add(movement);
        RetrieveTransactionResponseData responseData = new RetrieveTransactionResponseData();
        responseData.setTransaction(movementList);

        List<RetrieveTransactionResponseData> listData = new ArrayList<>();


        RetrieveTransactionResponse response = new RetrieveTransactionResponse();
        listData.add(responseData);
        response.setData(listData);
        ObjectMapper mapper = new ObjectMapper();
        mockBackEnd.enqueue(new MockResponse().setResponseCode(HttpStatus.OK.value())
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody(mapper.writeValueAsString(response)));

        MovementsAdapter movementsAdapter = new MovementsAdapter();
        movementsAdapter.setUrl(mockBackEnd.url("/movements").toString());
        movementsAdapter.setTimeout(6);
        Mono<List<Movement>> movementsR = movementsAdapter.consultMovements(balancesRequestData);
        //Entonces valiodar resultados
        //Blances traiga información
        StepVerifier.create(movementsR).expectNextMatches(movements1 -> "123".equals(movements1.get(0).getId())).verifyComplete();


    }
}
