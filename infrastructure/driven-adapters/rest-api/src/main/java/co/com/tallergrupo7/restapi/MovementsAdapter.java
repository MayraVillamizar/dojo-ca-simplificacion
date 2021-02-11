package co.com.tallergrupo7.restapi;

import co.com.tallergrupo7.model.Account;
import co.com.tallergrupo7.model.BalancesRequest;
import co.com.tallergrupo7.model.Movement;
import co.com.tallergrupo7.model.gateways.MovementsGateway;
import co.com.tallergrupo7.restapi.model.*;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MovementsAdapter implements MovementsGateway {

    String url = "https://purple-wave-7604.getsandbox.com/deposit/movements";

    public Mono<List<Movement>> consultMovements(Account account){

        Transaction transaction = Transaction.builder()
                .description("pago nomina").startDate("2020-01-01").endDate("2020-01-31").build();

        RetrieveTransactionRequestData data = RetrieveTransactionRequestData.builder()
                .pagination(Pagination.builder().size(20).key(1).build())
                .account(account).transaction(transaction)
                .office(Office.builder().code("406").name("direccion general").build()).build();
        List<RetrieveTransactionRequestData> datals = new ArrayList<>();
        datals.add(data);
        RetrieveTransactionRequest request = RetrieveTransactionRequest.builder().data(datals).build();

        return WebClient.create(url).post().header("Transaction-Tracker", UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON)
                .body (request, RetrieveTransactionRequest.class).retrieve()
                .bodyToMono(RetrieveTransactionResponse.class).map(r -> r.getData().get(0).getTransaction());




    }
}
