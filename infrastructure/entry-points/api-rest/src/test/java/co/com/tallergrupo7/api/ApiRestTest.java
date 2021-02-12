package co.com.tallergrupo7.api;

import co.com.tallergrupo7.model.BalancesMovement;
import co.com.tallergrupo7.model.BalancesRequest;
import co.com.tallergrupo7.model.BalancesResponse;
import co.com.tallergrupo7.usecase.balancemovement.BalanceMovementUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.cloud.config.enabled=false"})
public class ApiRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BalanceMovementUseCase balanceMovementUseCase;

    @Test
    public void getBalancesMovements(){

        BalancesRequest balancesRequest = new BalancesRequest();
        Mono<BalancesMovement> balancesMovementMono = Mono.just(new BalancesMovement());
        Mockito.when(balanceMovementUseCase.getBalancesMovements(balancesRequest)).thenReturn(balancesMovementMono);

        webTestClient.post().uri("/tallergrupo7/SaldosMovimientos")
                .accept(MediaType.APPLICATION_JSON).body(Mono.just(balancesRequest), BalancesRequest.class)
                .exchange().expectStatus().isOk().expectBody(BalancesResponse.class);

    }

}
