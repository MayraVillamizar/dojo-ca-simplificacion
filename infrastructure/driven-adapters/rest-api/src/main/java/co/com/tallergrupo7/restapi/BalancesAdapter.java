package co.com.tallergrupo7.restapi;

import co.com.tallergrupo7.model.Account;
import co.com.tallergrupo7.model.Balances;
import co.com.tallergrupo7.model.BalancesRequest;
import co.com.tallergrupo7.model.gateways.BalancesGateway;
import co.com.tallergrupo7.restapi.model.RetrieveBalanceResponse;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BalancesAdapter implements BalancesGateway {

    String url = "https://shy-smoke-9103.getsandbox.com/v1/operations/product-specific/deposits/accounts/balances";

    @Override
    public Mono<Balances> consultBalances(BalancesRequest balancesRequest) {

        return WebClient.create(url).post().contentType(MediaType.APPLICATION_JSON)
                .body (balancesRequest, BalancesRequest.class).retrieve()
                .bodyToMono(RetrieveBalanceResponse.class).map(r -> r.getData().get(0).getAccount().getBalances());


    }
}