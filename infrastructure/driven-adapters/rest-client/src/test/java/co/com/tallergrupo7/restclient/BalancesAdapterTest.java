package co.com.tallergrupo7.restclient;

import co.com.tallergrupo7.model.Account;
import co.com.tallergrupo7.model.Balances;
import co.com.tallergrupo7.restclient.model.AccountBalance;
import co.com.tallergrupo7.restclient.model.RetrieveBalanceResponse;
import co.com.tallergrupo7.restclient.model.RetrieveBalanceResponseData;
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

public class BalancesAdapterTest {

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
    public void consultBalance() throws JsonProcessingException {
        // Dado que
        Account account = new Account();
        account.setType("CUENTA_CORRIENTE");
        account.setNumber("55131428291");
        //cuando
        Balances balances = new Balances();
        balances.setAvailable(new BigDecimal(123));
        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setBalances(balances);
        RetrieveBalanceResponseData responseData = new RetrieveBalanceResponseData();
        responseData.setAccount(accountBalance);

        List<RetrieveBalanceResponseData> listData = new ArrayList<>();


        RetrieveBalanceResponse response = new RetrieveBalanceResponse();
        listData.add(responseData);
        response.setData(listData);
        ObjectMapper mapper = new ObjectMapper();
        mockBackEnd.enqueue(new MockResponse().setResponseCode(HttpStatus.OK.value())
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody(mapper.writeValueAsString(response)));

        BalancesAdapter balancesAdapter = new BalancesAdapter();
        balancesAdapter.setUrl(mockBackEnd.url("/balances").toString());
        balancesAdapter.setTimeout(6);
        Mono<Balances> balancesR = balancesAdapter.consultBalances(account);
        //Entonces valiodar resultados
        //Blances traiga información
        StepVerifier.create(balancesR).expectNextMatches(balances1 -> balances1.getAvailable().intValue() == 123).verifyComplete();


    }
}
