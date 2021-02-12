package co.com.tallergrupo7.usecase.balancemovement;

import co.com.tallergrupo7.model.*;
import co.com.tallergrupo7.model.gateways.BalancesGateway;
import co.com.tallergrupo7.model.gateways.MovementsGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class BalanceMovementUcTest {

   @Mock
   private MovementsGateway movementsGateway;

   @Mock
   private BalancesGateway balancesGateway;


    @Test
    public void getBalancesMovements(){

        // Dado que
        Account account = new Account();
        account.setType("CUENTA_CORRIENTE");
        account.setNumber("55131428291");

        TransactionFilter transactionF = new TransactionFilter();
        transactionF.setDescription("Pago Nomina");
        transactionF.setEndDate("2020-01-30");
        transactionF.setStartDate("2020-01-01");

        BalancesRequestData balancesRequestData = new BalancesRequestData();
        balancesRequestData.setAccount(account);
        balancesRequestData.setFilter(transactionF);
        List<BalancesRequestData> balancesRData = new ArrayList<>();
        balancesRData.add(balancesRequestData);
        BalancesRequest balancesRequest = new BalancesRequest();
        balancesRequest.setData(balancesRData);

        Balances balances = new Balances();
        balances.setAvailable(new BigDecimal(123));

        Mono<Balances> balancesMono = Mono.just(balances);
        Mockito.when(balancesGateway.consultBalances(account)).thenReturn(balancesMono);

        Movement movement = new Movement();
        movement.setId("123");
        List<Movement> movementLst = new ArrayList<>();
        movementLst.add(movement);
        Mono<List<Movement>> movementMono = Mono.just(movementLst);
        Mockito.when(movementsGateway.consultMovements(balancesRequestData)).thenReturn(movementMono);


         BalanceMovementUseCase balanceMovementUseCase = new BalanceMovementUseCase(balancesGateway, movementsGateway);
         Mono<BalancesMovement> balancesMovementMono = balanceMovementUseCase.getBalancesMovements(balancesRequest);


        StepVerifier.create(balancesMovementMono)
                .expectNextMatches(balancesMovement -> "123".equals(balancesMovement.getMovements().get(0).getId())
                        && balancesMovement.getBalances().getAvailable().intValue() == 123).verifyComplete();





    }
}
